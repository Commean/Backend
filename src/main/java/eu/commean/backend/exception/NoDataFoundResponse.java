package eu.commean.backend.exception;

import org.springframework.http.HttpStatus;

public class NoDataFoundResponse {

	private int code;
	private String reason;

	public NoDataFoundResponse(String reason, int code) {
		super();
		this.code = code;
		this.reason = reason;

	}

	public String getReason() {
		return reason;
	}

	public int getCode() {
		return code;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
