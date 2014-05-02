package com.chong.ulti;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceFilter_write extends XMLFilterImpl {

	 private static final String NAMESPACE = "http://example.ipsoft.com/coding.xsd";
	
	@Override
	public void endElement(String uri, String localName, String qName)
	        throws SAXException {
		if (qName.equalsIgnoreCase("site")) {
    		super.endElement(NAMESPACE, localName, qName);
    	} else {
    		super.endElement(null, localName, qName);
    	}
	}
	 
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
    	if (qName.equalsIgnoreCase("site")) {
			super.startElement(NAMESPACE, localName, qName, atts);
		} else {
			super.startElement(null, localName, qName, atts);
		}
    }	
}