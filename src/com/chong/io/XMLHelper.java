package com.chong.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.chong.entity.wrapper.Sites;
import com.chong.ulti.NamespaceFilter;

public class XMLHelper implements IOHelper<Sites> {
	
	private Sites sites;

	// Import
	@Override
	public Sites readFromFile(String path) throws 
		ParserConfigurationException, SAXException, IOException, JAXBException {
		
		// Prepare JAXB objects
    	JAXBContext jc = JAXBContext.newInstance(Sites.class);
    	Unmarshaller u = jc.createUnmarshaller();

    	// Create an XMLReader to use with our filter
    	XMLReader reader = XMLReaderFactory.createXMLReader();

    	// Create the filter (to add namespace) and set the xmlReader as its parent.
    	NamespaceFilter inFilter = new NamespaceFilter();
    	inFilter.setParent(reader);

    	// Prepare the input, in this case a java.io.File (output)
    	InputSource is = new InputSource(path);

    	// Create a SAXSource specifying the filter
    	SAXSource source = new SAXSource(inFilter, is);

    	// Do unmarshalling
    	setSites( (Sites)u.unmarshal(source) );
		
		return getSites();
	}

	// Export
	@Override
	public void writeToFile(Sites sites, String filePath) throws JAXBException, UnsupportedEncodingException, FileNotFoundException, SAXException {
		// Prepare JAXB objects
		JAXBContext jc = JAXBContext.newInstance(Sites.class);
		Marshaller m = jc.createMarshaller();

		// Define an output file
		File output = new File(filePath);

		// Do marshaller
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(sites, System.out);
		m.marshal(sites, output);
	}

	public Sites getSites() {
		return sites;
	}

	public void setSites(Sites sites) {
		this.sites = sites;
	}
	
}
