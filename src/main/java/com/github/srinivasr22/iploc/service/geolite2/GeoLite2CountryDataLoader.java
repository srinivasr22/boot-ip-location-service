package com.github.srinivasr22.iploc.service.geolite2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
class GeoLite2CountryDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	public static final String COUNTRY_FILENAME = "GeoLite2-Country-Locations-en.csv";

	@Autowired
	private GeoLite2DataRepository geoLite2DataRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		loadCountryData();
	}

	private void loadCountryData() {
		try {
			Path country = Paths.get(ClassLoader.getSystemResource(COUNTRY_FILENAME).toURI());
			System.out.println(country.toRealPath().toString());
			geoLite2DataRepository.setCountryMap(Files.lines(country)
						.skip(1)
						.filter(line -> !"".equals(line))
						.map(mapToCountryVO)
						.collect(Collectors.toMap(GeoLite2CountryVO::getId, Function.identity())));
			geoLite2DataRepository.getCountryMap()
								.forEach((id, vo) -> {System.out.println("id: "+id + ", vo:"+vo.getCountry());});
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println("Loaded GeoLite2 Country Data");
	}
	
	private Function<String, GeoLite2CountryVO> mapToCountryVO = line -> {
		String[] tokens = line.split(",");
		GeoLite2CountryVO gVo = new GeoLite2CountryVO();
		if (tokens.length > 0)
			gVo.setId(Integer.parseInt(tokens[0]));
		if (tokens.length > 2)
			gVo.setContinentCode(tokens[2]);
		if (tokens.length > 3)
			gVo.setContinent(tokens[3]);
		if (tokens.length > 4)
			gVo.setCountryCode(tokens[4]);
		if (tokens.length > 5)
			gVo.setCountry(tokens[5]);
		return gVo;
	};
}
