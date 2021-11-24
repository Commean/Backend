package eu.commean.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Crossroad have been defined")
public class NoDataFoundException extends RuntimeException {
	private String reason;
	private int code;

	public String getReason() {
		return reason;
	}

	public int getError_code() {
		return code;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setError_code(int code) {
		this.code = code;
	}

	public NoDataFoundException(String reason, int status) {
		super();
		this.reason = reason;
		this.code = status;
	}

}
