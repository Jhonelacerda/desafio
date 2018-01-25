package br.com.conductor.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.conductor.controller.ClienteController;
import br.com.conductor.service.ClienteService;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class TestClienteController {
	
	private String url = "http://localhost:8080/cliente";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClienteService service;
	
	@Test
	public void testeConsultarCliente() throws Exception {
		
		MvcResult result = this.mvc.perform(get(url + "/22222222222").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String metodo = result.getRequest().getMethod();
		System.out.println(metodo);
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
//	@Test
//	public void testDeletar() throws Exception {
//		Response response = ClienteUtil.response(EnumResponse.SUCESSO.getCodigo(), EnumResponse.SUCESSO.getMensagem());
//		BDDMockito.when(service.excluirCliente("22222222222")).thenReturn(response);
//		MvcResult result = this.mvc.perform(get(url).accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		String metodo = result.getRequest().getMethod();
//		System.out.println(metodo);
//		
//		int status = result.getResponse().getStatus();
//		assertEquals(200, status);
//		
//	}
//	
//	@Test
//	public void testCadastrarCliente() throws Exception {
//		
//		MvcResult result = this.mvc.perform(get(url+"/cadastrarCliente").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		String metodo = result.getRequest().getMethod();
//		System.out.println(metodo);
//		
//		int status = result.getResponse().getStatus();
//		assertEquals(200, status);
//		
//	}

}
