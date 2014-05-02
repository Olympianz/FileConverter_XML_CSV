package com.chong.ulti;

import java.util.ArrayList;
import java.util.List;

import com.chong.entity.wrapper.Host;
import com.chong.site.Site;

public class XML_objToCSV_objAdapter {

	public static List<Host> converXMLToCSV(Site site) {
		List<Host> hosts = new ArrayList<Host>();
		
		List<Site.Hosts.Host> hosts_xml = site.getHosts().getHost();
		
		for (Site.Hosts.Host host_xml : hosts_xml) {
			Host host = new Host();
			
			host.setSite_id(site.getId());
			host.setSite_name(site.getName());
			host.setSite_location(site.getLocation());
			
			host.setHost_id(host_xml.getId());
			host.setHost_name(host_xml.getHostName());
			host.setIp_address(host_xml.getIPAddress());
			host.setOperative_system(host_xml.getOS());
			host.setLoad_avg_1min(host_xml.getLoadAvg1Min());
			host.setLoad_avg_5min(host_xml.getLoadAvg5Min());
			host.setLoad_avg_15min(host_xml.getLoadAvg15Min());
			
			hosts.add(host);
		}
		
		return hosts;
	}
}
