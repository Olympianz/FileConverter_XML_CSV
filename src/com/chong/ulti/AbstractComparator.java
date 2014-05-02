package com.chong.ulti;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;

/**
 * The abstract class implements Comparator<T>
 * 	It implements the compare function based on multiple criteria.
 * 
 * @author NickyYue
 *
 * @param <T>
 */
public abstract class AbstractComparator<T> implements Comparator<T> {
	
	private FieldOperations<T> operator;
	private Map<String, Boolean> sortingFields;
	
	public AbstractComparator(Map<String, Boolean> sortingFields, 
			FieldOperations<T> operator) {
		super();
		this.sortingFields = sortingFields;
		this.operator = operator;
	}

	public Map<String, Boolean> getSortingFields() {
		return sortingFields;
	}

	public void setSortingFields(Map<String, Boolean> sortingFields) {
		this.sortingFields = sortingFields;
	}

	public FieldOperations<T> getOperator() {
		return operator;
	}

	public void setOperator(FieldOperations<T> operator) {
		this.operator = operator;
	}

	/**
	 * Compare provided fields of object. 
	 * Map<String, Boolean> fields:
	 * 	1. String, is fieldName used for sorting
	 * 	2. Boolean, is flag that indicates if it's ascending/descending
	 * 		"true" for ascending
	 * 		"false" for descending
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(T o1, T o2) {
		int c = 0;
		
		for (String fieldName : getSortingFields().keySet()) {
			if (operator.haveField(fieldName)) {
//				String v1 = operator.getFieldValue(o1, fieldName).toString();
//				String v2 = operator.getFieldValue(o2, fieldName).toString();
				
				Object v1 = operator.getFieldValue(o1, fieldName);
				Object v2 = operator.getFieldValue(o2, fieldName);

				Method method_compareTo = null;
				
				try {
					method_compareTo = v1.getClass().getMethod("compareTo", v1.getClass());
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				
				// deal "null" values
				if (v1==null)
					c = -1;
				else if (v2==null)
					c = 1;
				else
					try {
						c = (int) method_compareTo.invoke(v1, v2);
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				
				// check if descending
				if (getSortingFields().get(fieldName) == false) {
					c = -c;
				}
				
				// check result
				if (c!=0) 
					break;
				else 
					continue;
				
			} 
		}
		return c;
	}

}
