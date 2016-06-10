package com.matching.criteria;

import org.opensextant.geodesy.Geodetic2DArc;
import org.opensextant.geodesy.Geodetic2DPoint;
import org.opensextant.geodesy.Latitude;
import org.opensextant.geodesy.Longitude;

import com.matching.object.Location;
import com.matching.object.Rankable;

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
		
		Geodetic2DPoint firstPoint = new Geodetic2DPoint(new Longitude(firstLocation.getLongitude()), new Latitude(firstLocation.getLatitude()));
		Geodetic2DPoint secondPoint = new Geodetic2DPoint(new Longitude(secondLocation.getLongitude()), new Latitude(secondLocation.getLatitude()));
		
		Geodetic2DArc arc = new Geodetic2DArc(secondPoint, firstPoint);
		double distance = arc.getDistanceInMeters() / 1000.0;
		
		// If distance is acceptable, add extra score the closer it gets
		
		// If not, remove score the further it gets

		return score;
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
