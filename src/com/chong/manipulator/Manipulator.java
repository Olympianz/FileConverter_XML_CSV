package com.chong.manipulator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;


/**
 * DataShaping interface provides 4 methods
 * 1. Filter data with given criteria
 * 2. Sort data with given comparator
 * 
 * @author NickyYue
 *
 * @param <T>
 */
public interface Manipulator<T> {

	/**
	 * Filter data based on given criteria; 
	 * 	data should meet all criteria
	 * 
	 * @param List<T> oriData
	 * @param Map<String, String> criteria
	 * @return
	 * 		List<T> filteredData
	 */
	public List<T> filter(List<T> oriData, Map<String, String> criteria);
	
	
	/**
	 * Sort data given comparator
	 * 
	 * @param List<T> oriData
	 * @param Comparator<T> comparator
	 * @param fields
	 * 
	 */
	public void sort(List<T> oriData, Comparator<T> comparator, Map<String, Boolean> fields);
	
}
