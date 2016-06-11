package com.matching;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.matching.criteria.BooleanCriteria;
import com.matching.criteria.ListCriteria;
import com.matching.criteria.LocationCriteria;
import com.matching.object.Location;

/**
 * Unit tests for criteria
 * 
 * @author tiago
 *
 */
public class CriteriaTest {
	
	@Test
	public void booleanCriteriaTest() {
		// Job = false
		// Worker = true
		boolean firstValue = false;
		boolean secondValue = true;
		BooleanCriteria booleanCriteria = new BooleanCriteria(firstValue, secondValue);
		double score = booleanCriteria.getScore();
		assertTrue(score == 0.0d);
		
		// Job = false
		// Worker = false
		firstValue = false;
		secondValue = false;
		booleanCriteria = new BooleanCriteria(firstValue, secondValue);
		score = booleanCriteria.getScore();
		assertTrue(score == 1.0d);
		
		// Job = true
		// Worker = false
		firstValue = true;
		secondValue = false;
		booleanCriteria = new BooleanCriteria(firstValue, secondValue);
		score = booleanCriteria.getScore();
		assertTrue(score == 0.0d);
		
		// Job = true
		// Worker = true
		firstValue = true;
		secondValue = true;
		booleanCriteria = new BooleanCriteria(firstValue, secondValue);
		score = booleanCriteria.getScore();
		assertTrue(score == 1.0d);
	}
	
	@Test
	public void listCriteriaTest() {
		// Empty lists
		List<String> firstList = new ArrayList<String>();
		List<String> secondList = new ArrayList<String>();
		ListCriteria<String> listCriteria = new ListCriteria<String>(firstList, secondList);
		double score = listCriteria.getScore();
		assertTrue(score == 0.0d);
		
		// One value lists
		firstList = new ArrayList<String>();
		firstList.add("abc");
		secondList = new ArrayList<String>();
		secondList.add("abc");
		listCriteria = new ListCriteria<String>(firstList, secondList);
		score = listCriteria.getScore();
		assertTrue(score == 1.0d);
		
		// One value lists 2
		firstList = new ArrayList<String>();
		firstList.add("abc");
		secondList = new ArrayList<String>();
		listCriteria = new ListCriteria<String>(firstList, secondList);
		score = listCriteria.getScore();
		assertTrue(score == 0.0d);
		
		// One value lists 3
		firstList = new ArrayList<String>();
		secondList = new ArrayList<String>();
		secondList.add("abc");
		listCriteria = new ListCriteria<String>(firstList, secondList);
		score = listCriteria.getScore();
		assertTrue(score == 0.0d);
		
		// Half values
		firstList = new ArrayList<String>();
		firstList.add("abc");
		firstList.add("def");
		secondList = new ArrayList<String>();
		secondList.add("abc");
		listCriteria = new ListCriteria<String>(firstList, secondList);
		score = listCriteria.getScore();
		assertTrue(score == 0.5d);
		
		// Half values
		firstList = new ArrayList<String>();
		firstList.add("abc");
		secondList = new ArrayList<String>();
		secondList.add("abc");
		secondList.add("def");
		listCriteria = new ListCriteria<String>(firstList, secondList);
		score = listCriteria.getScore();
		assertTrue(score == 1.0d);
	}
	
	@Test
	public void locationCriteriaTest() {    	  
		// Calculate distance 0
		Location firstLocation = new Location();
		firstLocation.setLatitude(0);
		firstLocation.setLongitude(0);
		Location secondLocation = new Location();
		secondLocation.setLatitude(0);
		secondLocation.setLongitude(0);
		int maxDistance = 0;
		LocationCriteria locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		double distance = locationCriteria.calculateDistance();
		assertTrue(distance == 0.0d);
		
		// Calculate distance 1567
		firstLocation = new Location();
		firstLocation.setLatitude(0);
		firstLocation.setLongitude(0);
		secondLocation = new Location();
		secondLocation.setLatitude(10.0);
		secondLocation.setLongitude(10.0);
		maxDistance = 0;
		locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		distance = locationCriteria.calculateDistance();
		assertTrue(distance > 1500 && distance < 1600);
		
		// Calculate score, good
		// Distance = 157 km, max distance = 200 km
		firstLocation = new Location();
		firstLocation.setLatitude(0);
		firstLocation.setLongitude(0);
		secondLocation = new Location();
		secondLocation.setLatitude(1);
		secondLocation.setLongitude(1);
		maxDistance = 200;
		locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		double goodScore = locationCriteria.getScore();
		assertTrue(goodScore >= 0.5);
		
		// Calculate score, too good
		// Distance = 0 km, max distance = 0 km
		firstLocation = new Location();
		firstLocation.setLatitude(0);
		firstLocation.setLongitude(0);
		secondLocation = new Location();
		secondLocation.setLatitude(0);
		secondLocation.setLongitude(0);
		maxDistance = 0;
		locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		double tooGoodScore = locationCriteria.getScore();
		assertTrue(tooGoodScore > goodScore);
		
		// Calculate score, bad
		// Distance = 157 km, max distance = 150 km
		firstLocation = new Location();
		firstLocation.setLatitude(0);
		firstLocation.setLongitude(0);
		secondLocation = new Location();
		secondLocation.setLatitude(1);
		secondLocation.setLongitude(1);
		maxDistance = 150;
		locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		double badScore = locationCriteria.getScore();
		assertTrue(badScore < goodScore);
		
		// Calculate score, too bad
		// Distance = 157 km, max distance = 1 km
		firstLocation = new Location();
		firstLocation.setLatitude(0);
		firstLocation.setLongitude(0);
		secondLocation = new Location();
		secondLocation.setLatitude(1);
		secondLocation.setLongitude(1);
		maxDistance = 1;
		locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		double tooBadScore = locationCriteria.getScore();
		assertTrue(tooBadScore < badScore);
		
		// Weird bug with similar distances
		// Calculate distance 0
		secondLocation = new Location();
		secondLocation.setLatitude(50.256714);
		secondLocation.setLongitude(14.127058);
		maxDistance = 30;
		
		// Distance = 29.311
		firstLocation = new Location();
		firstLocation.setLatitude(50.022868);
		firstLocation.setLongitude(14.316602);		
		locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		double score1 = locationCriteria.getScore();
		
		// Distance = 29.191
		firstLocation = new Location();
		firstLocation.setLatitude(50.091271);
		firstLocation.setLongitude(13.810606);
		locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		double score2 = locationCriteria.getScore();
		
		// Distance = 28.614
		firstLocation = new Location();
		firstLocation.setLatitude(50.08989);
		firstLocation.setLongitude(13.821311);
		locationCriteria = new LocationCriteria(firstLocation, secondLocation, maxDistance);
		double score3 = locationCriteria.getScore();	
		
		assertTrue(score3 > score2 && score2 > score1);
	}

}
