package com.chong.ulti;

import java.util.Map;

import com.chong.site.Site;


/**
 * Site Comparator takes multiple sorting conditions for compare function
 * 		It has a private DataObjectOperations instance for checking valid fieldname 
 * 		and returning fieldvalue
 * @author NickyYue
 *
 */
public class SiteComparator extends AbstractComparator<Site> {
	
	public SiteComparator(Map<String, Boolean> sortingFields, FieldOperations<Site> operator) {
		super(sortingFields, operator);
	}
}
