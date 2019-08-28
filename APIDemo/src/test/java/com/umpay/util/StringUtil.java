package com.umpay.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class StringUtil {

	public static final String STR_FRONT = "front"; // 字符串前面
	public static final String STR_BACK = "back"; // 字符串后面
	public static String doSub(String line,String begin,String last){
		int beginindex=line.indexOf(begin)+begin.length();
		String tmp=line.substring(beginindex);
		return line.substring(beginindex,tmp.lastIndexOf(last)+beginindex);
	}
	
	public static int countChar(String old,String rex){
		String newStr=old.replace(rex, "");
		return (old.length()-newStr.length())/rex.length();
	}
	
	
	/**
	 * 获取字符串字节长度
	 * 
	 * @param str
	 * @return
	 */
	public static int getLength(String str) {
		if (null == str)
			return 0;
		try {
			return str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持GBK编码");
		}
	}

	/**
	 * 
	 * description: 去除字符串前后的空格，若<code>str</code>为<code>null</code>,返回空串
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str == null ? "" : str.trim();
	}

	/**
	 * 
	 * description:去除字符串对象空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(Object obj) {
		return obj == null ? "" : trim(obj.toString());
	}

	/**
	 * 
	 * description: 判断<code>str</code>是否为空串或<code>null</code>
	 * 
	 * @param str
	 * @return 若为空串或<code>null</code>返回<code>true</code>,否则返回<code>false</code>
	 */
	public static boolean isEmpty(String str) {
		str = trim(str);
		if ("".equals(str))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * description: 判断<code>str</code>是否为空串或<code>null</code>
	 * 
	 * @param str
	 * @return 若为空串或<code>null</code>返回<code>false</code>,否则返回<code>true</code>
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 
	 * description: 去掉obj中string类型字段值的空格，若obj为String,则将obj去空格后返回
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object trimObjectFields(Object obj) {
		if (obj == null)
			return "";
		Class cls = obj.getClass();
		if (obj instanceof String)
			return trim(obj.toString());
		Field[] fields = cls.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (!Modifier.isStatic(field.getModifiers()) && Modifier.isPrivate(field.getModifiers())) {
					Object value = field.get(obj);
					if (value instanceof String && null != value) {
						String resultStr = StringUtil.trim((String) value);
						String name = field.getName();
						String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
						Method setMethod = obj.getClass().getMethod(setMethodName, String.class);
						setMethod.setAccessible(true);
						setMethod.invoke(obj, resultStr);
					}
				}

			}
		} catch (Exception e) {
		}
		return obj;
	}

	/**
	 * 
	 * description: 去掉obj中string类型字段值的空格，若obj为String,则将obj去空格后返回
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object trimObjectFieldsOptional(Object obj) {
		if (obj == null)
			return "";
		Class cls = obj.getClass();
		if (obj instanceof String)
			return trim(obj.toString());
		Field[] fields = cls.getDeclaredFields();
		try {
			for (Field field : fields) {
				String type = field.getType().getSimpleName().toString();
				String name = field.getName();
				if ("String".equals(type)) {
					try {
						String getMethod = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
						Method get = cls.getMethod(getMethod);
						String value = "";
						Object objvalue = get.invoke(obj);
						if (null != objvalue) {
							value = trim(objvalue.toString());
						}
						String setMethod = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
						Method set = cls.getMethod(setMethod, String.class);
						set.invoke(obj, value);
					} catch (Exception e) {

					}
				}
			}
		} catch (Exception e) {
		}
		return obj;
	}

	/**
	 * 去掉map中string类型字段值的空格，若value为String,则将obj去空格后返回
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map trimMap(Map map) {
		if (map == null)
			return null;
		try {
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if (value instanceof String) {
					String valueStr = StringUtil.trim((String) value);
					map.put(key, valueStr);
				} else {
					if (value instanceof Map) {
						value = trimMap((Map) value);
					} else {
						value = StringUtil.trimObjectFields(value);
						map.put(key, value);
					}
				}
			}
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * 在原串的前面或者后面添加字符
	 * 
	 * @param str
	 *            原字符串
	 * @param len
	 *            生产的字符串长度
	 * @param fillChar
	 *            填充的字符
	 * @param position
	 *            填充的位置，默认为原串的前面
	 * @return
	 * @see:
	 */
	public static String replenish(String str, int len, char fillChar, String position) {
		str = trim(str);
		if (str.length() >= len) {
			return str;
		}

		String addStr = "";
		for (int i = str.length(); i < len; i++) {
			addStr += fillChar;
		}

		if (position.equals(STR_FRONT)) {
			str = addStr + str;
		} else {
			str = str + addStr;
		}

		return str;

	}

	public static String subString(String str, int maxLength) {
		return str.substring(0, maxLength);
	}

	/**
	 * 判断字符串长度是否大于某个值
	 */
	public static boolean isOverMaxLength(String str, int maxLength) {
		if (str.getBytes().length > maxLength) {
			return true;
		}
		return false;
	}

	/**
	 * 根据路径获取文件名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		path = trim(path);
		if ("".equals(path)) {
			return path;
		}
		String srcFileName = "";
		int post1 = path.lastIndexOf("/");
		int post2 = path.lastIndexOf("\\");
		if (post1 < post2) {
			srcFileName = path.substring(post2 + 1);
		} else {
			srcFileName = path.substring(post1 + 1);
		}
		return srcFileName;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("[0]*$", "");// 去掉多余的0
			if (String.valueOf(s.charAt(s.length() - 1)).equals(".")) {
				s = s.substring(0, s.length() - 1);
			}
			// s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;
	}

	/**
	 * desc:过滤特殊字符，例如%，<
	 * <p>
	 * 创建人：chengliuyun , Feb 21, 2013 4:30:42 PM
	 * </p>
	 * 
	 * @param input
	 * @return
	 */
	public static String filterString(String input) {
		if (input == null || "".equals(input.trim()) || "null".equals(input.trim())) {
			return "";
		}
		// 去掉所有html元素, sql 元素
		String ret = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "")
				.replace("%", "").replaceAll(" and ", "").replaceAll(" AND ", "").replaceAll(" or ", "")
				.replaceAll(" OR ", "");
		return ret;
	}

	/**
	 * Integer转成字符串
	 * 
	 * @author 肖明
	 * @param value
	 *            Integer值
	 * @return String值
	 */
	public static String integer2String(Integer value) {
		return value == null ? null : value + "";
	}

	/**
	 * 字符串转成Integer
	 * 
	 * @author 肖明
	 * @param value
	 *            String值
	 * @return Integer值
	 */
	public static Integer string2Integer(String value) {
		return StringUtil.isEmpty(value) ? null : Integer.parseInt(StringUtil.trim(value));
	}

	/**
	 * 获得一个UUID
	 * 
	 * @author 肖明
	 * @return UUID
	 */
	public static String getUUID() {
		String str = UUID.randomUUID().toString();// 标准的UUID格式为：xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxx(8-4-4-4-12)
		return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);// 去掉"-"符号
	}

	/**
	 * 将字符串用分隔符断裂成字符串数组，代替String.split方法
	 * 
	 * @author 肖明 2013-5-15
	 * @param s
	 *            原字符串
	 * @param c
	 *            分隔字符
	 * @return 结果数组
	 */
	public static String[] split(String s, char c) {
		int ln = s.length();
		int i = 0;
		int cnt = 1;
		while ((i = s.indexOf(c, i)) != -1) {
			++cnt;
			++i;
		}
		String[] res = new String[cnt];
		i = 0;
		int b = 0;
		while (b <= ln) {
			int e = s.indexOf(c, b);
			if (e == -1)
				e = ln;
			res[(i++)] = s.substring(b, e);
			b = e + 1;
		}
		return res;
	}

	/**
	 * 将数组用分隔符连接成新字符串
	 * 
	 * @author 肖明 2013-5-16
	 * @param strs
	 *            字符串数组
	 * @param sep
	 *            分隔符
	 * @return 结果字符串
	 */
	public static String join(String[] strs, String sep) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			res.append(strs[i] + sep);
		}
		return res.substring(0, res.length() - sep.length());
	}

	/**
	 * 随机产生指定位数的数字字符串
	 * 
	 * @param count
	 * @return
	 */
	public static String randomGenerateFigure(int count) {

		String[] s = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			int index = random.nextInt(10);
			sb.append(s[index]);
		}
		return sb.toString();
	}

	public static String[] randomGenerateFigureWithDiff() {
		String[] twoNO = new String[2];
		while (true) {
			twoNO[0] = randomGenerateFigure(9);
			twoNO[1] = randomGenerateFigure(9);
			if (twoNO[0] != twoNO[1]) {
				break;
			}
		}
		return twoNO;
	}

	/**
	 * desc:Int——>String 前补零
	 * <p>
	 * 创建人：王世院, Apr 12, 2013 9:52:14 AM
	 * </p>
	 * 
	 * @param length
	 * @param param
	 * @return
	 */
	public static String intToString(int length, int param) {
		String zero = "";
		for (int i = 0; i < length; i++) {
			zero = zero + "0";
		}
		zero = zero + param;
		return zero.substring(zero.length() - length, zero.length());
	}

	/**
	 * 关键字用* 替换 规则： value <= 6 替换为****** 6 > value <= 12 后四位保留，其他为* value > 12
	 * 前6位保留 中间* 后4位保留
	 * 
	 * @param value
	 * @return
	 */
	public static String replaceCharByStar(String value) {

		if (StringUtil.isEmpty(value) || "null".equals(value)) {
			return "";
		} else {
			if (value.length() <= 6) { // value <= 6 替换为******
				return lStr("", '*', value.length());
			} else if (value.length() > 6 && value.length() <= 12) { // 后四位保留，其他为*
				int valLen = value.length();
				String replaceHeadStr = value.substring(value.length() - 4);
				return replaceHeadStr = lStr(replaceHeadStr, '*', valLen);
			} else if (value.length() > 12) { // 前6位保留 中间* 后4位保留
				String replaceHeadStr = value.substring(0, 6);
				String replaceTailStr = value.substring(value.length() - 4);
				String dimStr = lStr("", '*', value.length() - 10);
				return replaceHeadStr + dimStr + replaceTailStr;
			} else {
				return "";
			}
		}
	}

	/*
	 * 左补长char
	 */
	public static String lStr(String s, char ch, int width) {
		if (s.length() < width) { // 需要前面补'0'
			while (s.length() < width)
				s = ch + s;
		} else { // 需要将高位丢弃
			s = s.substring(s.length() - width, s.length());
		}
		return s;
	}

	/**
	 * desc:取某个符号前或者某个符号后的字符串
	 * <p>
	 * 创建人：linfangjian , Sep 23, 2015 4:06:37 PM
	 * </p>
	 * 
	 * @param value
	 *            字符串
	 * @param punctuation
	 *            截取符号
	 * @param direction
	 *            符号前或者符号后 front（前） back（后）
	 * @return characterSegmentation("linfangjian@umpay.com","@","front") return
	 *         linfangjian
	 */
	public static String characterSegmentation(String value, String punctuation, String direction) {
		if (StringUtil.isEmpty(value) || "".equals(value)) {
			return "";
		} else {
			String ret;
			if (direction.equals(STR_FRONT)) {
				ret = value.substring(0, value.indexOf(punctuation) - 1);
			} else {
				ret = value.substring(value.indexOf(punctuation) + 1, value.length());
			}
			return ret;
		}
	}

	/**
	 * desc:脱敏处理，前6后4
	 * <p>
	 * 创建人：xuhuanchao , 2016-11-25 下午6:50:57
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String desensitization(String str) {
		return str.substring(0, 6) + "******" + str.substring(str.length() - 4);
	}

	/**
	 * desc:脱敏处理，保留前before位，后after位，中间位n位*
	 * <p>
	 * 创建人：xuhuanchao , 2016-11-25 下午6:50:57
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String desensitization(String str, int before, int after, int n) {
		if (before == 0 && after != 0) {
			// 保留后面after位，前面n个*
			return lStr("", '*', n) + str.substring(str.length() - after);
		} else if (before != 0 && after == 0) {
			// 保留前面before位，后面为n个*
			return str.substring(0, before) + lStr("", '*', n);
		} else if (before != 0 && after != 0) {
			// 保留前before位，后after位，中间位n位*
			return str.substring(0, before) + lStr("", '*', n) + str.substring(str.length() - after);
		} else {
			// 不保留，n位*
			return lStr("", '*', n);
		}

	}

	/**
	 * 
	 * desc:字符流转为16进制字符串
	 * <p>
	 * 创建人：zhaohonglin , 2017-4-26 下午5:00:53
	 * </p>
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {

		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1)

				hs = hs + "0" + stmp;

			else

				hs = hs + stmp;

		}

		return hs.toUpperCase();

	}

	/**
	 * 
	 * desc:读取2进制流
	 * <p>
	 * 创建人：zhaohonglin , 2017-4-26 下午5:27:04
	 * </p>
	 * 
	 * @param in
	 * @return
	 */
	public static byte[] readInputStream(InputStream in) {
		if (in == null)
			return null;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			byte[] tmp = new byte[512];
			int l;
			while ((l = in.read(tmp)) != -1) {
				buffer.write(tmp, 0, l);
			}
		} catch (Exception e) {
			return null;
		} finally {
			// IoUtils.close(in);
		}
		return buffer.toByteArray();
	}

	/**
	 * 
	 * desc:字符串转为字节流
	 * <p>
	 * 创建人：zhaohonglin , 2017-4-26 下午5:59:22
	 * </p>
	 * 
	 * @param s
	 * @return
	 */
	public static final byte[] hexStrToBytes(String s) {
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	/**
	 * 获取字符串str中包含target的个数
	 * 
	 * @param str
	 * @param target
	 * @author renjie
	 * @return
	 */
	public static int countStr(String str, String target) {
		return (str.length() - str.replace(target, "").length());
	}
	/**
	* 将String转成BCD码
	* 
	* @param s
	* @return
	*/
	public static byte [] StrToBCDBytes(String s)
	{

//		while(s.length()<256){
//			s="0" + s;
//		}
		if (s.length() % 2 != 0 ) {
			s = "0" + s;
		}
		
		System.out.println(s.length());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i += 2) {
			int high = cs[i] - 48;
			int low = cs[i + 1] - 48;
			baos.write(high << 4 | low);
		}

		return baos.toByteArray();
	}
	/**
	* 将BCD码转成String
	* 
	* @param b
	* @return
	*/
	public static String bcdToString(byte [] b){
		StringBuffer sb = new StringBuffer ();
		for (int i = 0;i < b.length;i++ )
		{
		int h = ((b [i] & 0xff) >> 4) + 48;
		sb.append ((char) h);
		int l = (b [i] & 0x0f) + 48;
		sb.append ((char) l);
		}
		return sb.toString () ;
	}
}
