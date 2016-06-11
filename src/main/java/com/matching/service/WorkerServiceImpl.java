package com.matching.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.matching.object.Worker;

/**
 * Worker Service Implementation
 * 
 * @author tiago
 *
 */
@Service
public class WorkerServiceImpl implements WorkerService {

	private static final String REST_URL = "http://swipejobs.azurewebsites.net/api/workers";

	@Autowired
	private RestTemplate restTemplate;

	public Worker getById(int workerId) {
		Worker worker = null;

		// Call REST service for workers
		ResponseEntity<Worker[]> workersResponse = restTemplate.getForEntity(REST_URL, Worker[].class);

		if (workersResponse != null && workersResponse.getBody() != null) {
			// Gets body content
			Worker[] workers = workersResponse.getBody();
			// Filter by worker Id
			Optional<Worker> matchingWorker = Arrays.stream(workers).filter(w -> w.getUserId() == workerId).findAny();
			// Return matched worker
			worker = matchingWorker.orElse(null);
		}

		return worker;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
