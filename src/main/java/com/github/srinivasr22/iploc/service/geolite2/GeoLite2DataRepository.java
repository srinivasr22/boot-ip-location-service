package com.github.srinivasr22.iploc.service.geolite2;

import java.util.Map;
import java.util.TreeSet;

public interface GeoLite2DataRepository {
	
	Map<Integer, GeoLite2CountryVO> getCountryMap();
	void setCountryMap(Map<Integer, GeoLite2CountryVO> countryMap);
	
	TreeSet<GeoLite2IpVO> getIpSet();
	void setIpSet(TreeSet<GeoLite2IpVO> ipSet);
	
}
