package com.chong.converter;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.chong.entity.wrapper.Host;
import com.chong.entity.wrapper.Sites;
import com.chong.io.CSVHelper;
import com.chong.io.XMLHelper;
import com.chong.manipulator.HostManipulator;
import com.chong.manipulator.SiteManipulator;
import com.chong.site.Site;
import com.chong.ulti.CSVComparator;
import com.chong.ulti.FieldOperations;
import com.chong.ulti.SiteComparator;
import com.chong.ulti.XML_objToCSV_objAdapter;

/**
 * 
 * 
 * @author NickyYue
 *
 */
public class ConverterTest {
	private static String filePath = "samples/ipsoft_perf_counters_xml_sample_data.xml";
	private static String fileOut_1 = "samples/testCase01-results.txt";
	private static String fileOut_2 = "samples/testCase02-results.txt";
	private static String fileOut_3 = "samples/testCase03-results.xml";

	/**
	 * Generate CSV output from XML input, output must be sorted by by operating system, 
	 * then by host_id descending.
	 * 
	 * Related tests include
	 * 1. test/ com.chong.io.test.XMLHelperTest.java
	 * 2. test/ com.chong.io.test.CSVHelperTest.java
	 * 3. test/ com.chong.ulti.test.XML_objToCSV_objAdapterTest.java
	 * 4. test/ com.chong.ulti.test.FieldOperationsTest.java
	 * 5. test/ com.chong.manipulator.test.SiteManipulatorTest.java
	 */
	@Test
	public void convertCSVToXML_sort() {
		// read from file
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
		
		// convert XML objects to CSV objects
		List<Site> sites = helper.getSites().getSites();
		List<Host> csv_hosts = new ArrayList<Host>();
		for (Site site : sites) {
			csv_hosts.addAll(XML_objToCSV_objAdapter.converXMLToCSV(site));
		}
		
		// sort CSV objects
		HostManipulator hostManipulator = 
				new HostManipulator(new FieldOperations<Host>(new Host()));
		FieldOperations<Host> operator = new FieldOperations<Host>(new Host());
		Map<String, Boolean> fields = new LinkedHashMap<String, Boolean>();
		
		fields.put("operative_system", false);
		fields.put("host_id", false);
		CSVComparator comparator = new CSVComparator(fields, operator);
		hostManipulator.sort(csv_hosts, comparator, fields);
		
		// test sorted objects
		assertThat(csv_hosts.size(), equalTo(10));
		assertThat(csv_hosts.get(0).getOperative_system(), is("Windows"));
		assertThat(csv_hosts.get(0).getHost_id(), equalTo(1001));
		assertThat(csv_hosts.get(1).getOperative_system(), is("Solaris"));
		assertThat(csv_hosts.get(1).getHost_id(), equalTo(2110));
		assertThat(csv_hosts.get(2).getOperative_system(), is("Solaris"));
		assertThat(csv_hosts.get(2).getHost_id(), equalTo(2102));
		assertThat(csv_hosts.get(3).getOperative_system(), is("Solaris"));
		assertThat(csv_hosts.get(3).getHost_id(), equalTo(2101));
		assertThat(csv_hosts.get(4).getOperative_system(), is("MacOS"));
		assertThat(csv_hosts.get(4).getHost_id(), equalTo(2235));
		assertThat(csv_hosts.get(5).getOperative_system(), is("MacOS"));
		assertThat(csv_hosts.get(5).getHost_id(), equalTo(2234));
		assertThat(csv_hosts.get(6).getOperative_system(), is("MacOS"));
		assertThat(csv_hosts.get(6).getHost_id(), equalTo(2233));
		assertThat(csv_hosts.get(7).getOperative_system(), is("Linux"));
		assertThat(csv_hosts.get(7).getHost_id(), equalTo(1004));
		assertThat(csv_hosts.get(8).getOperative_system(), is("Linux"));
		assertThat(csv_hosts.get(8).getHost_id(), equalTo(1003));
		assertThat(csv_hosts.get(9).getOperative_system(), is("Linux"));
		assertThat(csv_hosts.get(9).getHost_id(), equalTo(1002));
		
		// generate sorted output
		CSVHelper csv_helper = new CSVHelper();		
		try {
			csv_helper.writeToFile(csv_hosts, fileOut_1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File fileOut = new File(fileOut_1);
		assertThat(fileOut.exists() && !fileOut.isDirectory(), is(true));
		
	}
	
	/**
	 * Generate CSV output from XML input, filtering by site_name=¡¯ NY-01¡¯ and 
	 * sort by host_name ascending.
	 * 
	 * Related tests include
	 * 1. test/ com.chong.io.test.XMLHelperTest.java
	 * 2. test/ com.chong.io.test.CSVHelperTest.java
	 * 3. test/ com.chong.ulti.test.XML_objToCSV_objAdapterTest.java
	 * 4. test/ com.chong.ulti.test.FieldOperationsTest.java
	 * 5. test/ com.chong.manipulator.test.SiteManipulatorTest.java
	 */
	@Test
	public void convertCSVToXML_sort_filter() {
		// read from file
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
		
		// convert XML objects to CSV objects
		List<Site> sites = helper.getSites().getSites();
		List<Host> csv_hosts = new ArrayList<Host>();
		for (Site site : sites) {
			csv_hosts.addAll(XML_objToCSV_objAdapter.converXMLToCSV(site));
		}
		
		// filter CSV objects
		HostManipulator hostManipulator = 
				new HostManipulator(new FieldOperations<Host>(new Host()));
		FieldOperations<Host> operator = new FieldOperations<Host>(new Host());
		Map<String, String> filter_fields = new HashMap<String, String>();	
		
		filter_fields.put("site_name", "NY-01");
		List<Host> filteredHosts = hostManipulator.filter(csv_hosts, filter_fields);
		
		// sort CSV objects
		Map<String, Boolean> sort_fields = new LinkedHashMap<String, Boolean>();
		sort_fields.put("host_name", true);
		CSVComparator comparator = new CSVComparator(sort_fields, operator);
		hostManipulator.sort(filteredHosts, comparator, sort_fields);
		
		// test sorted objects
		assertThat(filteredHosts.size(), equalTo(4));
		assertThat(filteredHosts.get(0).getSite_name(), is("NY-01"));
		assertThat(filteredHosts.get(0).getHost_name(), is("srv001001"));
		assertThat(filteredHosts.get(1).getSite_name(), is("NY-01"));
		assertThat(filteredHosts.get(1).getHost_name(), is("srv001002"));
		assertThat(filteredHosts.get(2).getSite_name(), is("NY-01"));
		assertThat(filteredHosts.get(2).getHost_name(), is("srv001003"));
		assertThat(filteredHosts.get(3).getSite_name(), is("NY-01"));
		assertThat(filteredHosts.get(3).getHost_name(), is("srv001004"));
		
		// generate sorted output
		CSVHelper csv_helper = new CSVHelper();		
		try {
			csv_helper.writeToFile(filteredHosts, fileOut_2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File fileOut = new File(fileOut_2);
		assertThat(fileOut.exists() && !fileOut.isDirectory(), is(true));
	}
	
	/**
	 * Generate XML output from XML input, sorted by load average (1 min) ascending, 
	 * then host_id descending.
	 * 
	 * Related tests include
	 * 1. test/ com.chong.io.test.XMLHelperTest.java
	 * 2. test/ com.chong.io.test.CSVHelperTest.java
	 * 3. test/ com.chong.ulti.test.XML_objToCSV_objAdapterTest.java
	 * 4. test/ com.chong.ulti.test.FieldOperationsTest.java
	 * 5. test/ com.chong.manipulator.test.SiteManipulatorTest.java
	 */
	@Test
	public void convertXMLToXML_sort() {
		// read from file
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
		
		// sort XML objects
		Sites xml_sites = helper.getSites();
		List<Site> sites = xml_sites.getSites();
		
		// add two hosts to each site
		Site.Hosts.Host host01 = new Site.Hosts.Host();
		host01.setLoadAvg1Min((float)1.3);
		host01.setId((short)33);
		Site.Hosts.Host host02 = new Site.Hosts.Host();
		host02.setLoadAvg1Min((float)1.3);
		host02.setId((short)34);
		sites.get(0).getHosts().getHost().add(host01);
		sites.get(0).getHosts().getHost().add(host02);
		
		Site.Hosts.Host host11 = new Site.Hosts.Host();
		host11.setLoadAvg1Min((float)1.2);
		host11.setId((short)44);
		Site.Hosts.Host host12 = new Site.Hosts.Host();
		host12.setLoadAvg1Min((float)1.2);
		host12.setId((short)45);
		sites.get(1).getHosts().getHost().add(host11);
		sites.get(1).getHosts().getHost().add(host12);
		
		Site.Hosts.Host host21 = new Site.Hosts.Host();
		host21.setLoadAvg1Min((float)1.5);
		host21.setId((short)55);
		Site.Hosts.Host host22 = new Site.Hosts.Host();
		host22.setLoadAvg1Min((float)1.5);
		host22.setId((short)56);
		sites.get(2).getHosts().getHost().add(host21);
		sites.get(2).getHosts().getHost().add(host22);
		
		FieldOperations<Site> operator = new FieldOperations<Site>(new Site());
		SiteManipulator siteManipulator = new SiteManipulator(operator);
		Map<String, Boolean> fields = new LinkedHashMap<String, Boolean>();
		fields.put("loadAvg1Min", true);
		fields.put("host_id", false);
		SiteComparator comparator = new SiteComparator(fields, operator);
		siteManipulator.sort(sites, comparator, fields);
		
		
		// test sorted objects
		assertThat(sites.size(), equalTo(3));
		// site 0
		assertThat(sites.get(0).getHosts().getHost().size(), equalTo(6));
		assertThat(Math.abs(1.3 - 
				sites.get(0).getHosts().getHost().get(0).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(0).getHosts().getHost().get(0).getId(), equalTo(1001));
		assertThat(Math.abs(1.3 - 
				sites.get(0).getHosts().getHost().get(1).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(0).getHosts().getHost().get(1).getId(), equalTo(34));
		assertThat(Math.abs(1.3 - 
				sites.get(0).getHosts().getHost().get(2).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(0).getHosts().getHost().get(2).getId(), equalTo(33));
		assertThat(Math.abs(1.4 - 
				sites.get(0).getHosts().getHost().get(3).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(2.3 - 
				sites.get(0).getHosts().getHost().get(4).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(3.3 - 
				sites.get(0).getHosts().getHost().get(5).getLoadAvg1Min()), lessThan(0.001));
		
		// site 1
		assertThat(sites.get(1).getHosts().getHost().size(), equalTo(5));
		assertThat(Math.abs(1.2 - 
				sites.get(1).getHosts().getHost().get(0).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(1).getHosts().getHost().get(0).getId(), equalTo(2110));
		assertThat(Math.abs(1.2 - 
				sites.get(1).getHosts().getHost().get(1).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(1).getHosts().getHost().get(1).getId(), equalTo(45));
		assertThat(Math.abs(1.2 - 
				sites.get(1).getHosts().getHost().get(2).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(1).getHosts().getHost().get(2).getId(), equalTo(44));
		assertThat(Math.abs(3.3 - 
				sites.get(1).getHosts().getHost().get(3).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(5.3 - 
				sites.get(1).getHosts().getHost().get(4).getLoadAvg1Min()), lessThan(0.001));
		
		// site 2
		assertThat(sites.get(2).getHosts().getHost().size(), equalTo(5));
		assertThat(Math.abs(1.3 - 
				sites.get(2).getHosts().getHost().get(0).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(1.4 - 
				sites.get(2).getHosts().getHost().get(1).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(1.5 - 
				sites.get(2).getHosts().getHost().get(2).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(2).getHosts().getHost().get(2).getId(), equalTo(2233));
		assertThat(Math.abs(1.5 - 
				sites.get(2).getHosts().getHost().get(3).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(2).getHosts().getHost().get(3).getId(), equalTo(56));
		assertThat(Math.abs(1.5 - 
				sites.get(2).getHosts().getHost().get(4).getLoadAvg1Min()), lessThan(0.001));
		assertThat((int)sites.get(2).getHosts().getHost().get(4).getId(), equalTo(55));
	
		
		// generate sorted output
		XMLHelper xml_helper = new XMLHelper();		
		try {
			xml_helper.writeToFile(xml_sites, fileOut_3);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		File fileOut = new File(fileOut_3);
		assertThat(fileOut.exists() && !fileOut.isDirectory(), is(true));
		
	}

}
