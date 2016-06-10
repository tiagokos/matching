package com.matching.object;

/**
 * Wrapper class to rank objects
 * 
 * @author tiago
 *
 * @param <T>
 */
public class RankableObject<T> implements Rankable {

	private T object;
	private double score;

	/**
	 * Constructor
	 * 
	 * @param object
	 * @param score
	 */
	public RankableObject(T object, double score) {
		super();
		this.object = object;
		this.score = score;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
