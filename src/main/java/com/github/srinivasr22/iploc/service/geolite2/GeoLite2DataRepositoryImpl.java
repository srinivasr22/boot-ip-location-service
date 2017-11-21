package com.github.srinivasr22.iploc.service.geolite2;

import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

@Service
public class GeoLite2DataRepositoryImpl implements GeoLite2DataRepository{

	private 	Map<Integer, GeoLite2CountryVO> countryMap;
	
	private TreeSet<GeoLite2IpVO> ipSet;
	
	@Override
	public Map<Integer, GeoLite2CountryVO> getCountryMap() {
		return countryMap;
	}

	@Override
	public void setCountryMap(Map<Integer, GeoLite2CountryVO> countryMap) {
		this.countryMap = countryMap;
	}

	@Override
	public TreeSet<GeoLite2IpVO> getIpSet() {
		return ipSet;
	}

	@Override
	public void setIpSet(TreeSet<GeoLite2IpVO> ipSet) {
		this.ipSet = ipSet;
	}
	
}
