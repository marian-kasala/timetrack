package sk.lumba.timetrack.dao;

import java.util.List;

import sk.lumba.timetrack.domain.TimeRecord;

public interface RecordDAO {

	public List<TimeRecord> getTimeRecords(long offset, int length, String email);
	public void createTimeRecord(String email, String start, String end);
	
}
