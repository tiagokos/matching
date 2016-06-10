package com.matching.service;

import java.util.List;

import com.matching.object.Job;
import com.matching.object.Worker;

/**
 * Matching Service
 * 
 * @author tiago
 *
 */
public interface MatchingService {
	
	/**
	 * Retrieve recommended jobs for a worker
	 * 
	 * @param worker
	 * @return
	 */
	public List<Job> getMatchingJobs(Worker worker);

}
