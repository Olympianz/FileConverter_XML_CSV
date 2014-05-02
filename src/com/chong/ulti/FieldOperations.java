package com.chong.ulti;

import java.lang.reflect.Field;

/**
 * FieldOperations class:
 * 	1. matchConstraint(T record1, String fieldName, String fieldValue) 
 * 		throws NoSuchFieldException;
 * 	2. String getFieldValue(T record, String fieldName) throws NoSuchFieldException;
 * 	3. boolean haveField(String fieldName) throws NoSuchFieldException
 * 
 * @author NickyYue
 *
 * @param <T>
 */

public class FieldOperations<T> {
	
	private T record = null;
	public FieldOperations(T record1) {
		this.record = record1;
	}

	
	/**
	 * Method takes fieldName, fieldValue and record1 as parameters
	 * and compares the values to decide if this record matches the constraint.
	 * 
	 * Invalid criteria: 1. Invalid fieldname; 2. Invalid fieldvalue 
	 * 	only returns "false", no exception throws.
	 * 
	 * @param record1
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 * 		<p> "true", if object contains the fieldname and has the same fieldvalue </p>
	 * 		<p> "false", if object doesn't contain the fieldname or fieldvalue doesn't match </p>
	 */
	public boolean matchConstraint(T record1, String fieldName, Object fieldValue) {
		if (haveField(fieldName)) 
			return getFieldValue(record1, fieldName).equals(fieldValue);
		else 
			return false;
	}
	
	/**
	 * Check if data Object has the field specified by "fieldname"
	 * 
	 * @param record
	 * @param fieldName
	 * @return
	 * 		"null", if class doesn't have the given field
	 * 		value in the field
	 *  
	 */
	public Object getFieldValue(T record, String fieldName) {
		Object value = null;
		
		if (haveField(fieldName)) {
			try {
				Field field = record.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				value = field.get(record);
				
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} 
		}
		return value;
	}
	
	
	/**
	 * Check if object has such field
	 * 
	 * @param fieldName
	 * @return
	 * 		<p> "true", if object contains this field </p>
	 * 		<p> "false", if object doesn't contain this field </p>
	 */
	public boolean haveField(String fieldName) {
		
		try {
			record.getClass().getDeclaredField(fieldName);

		} catch (SecurityException | NoSuchFieldException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
