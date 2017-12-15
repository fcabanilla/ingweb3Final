package ar.com.magm.ti.service;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author magm
 *
 */
public class SimpleResponse implements Serializable {
	private static final long serialVersionUID = 6229322065137629670L;

	private int code;

	private String message;

	private String devMessage;

	public String getDevMessage() {
		String r = devMessage;
		if (tr != null) {
			final StringWriter sw = new StringWriter();
			final PrintWriter pw = new PrintWriter(sw, true);
			tr.printStackTrace(pw);
			r = sw.getBuffer().toString().replaceAll("\\n", String.format("%n")).replaceAll("\\t", String.format("\t"));
		}
		return r;
	}

	public void setDevMessage(String devMessage) {
		this.devMessage = devMessage;
	}

	public SimpleResponse() {
	}

	public SimpleResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	@JsonIgnore
	private Throwable tr;

	public SimpleResponse(int code, Throwable tr) {
		super();
		this.code = code;
		this.tr = tr;
	}

	public SimpleResponse(int code, String message, String devMessage) {
		super();
		this.code = code;
		this.message = message;
		this.devMessage = devMessage;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		String r = message;
		if (tr != null) {
			r = tr.getMessage();
		}
		return r;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
