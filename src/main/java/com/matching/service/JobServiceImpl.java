package com.matching.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.matching.object.Job;

/**
 * Job Service Implementation
 * 
 * @author tiago
 *
 */
@Service
public class JobServiceImpl implements JobService {

	private static final String REST_URL = "http://swipejobs.azurewebsites.net/api/jobs";

	@Autowired
	private RestTemplate restTemplate;
	
	public List<Job> getAll() {
		List<Job> jobs = null;
		
		ResponseEntity<Job[]> jobsResponse = restTemplate.getForEntity(REST_URL, Job[].class);
		if (jobsResponse != null && jobsResponse.getBody() != null) {
			jobs = Arrays.asList(jobsResponse.getBody());
		}
		
		return jobs;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
}
