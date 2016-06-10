package com.matching.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Worker Object
 * 
 * @author tiago
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker {

	private int userId;
	private List<String> certificates;
	private List<String> skills;
	private boolean driversLicense;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<String> certificates) {
		this.certificates = certificates;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public boolean isDriversLicense() {
		return driversLicense;
	}

	public void setDriversLicense(boolean driversLicense) {
		this.driversLicense = driversLicense;
	}

	@Override
	public String toString() {
		return "Worker [userId=" + userId + ", certificates=" + certificates + ", skills=" + skills
				+ ", driversLicense=" + driversLicense + "]";
	}

}
