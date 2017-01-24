package sk.lumba.timetrack.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import sk.lumba.timetrack.domain.TimeRecord;

public class LocalRecordDAOImpl implements RecordDAO {

	private List<TimeRecord> records = new ArrayList<>();
	
	@PostConstruct
	private void initialize() {
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
		records.add(new TimeRecord(new Date(), new Date(), "marian.kasala.mk@gmail.com"));
	}
	
	@Override
	public List<TimeRecord> getTimeRecords(long offset, int length, String email) {
		// TODO implement filter logic
		return records;
	}

	@Override
	public void createTimeRecord(String email, String start, String end) {
		records.add(new TimeRecord(new Date(), new Date(), email));
	}

}
