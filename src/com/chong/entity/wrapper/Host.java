package com.chong.entity.wrapper;

public class Host {

	private int site_id;
	
	private String site_name;
	
	private String site_location;
	
	private int host_id;
	
	private String host_name;
	
	private String ip_address;
	
	private String operative_system;
	
	private float load_avg_1min;
	
	private float load_avg_5min;
	
	private float load_avg_15min;

	public int getSite_id() {
		return site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getSite_location() {
		return site_location;
	}

	public void setSite_location(String site_location) {
		this.site_location = site_location;
	}

	public int getHost_id() {
		return host_id;
	}

	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getOperative_system() {
		return operative_system;
	}

	public void setOperative_system(String operative_system) {
		this.operative_system = operative_system;
	}

	public float getLoad_avg_1min() {
		return load_avg_1min;
	}

	public void setLoad_avg_1min(float load_avg_1min) {
		this.load_avg_1min = load_avg_1min;
	}

	public float getLoad_avg_5min() {
		return load_avg_5min;
	}

	public void setLoad_avg_5min(float load_avg_5min) {
		this.load_avg_5min = load_avg_5min;
	}

	public float getLoad_avg_15min() {
		return load_avg_15min;
	}

	public void setLoad_avg_15min(float load_avg_15min) {
		this.load_avg_15min = load_avg_15min;
	}
	
}
