package br.com.conductor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.conductor.bean.Cliente;
import br.com.conductor.dto.ResponseClienteDTO;
import br.com.conductor.dto.ResponseDTO;
import br.com.conductor.repository.ClienteRepository;
import br.com.conductor.util.ResponseEnun;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Método responsável por inserir um cliente na base de dados.
	 * @param cliente
	 * @return Retorna o objeto ResponseDTO que contém o código e a mensagem de resposta ao controller.
	 */
	public ResponseDTO cadastrarCliente(Cliente cliente) {
		ResponseDTO response = null;
		if (!StringUtils.isEmpty(cliente.getCpf()) && !StringUtils.isEmpty(cliente.getEndereco())
				&& !StringUtils.isEmpty(cliente.getIdade()) && !StringUtils.isEmpty(cliente.getNome())
				&& !StringUtils.isEmpty(cliente.getSaldo()) && !StringUtils.isEmpty(cliente.getSexo())) {
			try {
				Cliente clienteDuplicado = clienteRepository.findByCpf(cliente.getCpf());
				if (clienteDuplicado == null) {
					clienteRepository.save(cliente);
					response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
				} else {
					response = new ResponseDTO(ResponseEnun.CLIENTE_DUPLICADO.getCodigo(),
							ResponseEnun.CLIENTE_DUPLICADO.getMensagem());
				}
			} catch (Exception e) {
				response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
			}
		} else {
			response = new ResponseDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(),
					ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}

	/** 
	 * Método responsável por excluir um cliente da base de dados, utilizando o cpf.
	 * @param cpf
	 * @return Retorna o objeto ResponseDTO que contém o código e a mensagem de resposta ao controller.
	 */
	public ResponseDTO excluirCliente(String cpf) {
		ResponseDTO response = null;
		if (cpf != null) {
			try {
				Cliente cliente = clienteRepository.findByCpf(cpf);
				if (cliente != null) {
					clienteRepository.delete(cliente);
					response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
				} else {
					response = new ResponseDTO(ResponseEnun.CLIENTE_NAO_EXISTE.getCodigo(),
							ResponseEnun.CLIENTE_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
			}
		} else {
			response = new ResponseDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(),
					ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}

	/**
	 * Método responsável pela consulta de um cliente, utilizando o cpf.
	 * @param cpf
	 * @return Retorna o objeto ResponseClienteDTO que contém os atributos do cliente como resposta ao controller.
	 */
	public ResponseClienteDTO consultarCliente(String cpf) {
		ResponseClienteDTO responseCliente = null;
		if (cpf != null) {
			try {
				Cliente cliente = clienteRepository.findByCpf(cpf);
				if (cliente != null) {
					responseCliente = new ResponseClienteDTO();
					responseCliente.setCpf(cliente.getCpf());
					responseCliente.setEndereco(cliente.getEndereco());
					responseCliente.setIdade(cliente.getIdade());
					responseCliente.setNome(cliente.getNome());
					responseCliente.setSaldo(cliente.getSaldo());
					responseCliente.setSexo(cliente.getSexo());
				} else {
					responseCliente = new ResponseClienteDTO(ResponseEnun.CLIENTE_NAO_EXISTE.getCodigo(),
							ResponseEnun.CLIENTE_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				responseCliente = new ResponseClienteDTO(ResponseEnun.ERRO.getCodigo(),
						ResponseEnun.ERRO.getMensagem());
			}
		} else {
			responseCliente = new ResponseClienteDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(),
					ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return responseCliente;
	}

	/**
	 * Método responsável por alterar um cliente, a partir do seu cpf.
	 * @param clienteNovo
	 * @return Retorna o objeto ResponseClienteDTO que contém os atributos do cliente como resposta ao controller.
	 */
	public ResponseClienteDTO editarCliente(Cliente clienteNovo) {
		ResponseClienteDTO responseCliente = null;
		if (clienteNovo.getCpf() != null) {
			try {
				Cliente clienteAntigo = clienteRepository.findByCpf(clienteNovo.getCpf());
				if (clienteAntigo.getId() != null) {
					Cliente ClienteAtualizado = updateCliente(clienteNovo, clienteAntigo);
					responseCliente = new ResponseClienteDTO();
					responseCliente.setCpf(ClienteAtualizado.getCpf());
					responseCliente.setEndereco(ClienteAtualizado.getEndereco());
					responseCliente.setIdade(ClienteAtualizado.getIdade());
					responseCliente.setNome(ClienteAtualizado.getNome());
					responseCliente.setSaldo(ClienteAtualizado.getSaldo());
					responseCliente.setSexo(ClienteAtualizado.getSexo());
					responseCliente.setCodigo(ResponseEnun.SUCESSO.getCodigo());
					responseCliente.setMensagem(ResponseEnun.SUCESSO.getMensagem());
				} else {
					responseCliente = new ResponseClienteDTO(ResponseEnun.CLIENTE_NAO_EXISTE.getCodigo(),
							ResponseEnun.CLIENTE_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				responseCliente = new ResponseClienteDTO(ResponseEnun.ERRO.getCodigo(),
						ResponseEnun.ERRO.getMensagem());
			}
		} else {
			responseCliente = new ResponseClienteDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(),
					ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return responseCliente;
	}

	/**
	 * Método utilizado para alterar os valores informados do cliente e inserí-los na base.
	 * @param clienteNovo
	 * @param clienteAntigo
	 * @return Retorna o Cliente alterado.
	 */
	private Cliente updateCliente(Cliente clienteNovo, Cliente clienteAntigo) {
		if (!StringUtils.isEmpty(clienteNovo.getNome())) {
			clienteAntigo.setNome(clienteNovo.getNome());
		}
		if (!StringUtils.isEmpty(clienteNovo.getEndereco())) {
			clienteAntigo.setEndereco(clienteNovo.getEndereco());
		}
		if (!StringUtils.isEmpty(clienteNovo.getIdade())) {
			clienteAntigo.setIdade(clienteNovo.getIdade());
		}
		if (!StringUtils.isEmpty(clienteNovo.getSaldo())) {
			clienteAntigo.setSaldo(clienteNovo.getSaldo());
		}
		if (!StringUtils.isEmpty(clienteNovo.getSexo())) {
			clienteAntigo.setSexo(clienteNovo.getSexo());
		}

		return clienteRepository.save(clienteAntigo);
	}

	/**
	 *  Método utilizado para listar todos os clientes existentes.
	 * @return Retorna uma lista de objetos ResponseClienteDTO como resposta ao controller.
	 */
	public List<ResponseClienteDTO> listarClientes() {
		ResponseClienteDTO response = null;
		List<ResponseClienteDTO> responseList = new ArrayList<>();
		ResponseClienteDTO responseCliente = null;
		try {
			List<Cliente> listaClientes = clienteRepository.findAll();
			if (!listaClientes.isEmpty()) {
				for (Cliente cliente : listaClientes) {
					responseCliente = new ResponseClienteDTO();
					responseCliente.setCpf(cliente.getCpf());
					responseCliente.setEndereco(cliente.getEndereco());
					responseCliente.setIdade(cliente.getIdade());
					responseCliente.setNome(cliente.getNome());
					responseCliente.setSaldo(cliente.getSaldo());
					responseCliente.setSexo(cliente.getSexo());
					responseList.add(responseCliente);
				}
			} else {
				responseCliente = new ResponseClienteDTO(ResponseEnun.LISTA_VAZIA.getCodigo(),
						ResponseEnun.LISTA_VAZIA.getMensagem());
			}
		} catch (Exception e) {
			response = new ResponseClienteDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(),
					ResponseEnun.INFORMAR_CAMPO.getMensagem());
			responseList.add(response);
		}
		return responseList;
	}
}
