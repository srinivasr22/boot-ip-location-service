package com.github.srinivasr22.iploc.service.geolite2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;


@Service
public class GeoLite2IpDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	public static final String IP_FILENAME = "GeoLite2-Country-Blocks-IPv4.csv";
	
	@Autowired
	private GeoLite2DataRepository geoLite2DataRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		loadIpData();
	}	
	
	private void loadIpData() {
		System.out.println("Loading IP data");
		try {
			Path ips = Paths.get(ClassLoader.getSystemResource(IP_FILENAME).toURI());
			System.out.println(ips.toRealPath().toString());
			
			Comparator<GeoLite2IpVO> byCountryId =
					Comparator.comparingLong(GeoLite2IpVO::getNumericStartIp);
			Supplier<TreeSet<GeoLite2IpVO>> treeSetSupplier =
					() -> new TreeSet<GeoLite2IpVO>(byCountryId);
			
			geoLite2DataRepository.setIpSet(
				Files.lines(ips)
					  .skip(1)
					  .filter(line -> !"".equals(line))
					  .map(mapToIpVO)
					  .collect( Collectors.toCollection(treeSetSupplier)));
			geoLite2DataRepository.getIpSet().forEach(ip -> System.out.println("ips "+ ip.getIpRange() 
													+ " " + ip.getNumericStartIp()
													+ " " + ip.getCountryId()));
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private Function<String, GeoLite2IpVO> mapToIpVO = line -> {
		String[] token = line.split(",");
		GeoLite2IpVO geoLite2IpVO = new GeoLite2IpVO();
		if(token.length > 0)
			geoLite2IpVO.setIpRange(token[0]);
		if(token.length > 1) {
			try {
				geoLite2IpVO.setCountryId(Integer.parseInt(token[1]));				
			} catch(NumberFormatException ne) {
				
			}
		}
		
		String ips[] = geoLite2IpVO.getIpRange().split("/");
		geoLite2IpVO.setNumericStartIp(GeoLite2IpVO.calculateNumericIp(ips[0]));
		
		return geoLite2IpVO;
	};
}
