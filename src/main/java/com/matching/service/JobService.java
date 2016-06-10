package com.matching.service;

import java.util.List;

import com.matching.object.Job;

/**
 * Job Service Interface
 * 
 * @author tiago
 *
 */
public interface JobService {
	
	/**
	 * Retrieve all jobs
	 * 
	 * @return
	 */
	public List<Job> getAll();

}
