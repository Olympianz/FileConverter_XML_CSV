package com.chong.manipulator.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.chong.io.XMLHelper;
import com.chong.manipulator.SiteManipulator;
import com.chong.site.Site;
import com.chong.ulti.FieldOperations;
import com.chong.ulti.SiteComparator;

public class SiteManipulatorTest {
	public static SiteManipulator manipulator;
	public static FieldOperations<Site> operator;
	public static String filePath;
	public static String testFielPath;
	
	@BeforeClass
	public static void beforeClass() {
		operator = new FieldOperations<Site>(new Site());
		manipulator = new SiteManipulator(operator);
		filePath = "samples/ipsoft_perf_counters_xml_sample_data.xml";
		testFielPath = "samples/test_results.xml";
	}

	@Test
	public void testFilter() {
		/////////////////////////////
		// Get data from sample file
		/////////////////////////////
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
		
		/////////////////////////////
		// test filter function
		/////////////////////////////
		List<Site> sites = helper.getSites().getSites();
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("name", "NY-01");
		
		// One criteria one result
		List<Site> filteredSites = manipulator.filter(sites, criteria);
		assertThat(filteredSites.size(), equalTo(1));
		assertThat(filteredSites.get(0).getName(), is("NY-01"));
		
		Site n_site = new Site();
		n_site.setName("NY-01");
		n_site.setId((short) 15);
		n_site.setLocation("China");
		
		// One criteria two results
		sites.add(n_site);
		filteredSites = manipulator.filter(sites, criteria);
		assertThat(filteredSites.size(), equalTo(2));
		assertThat(filteredSites.get(0).getName(), is("NY-01"));
		
		// Two criteria one result
		criteria.put("location", "China");
		filteredSites = manipulator.filter(sites, criteria);
		assertThat(filteredSites.size(), equalTo(1));
		assertThat(filteredSites.get(0).getName(), is("NY-01"));
		assertThat(filteredSites.get(0).getLocation(), is("China"));
		
		// One criteria on "Site" + one criteria on nested "Host"
		criteria.remove("location");
		criteria.put("ipAddress", "10.1.2.3");
		Site.Hosts site_hosts = new Site.Hosts();
		Site.Hosts.Host host_test = new Site.Hosts.Host();
		host_test.setHostName("Chong2013");
		host_test.setIPAddress("10.1.2.3");
		site_hosts.getHost().add(host_test);
		sites.get(3).setHosts(site_hosts);
		
		assertThat(sites.size(), equalTo(4));
		filteredSites = manipulator.filter(sites, criteria);
		assertThat(filteredSites.size(), equalTo(2));
		assertThat(filteredSites.get(0).getHosts().getHost().size(), equalTo(1));
		assertThat(filteredSites.get(0).getHosts().getHost().get(0).getIPAddress(), is("10.1.2.3"));
		assertThat(filteredSites.get(1).getHosts().getHost().size(), equalTo(1));
		assertThat(filteredSites.get(1).getHosts().getHost().get(0).getIPAddress(), is("10.1.2.3"));
		
		// Wrong criteria
		criteria.put("sex", "Male");
		filteredSites = manipulator.filter(sites, criteria);
		assertThat(filteredSites.size(), equalTo(0));
				
	}
	
	@Test
	public void testSort() {
		/////////////////////////////
		// Get data from sample file
		/////////////////////////////
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
		
		/////////////////////////////
		// test sort function
		/////////////////////////////
		List<Site> sites = helper.getSites().getSites();
		Map<String, Boolean> sortingFields = new LinkedHashMap<String, Boolean>();
		
		// Wrong criteria
		// reserve original order
		sortingFields.put("sex", true);
		manipulator.sort(sites, new SiteComparator(sortingFields, operator), sortingFields);
		assertThat(sites.size(), equalTo(3));
		assertThat((int)sites.get(0).getId(), equalTo(101));
		assertThat((int)sites.get(1).getId(), equalTo(201));
		assertThat((int)sites.get(2).getId(), equalTo(202));
		
		// One criteria
		sortingFields.put("location", true);
		manipulator.sort(sites, new SiteComparator(sortingFields, operator), sortingFields);
		assertThat(sites.size(), equalTo(3));
		assertThat(sites.get(0).getLocation(), is("California-LA"));
		assertThat(sites.get(1).getLocation(), is("California-SF"));
		assertThat(sites.get(2).getLocation(), is("New York"));
		
		Site n_site = new Site();
		n_site.setName("NY-00");
		n_site.setId((short) 15);
		n_site.setLocation("New York");
		
		// Two criteria
		sites.add(n_site);
		sortingFields.put("name", true);
		manipulator.sort(sites,	new SiteComparator(sortingFields, operator), sortingFields);
		assertThat(sites.size(), equalTo(4));
		// Sort by "location" first
		assertThat(sites.get(0).getLocation(), is("California-LA"));
		assertThat(sites.get(1).getLocation(), is("California-SF"));
		assertThat(sites.get(2).getLocation(), is("New York"));
		assertThat(sites.get(3).getLocation(), is("New York"));
		// Sort by "name" then
		assertThat(sites.get(2).getName(), is("NY-00"));
		assertThat(sites.get(3).getName(), is("NY-01"));
		
		// One criteria on "Site" + one criteria on "Host"
		sortingFields.clear();
		sortingFields.put("name", true);
		sortingFields.put("loadAvg1Min", true);
		manipulator.sort(sites,	new SiteComparator(sortingFields, operator), sortingFields);
		// test sorted sites
		assertThat(sites.size(), equalTo(4));
		assertThat(sites.get(0).getName(), is("CA-01"));
		assertThat(sites.get(1).getName(), is("CA-02"));
		assertThat(sites.get(2).getName(), is("NY-00"));
		assertThat(sites.get(3).getName(), is("NY-01"));
		// test sorted hosts
		// sorted site 0 
		assertThat(sites.get(0).getHosts().getHost().size(), equalTo(3));
		assertThat(Math.abs(1.2 - 
				sites.get(0).getHosts().getHost().get(0).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(3.3 - 
				sites.get(0).getHosts().getHost().get(1).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(5.3 - 
				sites.get(0).getHosts().getHost().get(2).getLoadAvg1Min()), lessThan(0.001));

		// sorted site 1
		assertThat(sites.get(1).getHosts().getHost().size(), equalTo(3));
		assertThat(Math.abs(1.3 - 
				sites.get(1).getHosts().getHost().get(0).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(1.4 - 
				sites.get(1).getHosts().getHost().get(1).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(1.5 - 
				sites.get(1).getHosts().getHost().get(2).getLoadAvg1Min()), lessThan(0.001));
		
		// sorted site 2
		assertThat(sites.get(2).getHosts(), is(nullValue()));
		
		// sorted site 3
		assertThat(sites.get(3).getHosts().getHost().size(), equalTo(4));
		assertThat(Math.abs(1.3 - 
				sites.get(3).getHosts().getHost().get(0).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(1.4 - 
				sites.get(3).getHosts().getHost().get(1).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(2.3 - 
				sites.get(3).getHosts().getHost().get(2).getLoadAvg1Min()), lessThan(0.001));
		assertThat(Math.abs(3.3 - 
				sites.get(3).getHosts().getHost().get(3).getLoadAvg1Min()), lessThan(0.001));
		
	}
}
