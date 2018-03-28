package com.revature.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {
	private static ObjectMapper ob;
	
	public static synchronized ObjectMapper getInstance() {
		if (ob == null)
			ob = new ObjectMapper();
		
		return ob;
	}
}
