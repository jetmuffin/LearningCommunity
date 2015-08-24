package com.lst.lc.utils;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JspUtil {
        
        private static final long ONE_MINUTE = 60000L;  
        private static final long ONE_HOUR = 3600000L;  
        private static final long ONE_DAY = 86400000L;  
        private static final long ONE_WEEK = 604800000L;  
      
        private static final String ONE_SECOND_AGO = "秒前";  
        private static final String ONE_MINUTE_AGO = "分钟前";  
        private static final String ONE_HOUR_AGO = "小时前";  
        private static final String ONE_DAY_AGO = "天前";  
        private static final String ONE_MONTH_AGO = "月前";  
        private static final String ONE_YEAR_AGO = "年前";  
     
      
        public static String format(Date date) {  
            long delta = new Date().getTime() - date.getTime();  
            if (delta < 1L * ONE_MINUTE) {  
                long seconds = toSeconds(delta);  
                return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;  
            }  
            if (delta < 45L * ONE_MINUTE) {  
                long minutes = toMinutes(delta);  
                return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;  
            }  
            if (delta < 24L * ONE_HOUR) {  
                long hours = toHours(delta);  
                return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;  
            }  
            if (delta < 168L * ONE_HOUR) {  
                long days = toDays(delta);  
                return (days <= 0 ? 1 : days) + ONE_DAY_AGO;  
            }  
            else {  
                return DateUtils.getDateString(date);
            }  
        }  
      
        private static long toSeconds(long date) {  
            return date / 1000L;  
        }  
      
        private static long toMinutes(long date) {  
            return toSeconds(date) / 60L;  
        }  
      
        private static long toHours(long date) {  
            return toMinutes(date) / 60L;  
        }  
      
        private static long toDays(long date) {  
            return toHours(date) / 24L;  
        }  
      
        private static long toMonths(long date) {  
            return toDays(date) / 30L;  
        }  
      
        private static long toYears(long date) {  
            return toMonths(date) / 365L;  
        }  
        
        public static String formatForImage(String date) throws Exception{
                return format(formatString(getStrTime(date)));
        }

        /**
         * 获取年月日字符串
         * 
         * @return
         */
        public static String getDateNum() {
                Calendar c = Calendar.getInstance();
                String year = Integer.toString(c.get(Calendar.YEAR));
                String month = Integer.toString(c.get(Calendar.MONTH));
                String date = Integer.toString(c.get(Calendar.DATE));
                return year + month + date;
        }

        /**
         * 获取年月日时分秒字符串
         * 
         * @return
         */
        public static String getSecNum() {
                Calendar c = Calendar.getInstance();
                String year = Integer.toString(c.get(Calendar.YEAR));
                String month = Integer.toString(c.get(Calendar.MONTH));
                String date = Integer.toString(c.get(Calendar.DATE));
                String hour = Integer.toString(c.get(Calendar.HOUR));
                String minute = Integer.toString(c.get(Calendar.MINUTE));
                String second = Integer.toString(c.get(Calendar.SECOND));
                return year + month + date + hour + minute + second;
        }

        /**
         * 将时间按照格式进行转换
         * 
         * @param date
         *                时间
         * @param format
         *                时间格式
         * @return
         */
        public static String formatDate(Date date, String format) {
                String result = "";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                if (date != null) {
                        result = sdf.format(date);
                }
                return result;
        }

        public static String formatDate(Date date) {
                String result = "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (date != null) {
                        result = sdf.format(date);
                }
                return result;
        }

        /**
         * 将String时间转换成某时间格式
         * 
         * @param str
         *                时间
         * @param format
         *                格式
         * @return
         * @throws Exception
         */
        public static Date formatString(String str, String format)
                        throws Exception {
                if ((str == null) || (str.equals(""))) {
                        return null;
                }
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(str);
        }
        
        public static Date formatString(String str)
                        throws Exception {
                if ((str == null) || (str.equals(""))) {
                        return null;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.parse(str);
        }

        /**
         * 获取当前日期时间，24小时制
         * 
         * @return String类型的时间
         * @throws Exception
         */
        public static String getCurrentDateStr() {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                return sdf.format(date);
        }

        public static String getCurrentDateMS() {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                return sdf.format(date);
        }

        /**
         * 获取当前时间的前一天
         * 
         * @return
         */
        public static String getPereviousDayMS() {
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                date = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                return sdf.format(date);
        }

        /**
         * 获取距离今日的时间统计
         */
        public static String getLastTime(String lastTime) {
                Long lastTimeL = Long.parseLong(lastTime);
                Long nowTime = new Date().getTime();
                Long range = nowTime - lastTimeL;
                int days = (int) (range / 3600 / 1000 / 24);
                if (days < 1) {
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                        "yyyy-MM-dd HH:mm:ss");
                        String date = sdf.format(new Date(lastTimeL));
                        return date;
                } else if (days < 30) {
                        return days + "天前";
                } else {
                        return "很久以前";
                }
        }

        public static String getStrTime(String strTime) {
                String year = strTime.substring(0, 4);
                String month = strTime.substring(4, 6);
                String day = strTime.substring(6, 8);
                String hour = strTime.substring(8, 10);
                String minute = strTime.substring(10, 12);
                String second = strTime.substring(12, 14);
                return year + "-" + month + "-" + day + " " + hour + ":"
                                + minute + ":" + second;
        }

        /**
         * 保留两位小数
         * 
         * @param str
         * @return
         */
        public static String cutLength(String str) {
                if ((str == null) || (str.length() == 0))
                        return String.valueOf(0);
                char[] ch = str.toCharArray();
                for (int i = 0; i < ch.length; i++) {
                        if (ch[i] == '.') {
                                if (i + 3 <= ch.length)
                                        return str.substring(0, i + 3);
                                else
                                        return str.substring(0, ch.length);
                        }
                }
                return String.valueOf(0);
        }
        
        /**
         * 保留4位小数
         * 
         * @param str
         * @return
         */
        public static String cutLength4(String str) {
                if ((str == null) || (str.length() == 0))
                        return String.valueOf(0);
                char[] ch = str.toCharArray();
                for (int i = 0; i < ch.length; i++) {
                        if (ch[i] == '.') {
                                if (i + 5 <= ch.length)
                                        return str.substring(0, i + 5);
                                else
                                        return str.substring(0, ch.length);
                        }
                }
                return String.valueOf(0);
        }

        public static String getFileType(String fileName) {
                int index = fileName.lastIndexOf(".");
                return fileName.substring(index + 1);
        }

        public static String urlEncode(String url) {
                return URLEncoder.encode(url);
        }
}
