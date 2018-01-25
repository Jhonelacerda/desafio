package br.com.conductor.util;

import org.springframework.util.StringUtils;

import br.com.conductor.bean.Cliente;
import br.com.conductor.request.Request;
import br.com.conductor.response.ResponseCliente;

/**
 * Classe utilitária para validações
 * @author Jhone
 *
 */
public class ClienteUtil {
	
	/**
	 * Valida a nulabilidade dos campos do @Cliente
	 * @param cliente
	 * @return
	 */
	public static boolean validarCampos(Request cliente) {
		if (!StringUtils.isEmpty(cliente.getCpf()) && !StringUtils.isEmpty(cliente.getEndereco())
				&& !StringUtils.isEmpty(cliente.getIdade()) && !StringUtils.isEmpty(cliente.getNome())
				&& !StringUtils.isEmpty(cliente.getSaldo()) && !StringUtils.isEmpty(cliente.getSexo())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método utilizado para alterar os valores informados do cliente.
	 * @param clienteNovo
	 * @param clienteAntigo
	 * @return Retorna o @Cliente alterado.
	 */
	public static Cliente validarAlteracao(Cliente clienteNovo, Cliente clienteAntigo) {
				
		clienteAntigo.setNome(!StringUtils.isEmpty(clienteNovo.getNome()) ? 
				clienteNovo.getNome() : clienteAntigo.getNome());
		
		clienteAntigo.setEndereco(!StringUtils.isEmpty(clienteNovo.getEndereco()) ? 
				clienteNovo.getEndereco() : clienteAntigo.getEndereco());
		
		clienteAntigo.setIdade(!StringUtils.isEmpty(clienteNovo.getIdade()) ? 
				clienteNovo.getIdade() : clienteAntigo.getIdade());
		
		clienteAntigo.setSaldo(!StringUtils.isEmpty(clienteNovo.getSaldo()) ? 
				clienteNovo.getSaldo() : clienteAntigo.getSaldo());
		
		clienteAntigo.setSexo(!StringUtils.isEmpty(clienteNovo.getSexo()) ? 
				clienteNovo.getSexo() : clienteAntigo.getSexo());

		return clienteAntigo;
	}
	
	public static ResponseCliente response(String codigo, String mensagem) {
		return new ResponseCliente(codigo, mensagem);
	}

}
