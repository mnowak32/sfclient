package pl.codebrewery.sfgame.engine;

import java.util.Arrays;

public class Response {

	private boolean error;
	private int code;
	private String[] parts;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String[] getParts() {
		return parts;
	}

	public void setParts(String[] parts) {
		this.parts = parts;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return String.format("Response [error=%s, code=%s, parts=%s]", error, code, Arrays.toString(parts));
	}
}
