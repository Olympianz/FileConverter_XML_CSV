package com.chong.manipulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.chong.ulti.FieldOperations;


/**
 * AbstractAdapter class implements Adapter interface
 * 	has two methods:
 * 	1. List<T> filter(List<T> oriData, Map<String, String> criteria)
 *  2. List<T> sort(List<T> oriData, Comparator<T> comparator)
 * 
 * @author NickyYue
 *
 * @param <T>
 */
public abstract class AbstractManipulator<T> implements Manipulator<T>{

	protected FieldOperations<T> operator;
	
	public AbstractManipulator(FieldOperations<T> operator) {
		super();
		this.operator = operator;
	}

	/* (non-Javadoc)
	 * @see com.chong.adapter.Adapter#filter(java.util.List, java.util.Map)
	 */
	@Override
	public List<T> filter(List<T> oriData, Map<String, String> criteria) {
		List<T> filteredData = new ArrayList<T>();
		boolean isMatch = true;
		for (T record : oriData) {
			for (Map.Entry<String, String> entry : criteria.entrySet()) {
				if (operator.matchConstraint(record, entry.getKey(), entry.getValue())) {
					continue;
				} else {
					isMatch = false;
					break;
				}
			}
			
			if (isMatch) {
				filteredData.add(record);
			}
			isMatch = true;
		}
		return filteredData;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.chong.adapter.Adapter#sort(java.util.List, java.util.Comparator)
	 */
	@Override
	public void sort(List<T> oriData, Comparator<T> comparator, Map<String, Boolean> fields) {
		Collections.sort(oriData, comparator);
	}

}
