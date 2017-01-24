package sk.lumba.timetrack.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sk.lumba.timetrack.domain.TimeRecord;

public class LegacyAppRecordDAOImpl implements RecordDAO {

	private static final String DEFAULT_LEGACY_REST_URL = "http://192.168.2.200:8080/records";
	
	private final String legacyRestUrl;
	
	public LegacyAppRecordDAOImpl(String legacyRestUrl) {
		this.legacyRestUrl = (legacyRestUrl != null) ? legacyRestUrl : DEFAULT_LEGACY_REST_URL;
	}
	
	@Override
	public List<TimeRecord> getTimeRecords(long offset, int length, String email) {
		RestTemplate restTemplate = new RestTemplate();
	    
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(legacyRestUrl)
	    		.queryParam("offset", String.valueOf(offset))
	    		.queryParam("length", String.valueOf(length));
	    
	    if (email != null) {
	    	builder.queryParam("email", email);
	    }
	    
	    TimeRecord[] result = restTemplate.getForObject(builder.build().encode().toUri(), TimeRecord[].class);
	    return Arrays.asList(result);
	}

	@Override
	public void createTimeRecord(String email, String start, String end) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("email", email);
		map.add("start", start);
		map.add("end", end);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		restTemplate.postForEntity(legacyRestUrl, request , String.class);
	}

}
