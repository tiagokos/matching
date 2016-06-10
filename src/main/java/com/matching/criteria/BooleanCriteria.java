package com.matching.criteria;

import com.matching.Rankable;

/**
 * Boolean Criteria
 * 
 * Generates score between two boolean values
 * 
 * @author tiago
 *
 */
public class BooleanCriteria implements Rankable {

	private boolean firstValue;
	private boolean secondValue;	
	
	public BooleanCriteria(boolean firstValue, boolean secondValue) {
		super();
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	@Override
	public double getScore() {
		double score = 0;

		if (firstValue == secondValue) {
			score = 1;
		}

		return score;
	}

	public boolean isFirstValue() {
		return firstValue;
	}

	public void setFirstValue(boolean firstValue) {
		this.firstValue = firstValue;
	}

	public boolean isSecondValue() {
		return secondValue;
	}

	public void setSecondValue(boolean secondValue) {
		this.secondValue = secondValue;
	}

}
