package it.polito.tdp.formulaone.model;

public class Adiacenza {
	
	private Driver d1;
	private Driver d2;
	private Integer count;
	
	public Adiacenza(Driver d1, Driver d2, Integer count) {
		this.d1 = d1;
		this.d2 = d2;
		this.count = count;
	}

	public Driver getD1() {
		return d1;
	}

	public void setD1(Driver d1) {
		this.d1 = d1;
	}

	public Driver getD2() {
		return d2;
	}

	public void setD2(Driver d2) {
		this.d2 = d2;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}
