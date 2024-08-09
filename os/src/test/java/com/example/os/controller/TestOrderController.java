package com.example.os.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.os.entity.OrderModel;
import com.example.os.entity.PlaceOrder;
import com.example.os.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=OrderController.class)
public class TestOrderController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private OrderController orderController;
	@MockBean
	private OrderService orderService;
	
	@Test
	public void testGetMap() throws Exception {
		OrderModel om = new OrderModel();
		om.setCode("book");
		om.setName("Atomic habits");
		om.setStock((long) 20);
		
		Mockito.when(orderService.getProducts(om.getCode())).thenReturn(om);
		String uri = "/order/book";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				uri).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(om);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	/*@Test
	public void testPostMap() throws Exception {
		PlaceOrder po = new PlaceOrder();
		po.setCode("book");
		po.setStock((long) 12);
		
		String inputInJson = this.mapToJson(po);
		String URI = "/order/order save";
		Boolean result = true;
		if(orderService.placeOrder(po)) {
			result=true;
		}
		assertTrue(result);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result1 = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result1.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		
		
	}*/

}
