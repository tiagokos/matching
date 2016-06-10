package com.matching.criteria;

import java.util.List;

import com.matching.object.Rankable;

/**
 * List Criteria
 * 
 * Generates score between two list values
 * 
 * @author tiago
 *
 */
public class ListCriteria<T> implements Rankable {

	private List<T> firstList;
	private List<T> secondList;
	
	public ListCriteria(List<T> firstList, List<T> secondList) {
		super();
		this.firstList = firstList;
		this.secondList = secondList;
	}

	@Override
	public double getScore() {
		double score = 0;
		
		// Gets original list size
		int listSize = firstList.size();
		if (listSize > 0) {
			// Remove all values contained in the second list
			firstList.removeAll(secondList);
			// Gets remaining list size
			int remainingListSize = firstList.size();
			
			// Generates score based on remaining values
			// The more remaining values are, lower is the score
			score = (1.0 * listSize - remainingListSize) / listSize;
		}
		return score;		
	}

	public List<T> getFirstList() {
		return firstList;
	}

	public void setFirstList(List<T> firstList) {
		this.firstList = firstList;
	}

	public List<T> getSecondList() {
		return secondList;
	}

	public void setSecondList(List<T> secondList) {
		this.secondList = secondList;
	}

}
