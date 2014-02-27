package pl.codebrewery.sfgame.engine;

import java.util.Arrays;

public class Response {

	private boolean error;
	private int code;
	private String[] parts;
	private boolean debug;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Response withError(boolean error) {
		this.error = error;
		return this;
	}
	
	public String[] getParts() {
		return parts;
	}

	public void setParts(String[] parts) {
		this.parts = parts;
	}

	public Response withParts(String[] parts) {
		this.parts = parts;
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Response withCode(int code) {
		this.code = code;
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("Response [error=%s, code=%s, parts=%s]", error, code, Arrays.toString(parts));
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isDebug() {
		return debug;
	}
}
