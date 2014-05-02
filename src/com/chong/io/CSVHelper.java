package com.chong.io;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.chong.entity.wrapper.Host;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public class CSVHelper implements IOHelper<List<Host>> {

	@Override
	public List<Host> readFromFile(String path) throws IOException {
		List<Host> hosts = new ArrayList<Host>();
		Host host = null;
		
		Reader reader = new FileReader(path);
		CSVReader<String[]> csvHostReader = CSVReaderBuilder.newDefaultReader(reader);
		List<String[]> hosts_withHeader = csvHostReader.readAll();
		for (int i=1; i<hosts_withHeader.size(); i++) {
			host = generateHostFromString( (hosts_withHeader.get(i))[0] );
			hosts.add(host);
		}
		
		return hosts;
	}

	@Override
	public void writeToFile(List<Host> obj_hosts, String filePath) throws IOException {
		Writer out = new OutputStreamWriter(new FileOutputStream(filePath));
		String content = MyCSVEntryConverter.generateHeader() + "\n"
				+ MyCSVEntryConverter.convertEntries(obj_hosts);
		
		try {
			out.write(content);
			
		} finally {
			out.close();
		}
		
	}

	
	private Host generateHostFromString(String input) {
		Host host = new Host();
		
		String[] tokens = input.split(", ");
		host.setSite_id(Integer.parseInt(tokens[0]));
		host.setSite_name(tokens[1]);
		host.setSite_location(tokens[2]);
		host.setHost_id(Integer.parseInt(tokens[3]));
		host.setHost_name(tokens[4]);
		host.setIp_address(tokens[5]);
		host.setOperative_system(tokens[6]);
		host.setLoad_avg_1min(Float.parseFloat(tokens[7]));
		host.setLoad_avg_5min(Float.parseFloat(tokens[8]));
		host.setLoad_avg_15min(Float.parseFloat(tokens[9]));
		
		return host;
	}
	
}
