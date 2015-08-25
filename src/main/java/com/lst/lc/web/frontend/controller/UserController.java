package com.lst.lc.web.frontend.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.BlogDao;
import com.lst.lc.dao.QuestionDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Admin;
import com.lst.lc.entities.Blog;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Question;
import com.lst.lc.entities.QuestionAnswer;
import com.lst.lc.entities.RelUserCourse;
import com.lst.lc.entities.User;
import com.lst.lc.hbase.model.IntegralRecord;
import com.lst.lc.hbase.service.IntegralRecordOperation;
import com.lst.lc.utils.EncryptUtils;
import com.lst.lc.utils.MultipartFileUtils;
import com.lst.lc.web.bean.Info;
import com.lst.lc.web.bean.StatusMessage;
import com.lst.lc.web.service.BlogPageHandler;
import com.lst.lc.web.service.LogHandler;

@Controller
@RequestMapping("/user")
public class UserController {
        
        @Autowired
        private IntegralRecordOperation integralRecordOperation;

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("blogDao")
	private BlogDao blogDao;
	
	@Autowired
	@Qualifier("questionDao")
	private QuestionDao questionDao;
	
	@Autowired
	private LogHandler logHandler;
	
	@Autowired
	@Qualifier("blogPageHandler")
	private BlogPageHandler blogPageHandler;

	public UserController() {
		super();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "frontend/user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public StatusMessage login(HttpSession session, String email,
			String password) throws Exception {
		password = EncryptUtils.encryptMD5(password.getBytes());
		User user = userDao.validateUser(email, password);
		StatusMessage statusMessage;
		String message = null;
		if (user == null) {
			message = "邮箱错误";
			statusMessage = new StatusMessage(0, message);
		} else if (user.getPassword().equals(password)) {
			message = "登录成功";
			session.setAttribute("loginUser", user);
			
			List<User> friends = userDao.getValidateFriends(user.getUserId());
			if(friends.size() > 0){
			        Info info = new Info(); 
			        info.setNum(friends.size());
			        List<String> messages = new ArrayList<String>();
			        for(int i = 0; i < friends.size(); i++){
			                String str = friends.get(i).getUserName()+"请求添加你为好友";
			                messages.add(str);
			        }
			        info.setMessages(messages);
			        System.out.println(info.getNum());
			        session.setAttribute("info", info);
			}
			
			statusMessage = new StatusMessage(1, message);
			// 写进日志，积分加1
			logHandler.toLog(user, "登录网站");
			logHandler.updateIntegral(user.getUserId(), "login");
			integralRecordOperation.update(user.getEmail(), "login");

		} else {
			message = "密码错误";
			statusMessage = new StatusMessage(0, message);
		}
		return statusMessage;
	}

	@RequestMapping(value = "/zone/{userId}", method = RequestMethod.GET)
	public String index(Model model, @PathVariable int userId, String pageNum, String pageSize, String type) {

		User user = userDao.getById(userId);
		model.addAttribute("user", user);
		
		int pageNow = 1;
		int pagesize = 10;
		int sorttype = 1;
		if (pageSize != null) {
			pagesize = Integer.valueOf(pageSize);
		}
		if (pageSize != null) {
			pageNow = Integer.valueOf(pageNum);
		}
		if (type != null) {
			sorttype = Integer.valueOf(type);
		}
		
		model.addAttribute("page",
				blogPageHandler.getBlogs(pageNow, pagesize, sorttype));
		
		return "frontend/user/zone";
	}
	
	@RequestMapping(value = "/{userId}/center", method = RequestMethod.GET)
	public String center(Model model, @PathVariable int userId, HttpSession session) {
		//User user = (User) session.getAttribute("loginUser");
//		if(user == null){
//			return "redirect:/course/courses";
//		}else{
//			user = userDao.getById(user.getUserId());
			User user = userDao.getById(userId);
			model.addAttribute("user",user);
			Set<RelUserCourse> relUserCourses = user.getRelUserCourses();
			Iterator<RelUserCourse> iterator = relUserCourses.iterator();
			List<Course> courses = new ArrayList<Course>();
			while(iterator.hasNext()){
				RelUserCourse relUserCourse = iterator.next();
				Course course = relUserCourse.getCourse();
				courses.add(course);
			}
			model.addAttribute("courses", courses);
			
			List<Blog> blogs = blogDao.getBlogsOfUser(user.getUserId());
			List<Question> questions = questionDao.getQuestionOfUser(user.getUserId());
			model.addAttribute("blogs", blogs);
			model.addAttribute("questions", questions);
			return "frontend/user/center";
//		}
	}
	
	@RequestMapping(value="/user/{userId}/blog", method = RequestMethod.GET)
	public String blog(Model model, @PathVariable int userId, HttpSession session){
		User user = userDao.getById(userId);
		model.addAttribute("user",user);
		List<Blog> blogs = blogDao.getBlogsOfUser(userId);
		model.addAttribute("blogs", blogs);
		model.addAttribute("center_module", "blog");
		return "frontend/user/center";
	}

	@RequestMapping(value="/user/{userId}/course", method = RequestMethod.GET)
	public String course(Model model, @PathVariable int userId, HttpSession session){
		User user = userDao.getById(userId);
		model.addAttribute("user",user);
		Set<RelUserCourse> relUserCourses = user.getRelUserCourses();
		Iterator<RelUserCourse> iterator = relUserCourses.iterator();
		List<Course> courses = new ArrayList<Course>();
		while(iterator.hasNext()){
			RelUserCourse relUserCourse = iterator.next();
			Course course = relUserCourse.getCourse();
			courses.add(course);
		}
		model.addAttribute("courses", courses);
		model.addAttribute("center_module", "course");
		return "frontend/user/center";
	}
	
	@RequestMapping(value="/user/{userId}/ask", method = RequestMethod.GET)
	public String ask(Model model, @PathVariable int userId, HttpSession session){
		User user = userDao.getById(userId);
		model.addAttribute("user",user);
		List<Question> questions = questionDao.getQuestionOfUser(user.getUserId());
		model.addAttribute("questions", questions);
		model.addAttribute("center_module", "ask");
		return "frontend/user/center";
	}
	
	@RequestMapping(value="/user/{userId}/answer", method = RequestMethod.GET)
	public String answer(Model model, @PathVariable int userId, HttpSession session){
		User user = userDao.getById(userId);
		model.addAttribute("user",user);
		List<QuestionAnswer> questionAnswers = new ArrayList<QuestionAnswer>(user.getQuestionAnswers());
		model.addAttribute("answers", questionAnswers);
		model.addAttribute("center_module", "answer");
		return "frontend/user/center";
	}
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public StatusMessage register(String userName, String email,
			String password, String captcha, HttpSession session)
			throws Exception {

		StatusMessage statusMessage;
		String message = null;

		// 先判断邮箱是否已经被注册
		if (userDao.ifEmailExisted(email)) {
			message = "该邮箱已被注册";
			statusMessage = new StatusMessage(0, message);
		}
		// 判断验证码
		else if (!captcha.equals(session.getAttribute("validationCode"))) {
			message = "验证码错误";
			statusMessage = new StatusMessage(0, message);
		} else {
			User user = new User(userName, email,
					EncryptUtils.encryptMD5(password.getBytes()), "未知", 10,
					"菜鸟", "", "user");
			userDao.addUser(user);
			message = "注册成功，您已登录";
			statusMessage = new StatusMessage(1, message);
			session.setAttribute("loginUser", user);
		}
		return statusMessage;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("logoutMsg", "退出成功");
		session.removeAttribute("loginUser");
		return "redirect:/index";
	}

	@RequestMapping(value = "/completeInfo", method = RequestMethod.GET)
	public String completeInfo(Model model, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);
		return "frontend/user/complete";
	}

	@RequestMapping(value = "/completeInfo", method = RequestMethod.POST)
	public String complete(Model model, HttpSession session, String gender,
			MultipartFile avatar, String motto, String city, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("loginUser");
		String avatarPath = "/opt/LearningCommunity/avatar";
		String avatarUrl = MultipartFileUtils.saveFile(avatar, avatarPath);
		userDao.update(user.getUserId(), gender, avatarUrl, motto, city);
		redirectAttributes.addFlashAttribute("userMsg", "信息完善成功");
		return "redirect:/course/courses";
	}
	
	       @RequestMapping(value = "/addFriend/{uid}", method = RequestMethod.GET)
	        public String addFriend(Model model, HttpSession session, @PathVariable int uid, RedirectAttributes redirectAttributes) {
	                User user = (User) session.getAttribute("loginUser");
	                model.addAttribute("user", user);
	                String message = null;
	                if(!userDao.ifFriend(user.getUserId(), uid)){
	                     userDao.addRel(user.getUserId(), uid);   
	                     message = "好友请求已发送";
	                }else{
	                     message = "你们已经是好友";
	                }
	                redirectAttributes.addFlashAttribute("userMsg", message);
	                return "frontend/user/center";
	        }
	       
	       /**
	        * 
	        * @param model
	        * @param session
	        * @param state 为0表示拒绝,其他为同意
	        * @param uid 对方的id
	        * @return
	        */
               @RequestMapping(value = "/validateFriend/{uid}/{state}", method = RequestMethod.GET)
               public String validateFriend(Model model, HttpSession session, @PathVariable int state, @PathVariable int uid) {
                       User user = (User) session.getAttribute("loginUser");
                       model.addAttribute("user", user);
                       if(state != 0){
                               userDao.validateFriend(uid, user.getUserId(), 1);
                       }
                       return "frontend/user/center";
               }
               
               @RequestMapping(value = "/friends", method = RequestMethod.GET)
               public String friends(Model model, HttpSession session) {
                       User user = (User) session.getAttribute("loginUser");
                       model.addAttribute("user", user);
                       List<User> friends = userDao.getFriends(user.getUserId());
                       model.addAttribute("friends",friends);
                       return "frontend/user/center";
               }
               
               @RequestMapping(value = "/info", method = RequestMethod.GET)
               public String info(Model model, HttpSession session) {
                       User user = (User) session.getAttribute("loginUser");
                       model.addAttribute("user", user);
                       List<User> friends = userDao.getValidateFriends(user.getUserId());
                       model.addAttribute("friends",friends);
                       return "frontend/user/center";
               }
               
               @RequestMapping(value = "/record/{day}", method = RequestMethod.GET)
               @ResponseBody
               public List<IntegralRecord> record(Model model, HttpSession session, @PathVariable int day) {
                       User user = (User) session.getAttribute("loginUser");
                       List<IntegralRecord> records = integralRecordOperation.getRecent(user.getEmail(), day);
                       return records;
               }
}
