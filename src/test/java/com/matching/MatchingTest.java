package com.matching;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matching.object.Job;
import com.matching.object.Worker;
import com.matching.service.JobServiceImpl;
import com.matching.service.MatchingServiceImpl;
import com.matching.service.WorkerServiceImpl;

public class MatchingTest {

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = mock(RestTemplate.class);
	}
	
	@Test
	public void testGetJobs() throws JsonParseException, JsonMappingException, IOException {	
		ObjectMapper objectMapper = new ObjectMapper();		
		Job[] jobs = objectMapper.readValue(new File("src/test/resources/json/jobs1000000.json"), Job[].class);
		
		ResponseEntity<Job[]> jobsResponse = new ResponseEntity<Job[]>(jobs, HttpStatus.ACCEPTED);
		when(restTemplate.getForEntity("http://swipejobs.azurewebsites.net/api/jobs", Job[].class)).thenReturn(jobsResponse);
		JobServiceImpl jobServiceImpl = new JobServiceImpl();
		jobServiceImpl.setRestTemplate(restTemplate);
		jobServiceImpl.getAll();
	}
	
	@Test
	public void testGetWorkers() throws JsonParseException, JsonMappingException, IOException {	
		ObjectMapper objectMapper = new ObjectMapper();		
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Worker[] workers = objectMapper.readValue(new File("src/test/resources/json/workers1000000.json"), Worker[].class);
		
		ResponseEntity<Worker[]> workersResponse = new ResponseEntity<Worker[]>(workers, HttpStatus.ACCEPTED);
		when(restTemplate.getForEntity("http://swipejobs.azurewebsites.net/api/workers", Worker[].class)).thenReturn(workersResponse);
		WorkerServiceImpl workerServiceImpl = new WorkerServiceImpl();
		workerServiceImpl.setRestTemplate(restTemplate);
		workerServiceImpl.getById(0);
	}
	
	@Test
	public void testMassiveMatching() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();		
		Job[] jobs = objectMapper.readValue(new File("src/test/resources/json/jobs1000000.json"), Job[].class);
		
		ResponseEntity<Job[]> jobsResponse = new ResponseEntity<Job[]>(jobs, HttpStatus.ACCEPTED);
		when(restTemplate.getForEntity("http://swipejobs.azurewebsites.net/api/jobs", Job[].class)).thenReturn(jobsResponse);
		JobServiceImpl jobServiceImpl = new JobServiceImpl();
		jobServiceImpl.setRestTemplate(restTemplate);
		
		ObjectMapper objectMapper2 = new ObjectMapper();		
		objectMapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Worker[] workers = objectMapper2.readValue(new File("src/test/resources/json/workers1000000.json"), Worker[].class);
		
		ResponseEntity<Worker[]> workersResponse = new ResponseEntity<Worker[]>(workers, HttpStatus.ACCEPTED);
		when(restTemplate.getForEntity("http://swipejobs.azurewebsites.net/api/workers", Worker[].class)).thenReturn(workersResponse);
		WorkerServiceImpl workerServiceImpl = new WorkerServiceImpl();
		workerServiceImpl.setRestTemplate(restTemplate);

		MatchingServiceImpl matchingServiceImpl = new MatchingServiceImpl();
		matchingServiceImpl.setJobService(jobServiceImpl);
		matchingServiceImpl.setWorkerService(workerServiceImpl);
		matchingServiceImpl.getMatchingJobs(0);
	}
	
}
