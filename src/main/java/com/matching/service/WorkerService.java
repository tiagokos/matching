package com.matching.service;

import com.matching.object.Worker;

/**
 * Workers Service
 * 
 * @author tiago
 *
 */
public interface WorkerService {
	
	/**
	 * Get worker by Id
	 * 
	 * @param workerId
	 * @return
	 */
	public Worker getById(int workerId);

}
