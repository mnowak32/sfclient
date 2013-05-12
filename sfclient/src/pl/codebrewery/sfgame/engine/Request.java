package pl.codebrewery.sfgame.engine;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Request {

	private final static String DEFAULT_SESSION_ID = "00000000000000000000000000000000", SEPARATOR = "%3B";
	
	private int action;
	private List<String> params;
	private String sessionId;
	
	public Request(int a, String s, String... p) {
		action = a;
		sessionId = s;
		params = Arrays.asList(p);
	}

	public int getAction() {
		return action;
	}

	public String getActionStr() {
		return String.format("%03d", action);
	}
	
	public void setAction(int action) {
		this.action = action;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public String getSessionId() {
		if (StringUtils.isBlank(sessionId)) {
			return DEFAULT_SESSION_ID;
		}
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getAllParams() {
		return StringUtils.join(params, SEPARATOR);
	}
}
