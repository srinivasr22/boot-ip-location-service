package com.github.srinivasr22.iploc.service.geolite2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.srinivasr22.iploc.service.ExternalLocationCheckService;

@Service
public class GeoLite2LocationCheckServiceImpl implements ExternalLocationCheckService{
	
	@Autowired
	private GeoLite2DataRepository geoLite2DataRepository;
	
	@Override
	public boolean checkIpToCountryMatch(String ip, String country) {
		geoLite2DataRepository.getCountryMap();
		return false;
	}
}
