package com.matching.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.matching.comparator.ScoreComparator;
import com.matching.criteria.BooleanCriteria;
import com.matching.criteria.ListCriteria;
import com.matching.criteria.LocationCriteria;
import com.matching.object.Job;
import com.matching.object.RankableObject;
import com.matching.object.Worker;

/**
 * Main Matching Filter
 * 
 * @author tiago
 *
 */
public class MatchingFilter {

	private List<Job> jobs;
	private Worker worker;
		
	/**
	 * Constructor
	 * 
	 * @param jobs
	 * @param worker
	 */
	public MatchingFilter(List<Job> jobs, Worker worker) {
		super();
		this.jobs = jobs;
		this.worker = worker;
	}

	public List<Job> filter(int amount) {
		List<Job> filteredJobs = new ArrayList<Job>();
		List<RankableObject<Job>> rankedJobs = new ArrayList<RankableObject<Job>>();
		
		// Retrieve score for each job
		for (Job job : jobs) {
			double score = retrieveScore(job, worker);
			rankedJobs.add(new RankableObject<Job>(job, score));			
		}
		
		// Order list based on score
		Collections.sort(rankedJobs, new ScoreComparator());
		
		// Retrieve amount of filtered objects
		for (int i = 0; i < rankedJobs.size() && i < amount; i++) {
			filteredJobs.add(rankedJobs.get(i).getObject());
		}
		
		return filteredJobs;
	}

	/**
	 * Calculates total score based on criterias
	 * 
	 * @param job
	 * @param worker
	 * @return
	 */
	private double retrieveScore(Job job, Worker worker) {
		double score = 0;
		// Compute drivers license criteria
		score += new BooleanCriteria(true, worker.isDriversLicense()).getScore();
		// Compute certificates criteria
		score += new ListCriteria<String>(job.getRequiredCertificates(), worker.getCertificates()).getScore();
		// Compute location criteria
		score += new LocationCriteria(job.getLocation(), worker.getJobSearchAddress(), worker.getJobSearchAddress().getMaxJobDistance()).getScore();
		// Location still missing
		return score;
	}

	
	
}
