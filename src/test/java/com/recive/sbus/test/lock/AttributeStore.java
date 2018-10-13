package test.java.com.recive.sbus.test.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AttributeStore {
	private final Map<String, String> attributes = new HashMap<String, String>();

	public synchronized boolean userLocationMatchs(String name, String reg) {
		String location = attributes.get(name);
		if (null == location) {
			return false;
		} else {
			return Pattern.matches(reg, location);
		}
	}
}
