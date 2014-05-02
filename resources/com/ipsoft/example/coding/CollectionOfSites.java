package com.ipsoft.example.coding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Sites")
public class CollectionOfSites {

	@XmlElement(name = "Site", type = Site.class)
	private List<Site> sites = new ArrayList<Site>();
	
	public CollectionOfSites(){
	}
	
	public CollectionOfSites(List<Site> sites){
		this.sites = sites;
	}
	
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
	public List<Site> getSites() {
		return sites;
	}

}
