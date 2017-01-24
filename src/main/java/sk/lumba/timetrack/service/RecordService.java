package sk.lumba.timetrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.lumba.timetrack.dao.RecordDAO;
import sk.lumba.timetrack.domain.TimeRecord;

@Service
public class RecordService {

	@Autowired
	private RecordDAO dao;
	
	public List<TimeRecord> getTimeRecords(long offset, int length, String email) {
		return dao.getTimeRecords(offset, length, email);
	}
	
	public void createTimeRecord(String email, String start, String end) {
		dao.createTimeRecord(email, start, end);
	}
}
