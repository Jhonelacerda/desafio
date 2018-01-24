package br.com.conductor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.bean.Cliente;
import br.com.conductor.dto.RequestDTO;
import br.com.conductor.dto.ResponseClienteDTO;
import br.com.conductor.dto.ResponseDTO;
import br.com.conductor.service.ClienteService;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	/**
	 * @api {put} /cliente/cadastrarCliente Cadastra um novo cliente.
	 * @apiParamExample {json} Request-Example: 
	 * {
	 * 		"nome": "cliente2",
	 * 		"sexo": "M",
	 * 		"idade": 22,
	 * 		"cpf": 22222222222,
	 * 		"endereco": "endereco2",
	 * 		"saldo": 222
	 * }
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"codigo": "00",
	 * 		"mensagem": "Realizado com sucesso"
	 * }
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/cadastrarCliente")
	public ResponseEntity<ResponseDTO> cadastrarCliente(@RequestBody Cliente request) {
		ResponseDTO response = service.cadastrarCliente(request);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	/**
	 * @api {delete} /cliente/excluirCliente Exclui um cliente informado, utilizando o cpf
	 * 
	 * @apiParamExample {json} Request-Example: 
	 *{
	 *		"cpf": 22222222222
	 *}
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"codigo": "00",
	 * 		"mensagem": "Realizado com sucesso"
	 * }
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/excluirCliente")
	public ResponseEntity<ResponseDTO> excluirCliente(@RequestBody RequestDTO request) {
		ResponseDTO response = service.excluirCliente(request.getCpf());
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	/**
	 * @api {get} /cliente/consultarCliente/{cpf} Realiza a consulta de um cliente
	 * @apiParamExample {json} Request-Example: 
	 * 
	 * http://localhost:8080/cliente/consultarCliente/22222222222
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"nome": "cliente2",
	 * 		"sexo": "M",
	 * 		"idade": 22,
	 * 		"cpf": 22222222222,
	 * 		"endereco": "endereco2",
	 * 		"saldo": 222
	 * }
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/consultarCliente/{cpf}")
	public ResponseEntity<ResponseClienteDTO> consultarCliente(@PathVariable("cpf") String cpf) {
		ResponseClienteDTO response = service.consultarCliente(cpf);
		return new ResponseEntity<ResponseClienteDTO>(response, HttpStatus.OK);
	}
	
	/**
	 * @api {put} /cliente/editarCliente Altera um cliente existente, utilizando o cpf
	 * @apiParamExample {json} Request-Example: 
	 * {
	 * 		"nome": "cliente1",
	 * 		"sexo": "F",
	 * 		"idade": 22,
	 * 		"cpf": 11111111111,
	 * 		"endereco": "endereco1",
	 * 		"saldo": 150
	 * }
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"nome": "cliente1",
	 * 		"sexo": "F",
	 * 		"idade": 22,
	 * 		"cpf": 11111111111,
	 * 		"endereco": "endereco1",
	 * 		"saldo": 150
	 * 		"codigo": "00",
     *		"mensagem": "Realizado com sucesso"
	 * }
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/editarCliente")
	public ResponseEntity<ResponseClienteDTO> editarCliente(@RequestBody Cliente request) {
		ResponseClienteDTO response = service.editarCliente(request);
		return new ResponseEntity<ResponseClienteDTO>(response, HttpStatus.OK);
	}

	/**
	 * @api {get} /cliente/listarClientes Lista todos os clientes existentes
	 * @apiParamExample {json} Request-Example: 
	 * 
	 * http://localhost:8080/cliente/listarClientes
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 *[
     *	{
     *  "nome": "cliente1",
     *   "sexo": "M",
     *   "idade": 1,
     *   "cpf": "11111111111",
     *   "endereco": "endereco1",
     *   "saldo": 111
     *	},
     *	{
     *   "nome": "cliente2",
     *   "sexo": "M",
     *   "idade": 22,
     *   "cpf": "22222222222",
     *   "endereco": "endereco2",
     *   "saldo": 222
     *	}
	 *]
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/listarClientes")
	public ResponseEntity<List<ResponseClienteDTO>> listarClientes() {
		List<ResponseClienteDTO> response = service.listarClientes();
		return new ResponseEntity<List<ResponseClienteDTO>>(response, HttpStatus.OK);
	}
	
}
