package it.polito.tdp.extflightdelays.model;

public class Rotta {
	private Airport a1;
	private Airport a2;
	private double averageDistance;
	
	public Rotta(Airport a1, Airport a2, double averageDistance) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.averageDistance= averageDistance;
	}

	public Airport getA1() {
		return a1;
	}

	public void setA1(Airport a1) {
		this.a1 = a1;
	}

	public Airport getA2() {
		return a2;
	}

	public void setA2(Airport a2) {
		this.a2 = a2;
	}

	public double getAverageDistance() {
		return averageDistance;
	}

	public void setAverageDistance(float averageDistance) {
		this.averageDistance = averageDistance;
	}


	
	
	
	
	
}
