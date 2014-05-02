package com.chong.io;

public interface IOHelper<T> {

	/**
	 * Read from file and store data in list<T>
	 * 
	 * @param path
	 * @return
	 * 		List<T> 
	 */
	T readFromFile(String path) throws Exception;
	
	
	/**
	 * Write data to file with given path
	 * 
	 * @param data
	 */
	void writeToFile(T data, String filePath) throws Exception;


}
