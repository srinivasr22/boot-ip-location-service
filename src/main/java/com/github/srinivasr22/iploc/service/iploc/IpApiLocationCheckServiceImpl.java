package com.github.srinivasr22.iploc.service.iploc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.srinivasr22.iploc.service.ExternalLocationCheckService;

@Service
public class IpApiLocationCheckServiceImpl implements ExternalLocationCheckService{

	@Value("${ipapi.baseurl}")
	private String urlTemplate;
	
	@Override
	public boolean checkIpToCountryMatch(String ip, String country) {
		RestTemplate rt = new RestTemplate();
		IpApiLocationVO l = rt.getForObject(constructUrl(ip), IpApiLocationVO.class);
		System.out.println(l.toString());
		return country.equalsIgnoreCase(l.getCountryCode()) ? true:false;
	}
	
	private String constructUrl(String ip) {
		return this.urlTemplate.replace("{ip}", ip);
	}
}
