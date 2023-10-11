package jsoft.library;

import javax.servlet.ServletRequest;
import net.htmlparser.jericho.*;

public class Utilities {

	public static byte getByteParam(ServletRequest request, String name) {
		byte value = -1;
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Byte.parseByte(str_value);
		}
		return value;
	}
	
	public static short getShortParam(ServletRequest request, String name) {
		short value = -1;
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Short.parseShort(str_value);
		}
		return value;
	}
	
	public static int getIntParam(ServletRequest request, String name) {
		int value = -1;
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Integer.parseInt(str_value);
		}
		return value;
	}
	
	public static boolean getBoolParam(ServletRequest request, String name) {
		if(Utilities.getIntParam(request, name) != 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * mã hóa unicode thành html
	 * @param str_unicode
	 * @return
	 */
	public static String encode(String str_unicode) {
		return CharacterReference.encode(str_unicode);
	}
	
	public static String decode(String str_html) {
		return CharacterReference.decode(str_html);
	}
	
	
}
