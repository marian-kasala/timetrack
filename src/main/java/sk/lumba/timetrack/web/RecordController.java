package sk.lumba.timetrack.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sk.lumba.timetrack.service.RecordService;

@Controller
@RequestMapping("/records")
public class RecordController {

	@Autowired
	RecordService recordService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String displayRecordsPage() {
		return "records";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveNewRecord(String start, String end, String email) {
		recordService.createTimeRecord(email, start, end);

		return "records";
	}
	
}
