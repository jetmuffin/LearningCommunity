package com.lst.lc.web.service;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.color.RandomColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.ConfigurableFilterFactory;
import com.github.bingoohuang.patchca.filter.library.AbstractImageOp;
import com.github.bingoohuang.patchca.filter.library.WobbleImageOp;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.text.renderer.TextRenderer;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.lst.lc.web.bean.MyCaptcha;

@Service
public class CaptchaHandler {

	private ConfigurableCaptchaService configurableCaptchaService = null;
	private ColorFactory colorFactory = null;
	private RandomFontFactory fontFactory = null;
	private RandomWordFactory wordFactory = null;
	private TextRenderer textRenderer = null;
	private MyCaptcha myCaptcha = null;
	
	public CaptchaHandler() {
		super();
	}

	public MyCaptcha getCatpcha(){
		init();
		Captcha captcha = configurableCaptchaService.getCaptcha();  
		String code = captcha.getChallenge();
		BufferedImage bufferedImage = captcha.getImage();  
		myCaptcha.setCode(code);
		myCaptcha.setBufferedImage(bufferedImage);
		return this.myCaptcha;
	}
	
	public void init(){
		myCaptcha = new MyCaptcha();
        configurableCaptchaService = new ConfigurableCaptchaService();  
        // 颜色创建工厂,使用一定范围内的随机色  
        colorFactory = new RandomColorFactory();  
        configurableCaptchaService.setColorFactory(colorFactory);  
        // 随机字体生成器  
        fontFactory = new RandomFontFactory();  
        fontFactory.setMaxSize(32);  
        fontFactory.setMinSize(28);  
        configurableCaptchaService.setFontFactory(fontFactory);  
        // 随机字符生成器,去除掉容易混淆的字母和数字,如o和0等  
        wordFactory = new RandomWordFactory();  
        wordFactory.setCharacters("abcdefghkmnpqstwxyz23456789");  
        wordFactory.setMaxLength(5);  
        wordFactory.setMinLength(4);  
        configurableCaptchaService.setWordFactory(wordFactory);  
        // 自定义验证码图片背景  
        CustomBackgroundFactory backgroundFactory = new CustomBackgroundFactory();  
        configurableCaptchaService.setBackgroundFactory(backgroundFactory);  
        // 图片滤镜设置  
        ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();  
        List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();  
        WobbleImageOp wobbleImageOp = new WobbleImageOp();  
        wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);  
        wobbleImageOp.setxAmplitude(2.0);  
        wobbleImageOp.setyAmplitude(1.0);  
        filters.add(wobbleImageOp);  
        filterFactory.setFilters(filters);  
        configurableCaptchaService.setFilterFactory(filterFactory);  
        // 文字渲染器设置  
        textRenderer = new BestFitTextRenderer();  
        textRenderer.setBottomMargin(3);  
        textRenderer.setTopMargin(3);  
        configurableCaptchaService.setTextRenderer(textRenderer);  
        // 验证码图片的大小  
        configurableCaptchaService.setWidth(80);  
        configurableCaptchaService.setHeight(35);  
	}
	
}
