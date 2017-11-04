package com.github.srinivasr22.iploc.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class LocationVO {
	private String countryCode;
	private String zip;
	private String region;
	private String lat;
	private String lon;
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	@Override
	public String toString() {
		return "LocationVO{" +
				"countryCode='" + countryCode + '\'' +
				", region='" + region + '\'' +
				", zip='" + zip + '\'' +
				", lat='" + lat + '\'' +
				", lon='" + lon + '\'' +
				"}";
	}
	
}
