package com.matching.object;

/**
 * Job Search Address object
 * 
 * Extension for Location
 * 
 * @author tiago
 *
 */
public class JobSearchAddress extends Location {

	private int maxJobDistance;

	public int getMaxJobDistance() {
		return maxJobDistance;
	}

	public void setMaxJobDistance(int maxJobDistance) {
		this.maxJobDistance = maxJobDistance;
	}

}
