package com.github.srinivasr22.iploc.service.geolite2;

public class GeoLite2IpVO {

	private String ipRange;
	private String startIp;
	private String endIp;
	private long numericStartIp;
	private long numericEndIp;
	private int countryId;

	public String getIpRange() {
		return ipRange;
	}
	public void setIpRange(String ipRange) {
		this.ipRange = ipRange;
	}
	public String getStartIp() {
		return startIp;
	}
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public long getNumericStartIp() {
		return numericStartIp;
	}
	public void setNumericStartIp(long numericStartIp) {
		this.numericStartIp = numericStartIp;
	}
	public long getNumericEndIp() {
		return numericEndIp;
	}
	public void setNumericEndIp(long numericEndIp) {
		this.numericEndIp = numericEndIp;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
	public static long calculateNumericIp(String ip) {
		String[] ipBlocks = ip.split("\\.");

		long numericStartIp = 0;
		if(ipBlocks.length > 3) {
			numericStartIp += Integer.parseInt(ipBlocks[0]) * (long)Math.pow(256, 3);
			numericStartIp += Integer.parseInt(ipBlocks[1]) * (long)Math.pow(256, 2);
			numericStartIp += Integer.parseInt(ipBlocks[2]) * (long)Math.pow(256, 1);
			numericStartIp += Integer.parseInt(ipBlocks[3]) * (long)Math.pow(256, 0);			
		}
		return numericStartIp;
	}
}
