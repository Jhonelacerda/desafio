package br.com.conductor.service;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.conductor.bean.Cliente;
import br.com.conductor.dto.ResponseDTO;
import br.com.conductor.util.ResponseEnun;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestClienteService {
	
//	@Autowired
//	private ClienteService service;
//	
//	@Test
//	public void TestCadastrarCliente() {
//		Cliente cliente = new Cliente();
//		cliente.setCpf("123");
//		cliente.setEndereco("endereco1");
//		cliente.setIdade(1);
//		cliente.setNome("cliente1");
//		cliente.setSaldo(new BigDecimal(111));
//		cliente.setSexo("M");
//		
//		ResponseDTO ClienteCadastrado = service.cadastrarCliente(cliente);
//		assertNotNull(ClienteCadastrado.getCodigo().equals(ResponseEnun.SUCESSO.getCodigo()));
//		assertNotNull(ClienteCadastrado.getMensagem().equals(ResponseEnun.SUCESSO.getMensagem()));
//	}
//	
//	@Test
//	public void TestExcluirCliente() {
//		
//	}
//	@Test
//	public void TestConsultarCliente() {
//		
//	}
//	@Test
//	public void TestEditarCliente() {
//		
//	}
//	@Test
//	public void TestListarClientes() {
//		
//	}

}
