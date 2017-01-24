package sk.lumba.timetrack.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import sk.lumba.timetrack.json.JsonDateSerializer;

public class TimeRecord {

	//@JsonFormat(pattern="dd.MM.yyyy HH:mm")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date start;
	
	//@JsonFormat(pattern="dd.MM.yyyy HH:mm")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date end;
	
	private String email;
	
	public TimeRecord() {
		
	}
	
	public TimeRecord(Date start, Date end, String email) {
		this.start = start;
		this.end = end;
		this.email = email;
	}
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
