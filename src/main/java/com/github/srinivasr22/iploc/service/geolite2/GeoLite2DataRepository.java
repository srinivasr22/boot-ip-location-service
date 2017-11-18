package com.github.srinivasr22.iploc.service.geolite2;

import java.util.Map;

public interface GeoLite2DataRepository {
	
	Map<Integer, GeoLite2CountryVO> getCountryMap();
	void setCountryMap(Map<Integer, GeoLite2CountryVO> countryMap);
	
}
