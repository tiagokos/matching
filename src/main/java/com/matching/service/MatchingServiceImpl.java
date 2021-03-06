package com.matching.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matching.filter.MatchingFilter;
import com.matching.object.Job;
import com.matching.object.Worker;

/**
 * Matching Service Implementation
 * 
 * @author tiago
 *
 */
@Service
public class MatchingServiceImpl implements MatchingService {

	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private JobService jobService;
	
	public List<Job> getMatchingJobs(int workerId) {
		List<Job> matchedJobs = null;
		
		Worker worker = workerService.getById(workerId);
		if (worker != null) {
			List<Job> jobs = jobService.getAll();
			matchedJobs = new MatchingFilter(jobs, worker).filter(3);
		}
	    
	    return matchedJobs;
	}

	public WorkerService getWorkerService() {
		return workerService;
	}

	public void setWorkerService(WorkerService workerService) {
		this.workerService = workerService;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	
}
