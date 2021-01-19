package edu.njupt.springmvc;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperTest {
	private final ObjectMapper om = new ObjectMapper();
	
	@Test
	@Ignore
	public void testIfPropertyIsNull() throws JsonProcessingException {
//		Map<String, Object> map = new HashMap<>(10);
//		map.put("key", null);
//		map.put("value", "this value");
//		map.put("list", List.of("abc", "de"));
//		String value = om.writeValueAsString(map);
		Bean bean = new Bean();
		String value = om.writeValueAsString(bean);
		System.out.println(value);
	}
	
	public static class Bean{
		
	}
}
