package br.com.conductor.converter;

import br.com.conductor.bean.Cliente;
import br.com.conductor.request.Request;
import br.com.conductor.response.ResponseCliente;

/**
 * Classe utilizada como conversor de objetos
 * @author Jhone
 *
 */
public class ClienteConverter {
	
	public static ResponseCliente ClienteToResponseCliente (Cliente cliente) {
		ResponseCliente responseCliente = new ResponseCliente();
		responseCliente.setCpf(cliente.getCpf());
		responseCliente.setEndereco(cliente.getEndereco());
		responseCliente.setIdade(cliente.getIdade());
		responseCliente.setNome(cliente.getNome());
		responseCliente.setSaldo(cliente.getSaldo());
		responseCliente.setSexo(cliente.getSexo());		
		return responseCliente;
		
	}

	public static Cliente RequestToCliente(Request request) {
		Cliente cliente = new Cliente();
		cliente.setCpf(request.getCpf());
		cliente.setEndereco(request.getEndereco());
		cliente.setIdade(request.getIdade());
		cliente.setNome(request.getNome());
		cliente.setSaldo(request.getSaldo());
		cliente.setSexo(request.getSexo());
		return cliente;
	}
}
