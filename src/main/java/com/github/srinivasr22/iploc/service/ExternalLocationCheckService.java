package com.github.srinivasr22.iploc.service;

public interface ExternalLocationCheckService {
	
	boolean checkIpToCountryMatch (String ip, String country);

}
