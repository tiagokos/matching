package com.matching;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matching.object.Job;
import com.matching.object.Worker;
import com.matching.service.JobServiceImpl;
import com.matching.service.MatchingServiceImpl;
import com.matching.service.WorkerServiceImpl;

/**
 * Integration tests
 * 
 * @author tiago
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/mvc-dispatcher-servlet-test.xml" })
@WebAppConfiguration
public class MatchingIntegrationTest {

	private MockMvc mockMvc;
	private RestTemplate restTemplate;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		restTemplate = mock(RestTemplate.class);
	}

	@Test
	public void testEmptyCall() throws Exception {
		mockMvc.perform(get("/matches")).andExpect(status().isNotFound());
	}

	@Test
	public void testValidWorkerCall() throws Exception {
		mockMvc.perform(get("/matches/1")).andExpect(status().isOk());
	}

	@Test
	public void testInvalidWorkerCall() throws Exception {
		mockMvc.perform(get("/matches/1000")).andExpect(status().isNotFound());
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
