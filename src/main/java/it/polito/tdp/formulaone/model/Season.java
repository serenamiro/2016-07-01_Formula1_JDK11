package it.polito.tdp.formulaone.model;

public class Season {

	private Integer year ;
	private String url ;
	
	public Season(Integer year, String url) {
		super();
		this.year = year;
		this.url = url;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return ""+year;
	}
	
	
	
}

