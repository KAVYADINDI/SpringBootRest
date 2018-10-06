package com.capgemini.productapp.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ControllerTest {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void addProductTest() throws Exception {

		String content = "{\"productId\" : 123,\r\n" + "	\"productName\" : \"vivo\",\r\n"
				+ "	\"productCategory\" : \"mobile\",\r\n" + "	\"productPrice\" : 12000}";

		when(productService.addProduct(Mockito.isA(Product.class)))
				.thenReturn(new Product(123, "vivo", "mobile", 12000.0));

//		mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(content).accept(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.productId").value(123)).andDo(print());

		
		mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists()).andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(123)).andExpect(jsonPath("$.productName").value("vivo"))
				.andExpect(jsonPath("productCategory").value("mobile"))
				.andExpect(jsonPath("$.productPrice").value(12000.0)).andDo(print());
	}

	@Test
	public void updateProductTest() throws Exception {
		String content = "{\"productId\" : 123}"; //only id is what that matters here.

		when(productService.updateProduct(Mockito.isA(Product.class)))
				.thenReturn(new Product(123, "oppo", "mobile", 150000.0));
		when(productService.findProductById(123)).thenReturn(new Product(123, "oppo", "mobile", 12000.0));

		mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists()).andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(123)).andExpect(jsonPath("$.productName").value("oppo"))
				.andExpect(jsonPath("productCategory").value("mobile"))
				.andExpect(jsonPath("$.productPrice").value(150000.0)).andDo(print());
	}

	@Test
	public void testFindProductById() throws Exception {

		when(productService.findProductById(123)).thenReturn(new Product(123, "vivo", "mobile", 12000.0));

		mockMvc.perform(MockMvcRequestBuilders.get("/product/123").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists()).andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists()).andExpect(jsonPath("$.productId").value(123))
				.andExpect(jsonPath("$.productName").value("vivo"))
				.andExpect(jsonPath("$.productCategory").value("mobile"))
				.andExpect(jsonPath("$.productPrice").value(12000.0)).andDo(print());
	}

	@Test
	public void testDeleteproduct() throws Exception {
		when(productService.findProductById(1234)).thenReturn(new Product(123, "vivo", "mobile", 12000.0));
		mockMvc.perform(delete("/product/1234").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
