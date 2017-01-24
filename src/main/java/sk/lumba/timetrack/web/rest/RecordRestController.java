package sk.lumba.timetrack.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sk.lumba.timetrack.domain.TimeRecord;
import sk.lumba.timetrack.service.RecordService;

@RestController
@RequestMapping("/rest/records")
public class RecordRestController {
	
	@Autowired
	RecordService recordService;
		
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public List<TimeRecord> records(
			@RequestParam(value="offset", defaultValue="0") long offset,
			@RequestParam(value="length", defaultValue="10") int length,
			@RequestParam(value="email", required=false) String email) {
		
		return recordService.getTimeRecords(offset, length, email);
	}
		
}
