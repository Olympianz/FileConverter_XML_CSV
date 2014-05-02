package com.chong.io.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.chong.entity.wrapper.Host;
import com.chong.io.CSVHelper;

public class CSVHelperTest {
	private static String samplePath = "samples/ipsoft_perf_counters_csv_sample_data.txt";
	private static String testCSVPath = "samples/test_csv.txt";
	
	@Test
		(expected=IOException.class)
	public void testReadFromFile() throws IOException {
		CSVHelper helper = new CSVHelper();
		List<Host> hosts;
		
		hosts = helper.readFromFile(samplePath);
		assertThat(hosts.get(0).getSite_id(), equalTo(101));
		assertThat(hosts.get(0).getSite_name(), is("NY-01"));
		assertThat(hosts.get(0).getSite_location(), is("New York"));
		assertThat(hosts.get(0).getHost_id(), equalTo(1001));
		assertThat(hosts.get(0).getHost_name(), is("srv001001"));
		assertThat(hosts.get(0).getIp_address(), is("10.1.2.3"));
		assertThat(hosts.get(0).getOperative_system(), is("Windows"));
		assertThat(Math.abs(1.3 - hosts.get(0).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(2.5 - hosts.get(0).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.2 - hosts.get(0).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(1).getSite_id(), equalTo(101));
		assertThat(hosts.get(1).getSite_name(), is("NY-01"));
		assertThat(hosts.get(1).getSite_location(), is("New York"));
		assertThat(hosts.get(1).getHost_id(), equalTo(1002));
		assertThat(hosts.get(1).getHost_name(), is("srv001002"));
		assertThat(hosts.get(1).getIp_address(), is("10.1.2.4"));
		assertThat(hosts.get(1).getOperative_system(), is("Linux"));
		assertThat(Math.abs(1.4 - hosts.get(1).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(2.5 - hosts.get(1).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.2 - hosts.get(1).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(2).getSite_id(), equalTo(101));
		assertThat(hosts.get(2).getSite_name(), is("NY-01"));
		assertThat(hosts.get(2).getSite_location(), is("New York"));
		assertThat(hosts.get(2).getHost_id(), equalTo(1003));
		assertThat(hosts.get(2).getHost_name(), is("srv001003"));
		assertThat(hosts.get(2).getIp_address(), is("10.1.2.5"));
		assertThat(hosts.get(2).getOperative_system(), is("Linux"));
		assertThat(Math.abs(3.3 - hosts.get(2).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(1.6 - hosts.get(2).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.8 - hosts.get(2).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(3).getSite_id(), equalTo(101));
		assertThat(hosts.get(3).getSite_name(), is("NY-01"));
		assertThat(hosts.get(3).getSite_location(), is("New York"));
		assertThat(hosts.get(3).getHost_id(), equalTo(1004));
		assertThat(hosts.get(3).getHost_name(), is("srv001004"));
		assertThat(hosts.get(3).getIp_address(), is("10.1.2.6"));
		assertThat(hosts.get(3).getOperative_system(), is("Linux"));
		assertThat(Math.abs(2.3 - hosts.get(3).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(4.5 - hosts.get(3).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(4.2 - hosts.get(3).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(4).getSite_id(), equalTo(201));
		assertThat(hosts.get(4).getSite_name(), is("CA-01"));
		assertThat(hosts.get(4).getSite_location(), is("California-LA"));
		assertThat(hosts.get(4).getHost_id(), equalTo(2101));
		assertThat(hosts.get(4).getHost_name(), is("srv002101"));
		assertThat(hosts.get(4).getIp_address(), is("172.168.3.23"));
		assertThat(hosts.get(4).getOperative_system(), is("Solaris"));
		assertThat(Math.abs(5.3 - hosts.get(4).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(2.8 - hosts.get(4).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.9 - hosts.get(4).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(5).getSite_id(), equalTo(201));
		assertThat(hosts.get(5).getSite_name(), is("CA-01"));
		assertThat(hosts.get(5).getSite_location(), is("California-LA"));
		assertThat(hosts.get(5).getHost_id(), equalTo(2102));
		assertThat(hosts.get(5).getHost_name(), is("srv002102"));
		assertThat(hosts.get(5).getIp_address(), is("172.168.3.33"));
		assertThat(hosts.get(5).getOperative_system(), is("Solaris"));
		assertThat(Math.abs(3.3 - hosts.get(5).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(3.5 - hosts.get(5).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.8 - hosts.get(5).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(6).getSite_id(), equalTo(201));
		assertThat(hosts.get(6).getSite_name(), is("CA-01"));
		assertThat(hosts.get(6).getSite_location(), is("California-LA"));
		assertThat(hosts.get(6).getHost_id(), equalTo(2110));
		assertThat(hosts.get(6).getHost_name(), is("srv002110"));
		assertThat(hosts.get(6).getIp_address(), is("172.168.3.43"));
		assertThat(hosts.get(6).getOperative_system(), is("Solaris"));
		assertThat(Math.abs(1.2 - hosts.get(6).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(1.5 - hosts.get(6).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.6 - hosts.get(6).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(7).getSite_id(), equalTo(202));
		assertThat(hosts.get(7).getSite_name(), is("CA-02"));
		assertThat(hosts.get(7).getSite_location(), is("California-SF"));
		assertThat(hosts.get(7).getHost_id(), equalTo(2233));
		assertThat(hosts.get(7).getHost_name(), is("pc002233"));
		assertThat(hosts.get(7).getIp_address(), is("172.168.240.31"));
		assertThat(hosts.get(7).getOperative_system(), is("MacOS"));
		assertThat(Math.abs(1.5 - hosts.get(7).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(3.5 - hosts.get(7).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.5 - hosts.get(7).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(8).getSite_id(), equalTo(202));
		assertThat(hosts.get(8).getSite_name(), is("CA-02"));
		assertThat(hosts.get(8).getSite_location(), is("California-SF"));
		assertThat(hosts.get(8).getHost_id(), equalTo(2234));
		assertThat(hosts.get(8).getHost_name(), is("pc002234"));
		assertThat(hosts.get(8).getIp_address(), is("172.168.240.32"));
		assertThat(hosts.get(8).getOperative_system(), is("MacOS"));
		assertThat(Math.abs(1.3 - hosts.get(8).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(2.2 - hosts.get(8).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.4 - hosts.get(8).getLoad_avg_15min()), lessThan(0.001));
		
		assertThat(hosts.get(9).getSite_id(), equalTo(202));
		assertThat(hosts.get(9).getSite_name(), is("CA-02"));
		assertThat(hosts.get(9).getSite_location(), is("California-SF"));
		assertThat(hosts.get(9).getHost_id(), equalTo(2235));
		assertThat(hosts.get(9).getHost_name(), is("pc002235"));
		assertThat(hosts.get(9).getIp_address(), is("172.168.240.34"));
		assertThat(hosts.get(9).getOperative_system(), is("MacOS"));
		assertThat(Math.abs(1.4 - hosts.get(9).getLoad_avg_1min()), lessThan(0.001));
		assertThat(Math.abs(3.5 - hosts.get(9).getLoad_avg_5min()), lessThan(0.001));
		assertThat(Math.abs(1.2 - hosts.get(9).getLoad_avg_15min()), lessThan(0.001));
		
		hosts = helper.readFromFile("Wrong Path");
	}

	@Test
	public void testWriteToFile() throws IOException {
		/////////////////////////
		// Read from sample file
		/////////////////////////
		
		CSVHelper helper = new CSVHelper();
		List<Host> hosts;
		
		hosts = helper.readFromFile(samplePath);
		
		/////////////////////////
		// Write to test file
		/////////////////////////
		File file = new File(testCSVPath);
		helper.writeToFile(hosts, testCSVPath);
		
		assertThat(file.exists() && !file.isDirectory(), is(true));
	}

}
