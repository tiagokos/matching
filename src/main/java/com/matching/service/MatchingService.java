package com.matching.service;

import java.util.List;

import com.matching.object.Job;

/**
 * Matching Service
 * 
 * @author tiago
 *
 */
public interface MatchingService {
	
	/**
	 * Retrieve recommended jobs for a worker id
	 * 
	 * @param workerId
	 * @return
	 */
	public List<Job> getMatchingJobs(int workerId);

}
