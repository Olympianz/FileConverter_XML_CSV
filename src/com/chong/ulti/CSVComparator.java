package com.chong.ulti;

import java.util.Map;

import com.chong.entity.wrapper.Host;

public class CSVComparator extends AbstractComparator<Host>{

	public CSVComparator(Map<String, Boolean> sortingFields,
			FieldOperations<Host> operator) {
		super(sortingFields, operator);
	}

}
