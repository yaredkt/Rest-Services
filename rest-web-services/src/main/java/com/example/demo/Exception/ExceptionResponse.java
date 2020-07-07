package com.example.demo.Exception;

import java.util.Date;

public class ExceptionResponse {
	
	private String traceid;
	
	private Date timeStamp;
	
	//private String errorCode;
	
	private String message;
	
	
	
	private String messageDetail;

	public ExceptionResponse(String traceid, Date timeStamp, String message, String messageDetail) {
		super();
		this.traceid = traceid;
		this.timeStamp = timeStamp;
		this.message = message;
		this.messageDetail = messageDetail;
	}
	
	public String getTraceid() {
		
		return traceid;
	}
	
	public void setTraceId(String traceid) {
		this.traceid = traceid;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		message = message;
	}

	public String getMessageDetail() {
		return messageDetail;
	}

	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}
	
	
	
	
	
	

}
