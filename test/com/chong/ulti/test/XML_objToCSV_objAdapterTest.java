package com.chong.ulti.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.chong.entity.wrapper.Host;
import com.chong.site.Site;
import com.chong.ulti.XML_objToCSV_objAdapter;

public class XML_objToCSV_objAdapterTest {

	@Test
	public void testConverXMLToCSV() {
		List<Host> hosts = new ArrayList<Host>();
		Site site = new Site();
		
		site.setId((short)99);
		site.setName("Cogent");
		site.setLocation("Pitts");
		site.setHosts(new Site.Hosts());
		
		// One site, one host
		Site.Hosts hosts_xml = site.getHosts();
		Site.Hosts.Host host_xml = new Site.Hosts.Host();
		host_xml.setId((short)999);
		host_xml.setHostName("Chong");
		host_xml.setIPAddress("10.0.0.0");
		host_xml.setOS("Windows 7");
		host_xml.setLoadAvg1Min((float)1.0);
		host_xml.setLoadAvg5Min((float)1.1);
		host_xml.setLoadAvg15Min((float)1.2);
		hosts_xml.getHost().add(host_xml);
		
		hosts = XML_objToCSV_objAdapter.converXMLToCSV(site);
		assertThat(hosts.size(), equalTo(1));
		assertThat(hosts.get(0).getSite_id(), equalTo(99));
		assertThat(hosts.get(0).getSite_name(), is("Cogent"));
		assertThat(hosts.get(0).getSite_location(), is("Pitts"));
		assertThat(hosts.get(0).getHost_id(), equalTo(999));
		assertThat(hosts.get(0).getHost_name(), is("Chong"));
		assertThat(hosts.get(0).getIp_address(), is("10.0.0.0"));
		assertThat(hosts.get(0).getOperative_system(), is("Windows 7"));
		assertThat(Math.abs(1.0 - hosts.get(0).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(1.1 - hosts.get(0).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.2 - hosts.get(0).getLoad_avg_15min()), lessThan(0.001));
		
		// One site, two hosts
		Site.Hosts.Host host_xml2 = new Site.Hosts.Host();
		host_xml2.setId((short)1000);
		host_xml2.setHostName("Chen");
		host_xml2.setIPAddress("10.0.0.1");
		host_xml2.setOS("Windows 8");
		host_xml2.setLoadAvg1Min((float)2.0);
		host_xml2.setLoadAvg5Min((float)2.1);
		host_xml2.setLoadAvg15Min((float)2.2);
		hosts_xml.getHost().add(host_xml2);
		
		hosts = XML_objToCSV_objAdapter.converXMLToCSV(site);
		assertThat(hosts.size(), equalTo(2));
		assertThat(hosts.get(0).getSite_id(), equalTo(99));
		assertThat(hosts.get(0).getSite_name(), is("Cogent"));
		assertThat(hosts.get(0).getSite_location(), is("Pitts"));
		assertThat(hosts.get(0).getHost_id(), equalTo(999));
		assertThat(hosts.get(0).getHost_name(), is("Chong"));
		assertThat(hosts.get(0).getIp_address(), is("10.0.0.0"));
		assertThat(hosts.get(0).getOperative_system(), is("Windows 7"));
		assertThat(Math.abs(1.0 - hosts.get(0).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(1.1 - hosts.get(0).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.2 - hosts.get(0).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(1).getSite_id(), equalTo(99));
		assertThat(hosts.get(1).getSite_name(), is("Cogent"));
		assertThat(hosts.get(1).getSite_location(), is("Pitts"));
		assertThat(hosts.get(1).getHost_id(), equalTo(1000));
		assertThat(hosts.get(1).getHost_name(), is("Chen"));
		assertThat(hosts.get(1).getIp_address(), is("10.0.0.1"));
		assertThat(hosts.get(1).getOperative_system(), is("Windows 8"));
		assertThat(Math.abs(2.0 - hosts.get(1).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(2.1 - hosts.get(1).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(2.2 - hosts.get(1).getLoad_avg_15min()), lessThan(0.001));
	}

}
