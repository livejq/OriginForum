package cn.justquiet.Utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ConvertUtils {

	// 获取存入数据库显示格式时间
	public static String getTime() {
		DateFormat simpledate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return simpledate.format(date);
	}
	// 获取外部显示格式时间
		public static String getTime(Date date) {
			DateFormat simpledate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			return simpledate.format(date);
		}
	//表中时间格式转换
	public static String getJQTime(Timestamp timestamp) {
		DateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		Date time = new Date(timestamp.getTime());//java.util.Date
		String date = simpleDateFormat.format(time);
		return date;
	}
	
	//通过数据表获取截止时间
	public static String getCutoffTime(Timestamp timestamp, int deadline) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");   
		Date time = new Date(timestamp.getTime());
		long afterTime = (time.getTime()/1000)+deadline*24*60*60;   
		time.setTime(afterTime*1000);   
		return format.format(time); 
	}
	//一发布直接获取截止时间
	public static String getCutoffTimeDirect(Date date, int deadline) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");   
		long afterTime = (date.getTime()/1000)+deadline*24*60*60;   
		date.setTime(afterTime*1000);   
		return format.format(date); 
	}
	public static boolean isTaskOk(String cutofftime) {
		String pattern = "yyyy-MM-dd HH:mm";
		Date ctime = null;
		Date ntime = null;
		try {
			ctime = new SimpleDateFormat(pattern).parse(cutofftime);
			ntime = new SimpleDateFormat(pattern).parse(getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctime.getTime()<ntime.getTime()?true:false;
	}
	// 截取字符串
	public static String getLong(String str, int end) {
		String result = "";
		if (str.length() > end) {
			result = str.substring(0, end);
			result += "...";
		} else {
			result = str;
		}

		return result;
	}
	
	//取得指定位随机数字符串
	public static String getRandom(int length){
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

}
