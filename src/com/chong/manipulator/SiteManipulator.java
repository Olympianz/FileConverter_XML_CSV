package com.chong.manipulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.chong.site.Site;
import com.chong.site.Site.Hosts.Host;
import com.chong.ulti.FieldOperations;
import com.chong.ulti.HostComparator;


/**
 * XMLToCSVAdapter extends AbstractAdapter<Site>
 *  1. List<Site> filter(List<Site>, Map<String, String>)
 *  2. List<Site> sort(List<Site>, Comparator<T>)
 * 
 * @author NickyYue
 *
 */
public class SiteManipulator extends AbstractManipulator<Site> {
	
	private FieldOperations<Host> operator_host = new FieldOperations<Host>(new Host());
 	
	public SiteManipulator(FieldOperations<Site> operator) {
		super(operator);
	}
	
	/**
	 * Overridden "filter" function filters a List<Site> by checking fieldnames of "Site" object
	 * 	and fieldnames of nested "Host" Object as well.
	 * 
	 * @param sites
	 * @param criteria
	 * @return
	 * 		filtered sites
	 */
	@Override
	public List<Site> filter(List<Site> sites, Map<String, String> criteria) {
		List<Site> filteredData = new ArrayList<Site>();
		boolean isMatchSiteAttribute = true;
		boolean isMatchHostAttribute = true;
		
		for (Site site : sites) {
			for (Map.Entry<String, String> entry : criteria.entrySet()) {
				// if attribute is in Site object
				if (operator.matchConstraint(site, entry.getKey(), entry.getValue())) {
					continue;
				} else {
					isMatchSiteAttribute = false;
					
					// check if "hosts" attribute exists
					if (site.getHosts()==null) {
						isMatchHostAttribute = false;
						break;
					}
					List<Host> hosts = site.getHosts().getHost();
					
					
					// if attribute is in Host object 
					if (operator_host.haveField(entry.getKey())) {
						Site.Hosts site_hosts = new Site.Hosts();
						
						// filter hosts based on criteria
						for (Host host : hosts) {
							if (operator_host.matchConstraint(host, entry.getKey(), entry.getValue()))
								site_hosts.getHost().add(host);
						}
						
						// update site with sorted hosts, if there is a match
						if (site_hosts.getHost().size()>0) {
							site.setHosts(site_hosts);
							
							// reset flag for Site attribute
							isMatchSiteAttribute = true;
						} else {
							// 0 match in host
							isMatchHostAttribute = false;
							break;
						}
					} else {
						isMatchHostAttribute = false;
						break;
					}
				}
			}
			
			if (isMatchSiteAttribute || isMatchHostAttribute) {
				filteredData.add(site);
			}
			
			isMatchSiteAttribute = true;
			isMatchHostAttribute = true;
		}
		
		return filteredData;
	}
	
	/**
	 * Overridden "sort" function sorts a List<Site> by fieldnames of "Site" object
	 * 	and fieldnames of nested "Host" Object as well.
	 * 
	 * @param sites
	 * @return
	 * 		sorted sites
	 */
	@Override
	public void sort(List<Site> sites, Comparator<Site> site_comparator, 
			Map<String, Boolean> fields) {
		Comparator<Host> host_comparator = 
				new HostComparator(
						fields, new FieldOperations<Host>(new Host()));
		
		// sort hosts within site object
		for (Site site: sites) {
			if (site.getHosts()!=null) {
				List<Host> hosts = site.getHosts().getHost();
				Collections.sort(hosts, host_comparator);
			}
		}
		
		// sort sites
		Collections.sort(sites, site_comparator);
	}
}
