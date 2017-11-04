package com.github.srinivasr22.iploc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.srinivasr22.iploc.service.ExternalLocationCheckService;

@RestController
public class IpLocationCheckEndpoint {

	@Autowired
	private ExternalLocationCheckService lc;
	
	
	@RequestMapping(value="/check/{country}", method=RequestMethod.GET )
	public String checkIfUSIp(@PathVariable(name="country") String country,
								@RequestParam(value="ip") String ip) {
		System.out.println("Country is ->" + country + "IP is ->" + ip);
		return lc.checkIpToCountryMatch(ip, country)?"true":"false";
	}
}
