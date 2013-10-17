package pl.codebrewery.sfgame.interfaces;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	static String hash(String in) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] dig = md.digest(in.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : dig) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
}
