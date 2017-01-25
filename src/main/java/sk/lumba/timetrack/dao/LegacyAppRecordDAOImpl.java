package sk.lumba.timetrack.dao;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sk.lumba.timetrack.domain.TimeRecord;
import sk.lumba.timetrack.exception.LegacyApplException;

public class LegacyAppRecordDAOImpl implements RecordDAO {

	private static final Logger logger = LoggerFactory.getLogger(LegacyAppRecordDAOImpl.class);
	
	private static final String DEFAULT_LEGACY_REST_URL = "http://192.168.2.200:8080/records";
	
	private final String legacyRestUrl;
	
	public LegacyAppRecordDAOImpl(String legacyRestUrl) {
		logger.info("legacyRestUrl from env: {}", legacyRestUrl);
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
	    try {
		    TimeRecord[] result = restTemplate.getForObject(builder.build().encode().toUri(), TimeRecord[].class);
		    return Arrays.asList(result);
	    } catch (Exception e) {
	    	logger.error("Error by accesing legacy application.", e);
	    	throw new LegacyApplException(e.getMessage(), e);	    	
	    }
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

		try {
			restTemplate.postForEntity(legacyRestUrl, request , String.class);
		} catch (Exception e) {
			logger.error("Error by accesing legacy application.", e);
	    	throw new LegacyApplException(e.getMessage(), e);	    	
	    }
	}
	
}
