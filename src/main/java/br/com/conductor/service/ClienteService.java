package br.com.conductor.service;


import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.conductor.bean.Cliente;
import br.com.conductor.converter.ClienteConverter;
import br.com.conductor.repository.ClienteRepository;
import br.com.conductor.request.Request;
import br.com.conductor.response.Response;
import br.com.conductor.response.ResponseCliente;
import br.com.conductor.util.ClienteUtil;
import br.com.conductor.util.EnumResponse;

/**
 * Classe responsável pela lógica de negocio do cliente
 * @author Jhone
 *
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Método responsável por inserir um cliente na base de dados.
	 * @param request
	 * @return Retorna o objeto ResponseDTO que contém o código e a mensagem de resposta ao controller.
	 */
	public Response cadastrarCliente(Request request) {
		Response response = null;
		if (ClienteUtil.validarCampos(request)) {
			try {
				Cliente clienteDuplicado = clienteRepository.findByCpf(request.getCpf());
				if (clienteDuplicado == null) {
					clienteRepository.save(ClienteConverter.RequestToCliente(request));
					response = ClienteUtil.response(EnumResponse.SUCESSO.getCodigo(), EnumResponse.SUCESSO.getMensagem());
				} else {
					response = ClienteUtil.response(EnumResponse.CLIENTE_DUPLICADO.getCodigo(),
							EnumResponse.CLIENTE_DUPLICADO.getMensagem());
				}
			} catch (Exception e) {
				response = ClienteUtil.response(EnumResponse.ERRO.getCodigo(), EnumResponse.ERRO.getMensagem());
			}
		} else {
			response = ClienteUtil.response(EnumResponse.INFORMAR_CAMPO.getCodigo(),
					EnumResponse.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}

	/** 
	 * Método responsável por excluir um cliente da base de dados, utilizando o cpf.
	 * @param cpf
	 * @return Retorna o objeto ResponseDTO que contém o código e a mensagem de resposta ao controller.
	 */
	public Response excluirCliente(String cpf) {
		Response response = null;
		if (cpf != null) {
			try {
				Cliente cliente = clienteRepository.findByCpf(cpf);
				if (cliente != null) {
					clienteRepository.delete(cliente);
					response = ClienteUtil.response(EnumResponse.SUCESSO.getCodigo(), EnumResponse.SUCESSO.getMensagem());
				} else {
					response = ClienteUtil.response(EnumResponse.CLIENTE_NAO_EXISTE.getCodigo(),
							EnumResponse.CLIENTE_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				response = ClienteUtil.response(EnumResponse.ERRO.getCodigo(), EnumResponse.ERRO.getMensagem());
			}
		} else {
			response = ClienteUtil.response(EnumResponse.INFORMAR_CAMPO.getCodigo(),
					EnumResponse.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}

	/**
	 * Método responsável pela consulta de um cliente, utilizando o cpf.
	 * @param cpf
	 * @return Retorna o objeto ResponseClienteDTO que contém os atributos do cliente como resposta ao controller.
	 */
	public ResponseCliente consultarCliente(String cpf) {
		ResponseCliente responseCliente = null;
		if (cpf != null) {
			try {
				Cliente cliente = clienteRepository.findByCpf(cpf);
				if (cliente != null) {
					responseCliente = ClienteConverter.ClienteToResponseCliente(cliente);
				} else {
					responseCliente = ClienteUtil.response(EnumResponse.CLIENTE_NAO_EXISTE.getCodigo(),
							EnumResponse.CLIENTE_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				responseCliente = ClienteUtil.response(EnumResponse.ERRO.getCodigo(),
						EnumResponse.ERRO.getMensagem());
			}
		} else {
			responseCliente = ClienteUtil.response(EnumResponse.INFORMAR_CAMPO.getCodigo(),
					EnumResponse.INFORMAR_CAMPO.getMensagem());
		}
		return responseCliente;
	}

	/**
	 * Método responsável por alterar um cliente, a partir do seu cpf.
	 * @param clienteNovo
	 * @return Retorna o objeto ResponseClienteDTO que contém os atributos do cliente como resposta ao controller.
	 */
	public ResponseCliente editarCliente(Cliente clienteNovo) {
		ResponseCliente responseCliente = null;
		if (clienteNovo.getCpf() != null) {
			try {
				Cliente clienteAntigo = clienteRepository.findByCpf(clienteNovo.getCpf());
				if (clienteAntigo != null) {
					Cliente ClienteAtualizado = ClienteUtil.validarAlteracao(clienteNovo, clienteAntigo);
					clienteRepository.save(ClienteAtualizado);
					responseCliente = ClienteConverter.ClienteToResponseCliente(ClienteAtualizado);
					responseCliente.setCodigo(EnumResponse.SUCESSO.getCodigo());
					responseCliente.setMensagem(EnumResponse.SUCESSO.getMensagem());
				} else {
					responseCliente = ClienteUtil.response(EnumResponse.CLIENTE_NAO_EXISTE.getCodigo(),
							EnumResponse.CLIENTE_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				responseCliente = ClienteUtil.response(EnumResponse.ERRO.getCodigo(),
						EnumResponse.ERRO.getMensagem());
			}
		} else {
			responseCliente = ClienteUtil.response(EnumResponse.INFORMAR_CAMPO.getCodigo(),
					EnumResponse.INFORMAR_CAMPO.getMensagem());
		}
		return responseCliente;
	}


	/**
	 *  Método utilizado para listar todos os clientes existentes por paginação.
	 * @return Retorna uma lista de objetos @Cliente.
	 */
	public Object listarClientes(Pageable pageble) {
		Response response = null;
		Page<Cliente> paginas = null;
		try {
			paginas = clienteRepository.findAll(pageble);
		} catch (Exception e) {
			response = ClienteUtil.response(EnumResponse.ERRO.getCodigo(), EnumResponse.ERRO.getMensagem());
			return response;
		}
		return paginas;
	}
}
