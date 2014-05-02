package com.chong.ulti;

import java.util.Map;

import com.chong.site.Site.Hosts.Host;

/**
 * Host Comparator takes multiple sorting conditions for compare function
 * 		It has a private DataObjectOperations instance for checking valid fieldname 
 * 		and returning fieldvalue
 * 
 * @author NickyYue
 *
 */
public class HostComparator extends AbstractComparator<Host>{
	
	public HostComparator(Map<String, Boolean> sortingFields,
			FieldOperations<Host> operator) {
		super(sortingFields, operator);
	}

}
