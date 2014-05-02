package com.chong.io;

import java.util.List;

import com.chong.entity.wrapper.Host;

public class MyCSVEntryConverter {

	public static String convertEntries(List<Host> hosts) {
		StringBuilder content = new StringBuilder();
		
		for (Host host : hosts) {
			content.append(MyCSVEntryConverter.convertToString(host))
				.append("\n");
		}
		
		return content.toString();
	}
	
	private static String convertToString(Host host) {
		StringBuilder sb = new StringBuilder();
		sb.append(host.getSite_id()).append(", ")
			.append(host.getSite_name()).append(", ")
			.append(host.getSite_location()).append(", ")
			.append(host.getHost_id()).append(", ")
			.append(host.getHost_name()).append(", ")
			.append(host.getIp_address()).append(", ")
			.append(host.getOperative_system()).append(", ")
			.append(host.getLoad_avg_1min()).append(", ")
			.append(host.getLoad_avg_5min()).append(", ")
			.append(host.getLoad_avg_15min())
			;
		return sb.toString();
	}
	
	public static String generateHeader() {
		return  "site_id, "
				+ "site_name, "
				+ "site_location, "
				+ "host_id, "
				+ "host_name, "
				+ "ip_address, "
				+ "operative_system, "
				+ "load_avg_1min, "
				+ "load_avg_5min, l"
				+ "oad_avg_15min";
	}
}
