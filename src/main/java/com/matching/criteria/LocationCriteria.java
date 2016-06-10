package com.matching.criteria;

import org.opensextant.geodesy.Geodetic2DArc;
import org.opensextant.geodesy.Geodetic2DPoint;
import org.opensextant.geodesy.Latitude;
import org.opensextant.geodesy.Longitude;

import com.matching.Rankable;
import com.matching.object.Location;

/**
 * Location Criteria
 * 
 * Generates score between two different locations
 * 
 * @author tiago
 *
 */
public class LocationCriteria implements Rankable {

	private Location firstLocation;
	private Location secondLocation;
	private int maxDistance;

	public LocationCriteria(Location firstLocation, Location secondLocation, int maxDistance) {
		super();
		this.firstLocation = firstLocation;
		this.secondLocation = secondLocation;
		this.maxDistance = maxDistance;
	}

	@Override
	public double getScore() {
		double score = 0;
		
		double distance = calculateDistance();
		// If distance is acceptable, add extra score the closer it gets
		if (distance <= maxDistance) {
			score = 0.7;
			// Place for improvements
			if (maxDistance != 0) {
				// Normalizing
				score += 0.3 * (1 - (distance / maxDistance));
			} else {
				score += 0.3;
			}			
		} else { // If not, remove score the further it gets
			score = 0.3;
			// Place for improvements
			score *= (1 / (distance / maxDistance));
		}
		return score;
	}
	
	/**
	 * Helper method to calculate distance in kms
	 * 
	 * @return
	 */
	public double calculateDistance() {
		double distance = 0;
		
		Geodetic2DPoint firstPoint = new Geodetic2DPoint(new Longitude(String.valueOf(firstLocation.getLongitude())), new Latitude(String.valueOf(firstLocation.getLatitude())));
		Geodetic2DPoint secondPoint = new Geodetic2DPoint(new Longitude(String.valueOf(secondLocation.getLongitude())), new Latitude(String.valueOf(secondLocation.getLatitude())));
		
		Geodetic2DArc arc = new Geodetic2DArc(secondPoint, firstPoint);
		distance = arc.getDistanceInMeters() / 1000.0;
		
		return distance;
	}

	public Location getFirstLocation() {
		return firstLocation;
	}

	public void setFirstLocation(Location firstLocation) {
		this.firstLocation = firstLocation;
	}

	public Location getSecondLocation() {
		return secondLocation;
	}

	public void setSecondLocation(Location secondLocation) {
		this.secondLocation = secondLocation;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

}
