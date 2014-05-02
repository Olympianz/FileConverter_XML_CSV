package com.chong.ulti;
 
import org.xml.sax.*;
import org.xml.sax.helpers.XMLFilterImpl;
 
public class NamespaceFilter extends XMLFilterImpl {
 
//    private static final String NAMESPACE = "http://example.ipsoft.com/coding.xsd";
 
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
    	if (qName.equalsIgnoreCase("site")) {
    		super.endElement(null, localName, qName);
    	} else {
    		super.endElement(uri, localName, qName);
    	}
    }
 
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
    	if (qName.equalsIgnoreCase("site")) {
    		super.startElement(null, localName, qName, atts);
    	} else {
    		super.startElement(uri, localName, qName, atts);
    	}
    }
 
}