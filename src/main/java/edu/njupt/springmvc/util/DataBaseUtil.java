package edu.njupt.springmvc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * @author Administrator
 *
 */
public class DataBaseUtil {
	private static final String DEFAULT_TIME_STEMP = "yyyy-MM-dd HH:mm:ss";
	private static final Logger logger = Logger.getLogger(DataBaseUtil.class);
	/**
	 * 获取随机uuid
	 * 
	 * @return
	 */
	public static String getRandomUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		logger.info("random uuid ===> " + uuid);
		return uuid;
	}
	/**
	 * 获取当前系统时间 默认方式 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String currentTimeByDefault() {
		String current = currentTimeByUserType(DEFAULT_TIME_STEMP);
		return current;
	}
	/**
	 * 获取当前系统时间 通过自定义方式
	 * @param pattern
	 * @return
	 */
	public static String currentTimeByUserType(String pattern) {
		Date date = new Date(System.currentTimeMillis());
		String current = new SimpleDateFormat(pattern).format(date);
		logger.info("time stemp ===> " + current);
		return current;
	}
	/**
	 * 获取md5信息摘要
	 * @param msg
	 * @return
	 */
	public static String getMD5Digest(String msg) {
		StringBuilder sb = new StringBuilder(32);
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] b = digest.digest(msg.getBytes("utf-8"));
			for (byte c : b) {
				String hex = Integer.toHexString(c & 0xff);
				sb.append(hex.length() == 1? "0": "");
				sb.append(hex);
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.warn(e.getMessage(), e);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			String str = "123";
			String digest = getMD5Digest(str);
			logger.info(str + " ===> " + digest);
		}
	}
	
	
}
