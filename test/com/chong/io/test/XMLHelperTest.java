package com.chong.io.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.chong.entity.wrapper.Sites;
import com.chong.io.XMLHelper;
import com.chong.site.Site;

public class XMLHelperTest {
	public static String filePath;
	public static String testFilePath;
	
	@BeforeClass
	public static void beforeClass() {
		filePath = "samples/ipsoft_perf_counters_xml_sample_data.xml";
		testFilePath = "samples/test_results.xml";
	}
	
	@Test
	public void testReadFromFileString() {
		
		File file = new File(filePath);
		XMLHelper helper = new XMLHelper();
		
		// check file exists
		assertThat("File doesn't exist!", file.exists() && !file.isDirectory(), is(true));
		
		// execute readFromFile function 
		try {
			helper.readFromFile(filePath);
		} catch (ParserConfigurationException | SAXException | IOException
				| JAXBException e) {
			e.printStackTrace();
		}
		
		// check if content are empty
		Sites sites = helper.getSites();
		final int COUNT = 3;
		assertThat("Failed loading data from xml to Java object", sites, is(notNullValue()));
		assertThat(sites.getSites().size(), is(COUNT));
		for (int i=0; i<3; i++) {
			assertThat(sites.getSites().get(i), is(notNullValue()));
			assertThat(sites.getSites().get(i).getName(), is(notNullValue()));
			assertThat(sites.getSites().get(i).getLocation(), is(notNullValue()));
			assertThat(sites.getSites().get(i).getHosts(), is(notNullValue()));
			assertThat(sites.getSites().get(i).getId(), is(notNullValue()));
			assertThat(sites.getSites().get(i).getHosts().getHost().size(), greaterThan(0));
		}
		
		// check values
		// site 1
		assertThat(sites.getSites().get(0).getName(), is("NY-01"));
		assertThat(sites.getSites().get(0).getLocation(), is("New York"));
		assertThat((int)sites.getSites().get(0).getId(), equalTo(101));
		List<Site.Hosts.Host> hosts = sites.getSites().get(0).getHosts().getHost();
		assertThat(hosts.size(), equalTo(4));
		// site 1 host 1
		assertThat((int)hosts.get(0).getId(), equalTo(1001));
		assertThat(hosts.get(0).getHostName(), is("srv001001"));
		assertThat(hosts.get(0).getIPAddress(), is("10.1.2.3"));
		assertThat(hosts.get(0).getOS(), is("Windows"));
		assertThat(Math.abs(1.3 - hosts.get(0).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(2.5 - hosts.get(0).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.2 - hosts.get(0).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		// site 1 host 2
		assertThat((int)hosts.get(1).getId(), equalTo(1002));
		assertThat(hosts.get(1).getHostName(), is("srv001002"));
		assertThat(hosts.get(1).getIPAddress(), is("10.1.2.4"));
		assertThat(hosts.get(1).getOS(), is("Linux"));
		assertThat(Math.abs(1.4 - hosts.get(1).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(2.5 - hosts.get(1).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.2 - hosts.get(1).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		// site 1 host 3
		assertThat((int)hosts.get(2).getId(), equalTo(1003));
		assertThat(hosts.get(2).getHostName(), is("srv001003"));
		assertThat(hosts.get(2).getIPAddress(), is("10.1.2.5"));
		assertThat(hosts.get(2).getOS(), is("Linux"));
		assertThat(Math.abs(3.3 - hosts.get(2).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.6 - hosts.get(2).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.8 - hosts.get(2).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		// site 1 host 4
		assertThat((int)hosts.get(3).getId(), equalTo(1004));
		assertThat(hosts.get(3).getHostName(), is("srv001004"));
		assertThat(hosts.get(3).getIPAddress(), is("10.1.2.6"));
		assertThat(hosts.get(3).getOS(), is("Linux"));
		assertThat(Math.abs(2.3 - hosts.get(3).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(4.5 - hosts.get(3).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(4.2 - hosts.get(3).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		
		// site 2
		assertThat(sites.getSites().get(1).getName(), is("CA-01"));
		assertThat(sites.getSites().get(1).getLocation(), is("California-LA"));
		assertThat((int)sites.getSites().get(1).getId(), equalTo(201));
		hosts = sites.getSites().get(1).getHosts().getHost();
		assertThat(hosts.size(), equalTo(3));
		// site 2 host 1
		assertThat((int)hosts.get(0).getId(), equalTo(2101));
		assertThat(hosts.get(0).getHostName(), is("srv002101"));
		assertThat(hosts.get(0).getIPAddress(), is("172.168.3.23"));
		assertThat(hosts.get(0).getOS(), is("Solaris"));
		assertThat(Math.abs(5.3 - hosts.get(0).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(2.8 - hosts.get(0).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.9 - hosts.get(0).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		// site 2 host 2
		assertThat((int)hosts.get(1).getId(), equalTo(2102));
		assertThat(hosts.get(1).getHostName(), is("srv002102"));
		assertThat(hosts.get(1).getIPAddress(), is("172.168.3.33"));
		assertThat(hosts.get(1).getOS(), is("Solaris"));
		assertThat(Math.abs(3.3 - hosts.get(1).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(3.5 - hosts.get(1).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.8 - hosts.get(1).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		// site 2 host 3
		assertThat((int)hosts.get(2).getId(), equalTo(2110));
		assertThat(hosts.get(2).getHostName(), is("srv002110"));
		assertThat(hosts.get(2).getIPAddress(), is("172.168.3.43"));
		assertThat(hosts.get(2).getOS(), is("Solaris"));
		assertThat(Math.abs(1.2 - hosts.get(2).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.5 - hosts.get(2).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.6 - hosts.get(2).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		
		// site 3
		assertThat(sites.getSites().get(2).getName(), is("CA-02"));
		assertThat(sites.getSites().get(2).getLocation(), is("California-SF"));
		assertThat((int)sites.getSites().get(2).getId(), equalTo(202));
		hosts = sites.getSites().get(2).getHosts().getHost();
		assertThat(hosts.size(), equalTo(3));
		// site 3 host 1
		assertThat((int)hosts.get(0).getId(), equalTo(2233));
		assertThat(hosts.get(0).getHostName(), is("pc002233"));
		assertThat(hosts.get(0).getIPAddress(), is("172.168.240.31"));
		assertThat(hosts.get(0).getOS(), is("MacOS"));
		assertThat(Math.abs(1.5 - hosts.get(0).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(3.5 - hosts.get(0).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.5 - hosts.get(0).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		// site 3 host 2
		assertThat((int)hosts.get(1).getId(), equalTo(2234));
		assertThat(hosts.get(1).getHostName(), is("pc002234"));
		assertThat(hosts.get(1).getIPAddress(), is("172.168.240.32"));
		assertThat(hosts.get(1).getOS(), is("MacOS"));
		assertThat(Math.abs(1.3 - hosts.get(1).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(2.2 - hosts.get(1).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.4 - hosts.get(1).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
		// site 3 host 3
		assertThat((int)hosts.get(2).getId(), equalTo(2235));
		assertThat(hosts.get(2).getHostName(), is("pc002235"));
		assertThat(hosts.get(2).getIPAddress(), is("172.168.240.34"));
		assertThat(hosts.get(2).getOS(), is("MacOS"));
		assertThat(Math.abs(1.4 - hosts.get(2).getLoadAvg1Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(3.5 - hosts.get(2).getLoadAvg5Min() ), lessThanOrEqualTo(0.001));
		assertThat(Math.abs(1.2 - hosts.get(2).getLoadAvg15Min() ), lessThanOrEqualTo(0.001));
	}

	@Test
	public void testWriteToFile() {
		File file = new File(testFilePath);
		XMLHelper helper = new XMLHelper();
		
		// execute readFromFile function 
		try {
			helper.readFromFile(filePath);
		} catch (ParserConfigurationException | SAXException | IOException
				| JAXBException e) {
			e.printStackTrace();
		}
		
		try {
			helper.writeToFile(helper.getSites(), testFilePath);
		} catch (JAXBException | UnsupportedEncodingException | FileNotFoundException | SAXException e) {
			e.printStackTrace();
		}
		
		// check file exists
		assertThat("Failed to create a file", file.exists() && !file.isDirectory(), is(true));
	}

}
