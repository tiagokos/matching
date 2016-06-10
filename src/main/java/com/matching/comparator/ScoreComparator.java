package com.matching.comparator;

import java.util.Comparator;

import com.matching.Rankable;

/**
 * Order objects by score
 * 
 * @author tiago
 *
 */
public class ScoreComparator implements Comparator<Rankable> {

	@Override
	public int compare(Rankable o1, Rankable o2) {
		if (o1.getScore() > o2.getScore()) {
			return -1;
		}
		if (o1.getScore() < o2.getScore()) {
			return 1;
		}
		return 0;
	}

}
