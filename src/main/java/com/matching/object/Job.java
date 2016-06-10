package com.matching.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Job Object
 * 
 * @author tiago
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {

	private List<String> requiredCertificates;
	private boolean requiredDriversLicense;
	private Location location;

	public List<String> getRequiredCertificates() {
		return requiredCertificates;
	}

	public void setRequiredCertificates(List<String> requiredCertificates) {
		this.requiredCertificates = requiredCertificates;
	}

	public boolean isRequiredDriversLicense() {
		return requiredDriversLicense;
	}

	public void setRequiredDriversLicense(boolean requiredDriversLicense) {
		this.requiredDriversLicense = requiredDriversLicense;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
