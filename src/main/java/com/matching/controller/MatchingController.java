package com.matching.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matching.object.Job;
import com.matching.service.MatchingService;

/**
 * Matching Controller
 * 
 * @author tiago
 *
 */
@RestController
public class MatchingController {

	@Autowired
	private MatchingService matchingService;

	@RequestMapping(value = "/matches/{workerId}", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getMatches(@PathVariable("workerId") int workerId) {
		// Delegates work to service
		List<Job> jobs = matchingService.getMatchingJobs(workerId);

		HttpStatus httpStatus = HttpStatus.OK;
		if (jobs == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<List<Job>>(jobs, httpStatus);
	}

}
