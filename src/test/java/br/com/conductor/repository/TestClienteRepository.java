package br.com.conductor.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.conductor.bean.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestClienteRepository {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EntityManager manager;
	
	@Test
	public void TestSalvarCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setCpf("11111111111");
		cliente.setEndereco("endereco1");
		cliente.setIdade(1);
		cliente.setNome("cliente1");
		cliente.setSaldo(new BigDecimal(111));
		cliente.setSexo("M");
		
		Cliente ClienteCadastrado = repository.save(cliente);
		assertNotNull(ClienteCadastrado.getId());
	}
	
	@Test
	public void TestFindByCpf() {
		
		Cliente cliente = new Cliente();
		cliente.setCpf("11111111111");
		cliente.setEndereco("endereco1");
		cliente.setIdade(1);
		cliente.setNome("cliente1");
		cliente.setSaldo(new BigDecimal(111));
		cliente.setSexo("M");
		manager.persist(cliente);
		
		Cliente ClienteCadastrado = repository.findByCpf(cliente.getCpf());
		
		assertThat(ClienteCadastrado.getCpf().equals(cliente.getCpf()));
		assertThat(ClienteCadastrado.getNome().equals(cliente.getNome()));
		
	}

}
