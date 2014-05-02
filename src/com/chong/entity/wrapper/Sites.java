package com.chong.entity.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.chong.site.Site;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Sites")
public class Sites {
	
	@XmlElement(name = "Site", type = Site.class)
	private List<Site> sites = new ArrayList<Site>();
	
	public Sites() {}
	
	public Sites(List<Site> sites) {
		this.sites = sites;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	
}
