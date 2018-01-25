package br.com.conductor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.bean.Cliente;
import br.com.conductor.repository.ClienteRepository;
import br.com.conductor.request.Request;
import br.com.conductor.response.Response;
import br.com.conductor.response.ResponseCliente;
import br.com.conductor.service.ClienteService;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	/**
	 * @api {post} /cliente Cadastra um novo cliente.
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
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> cadastrarCliente(@RequestBody Request request) {
		Response response = service.cadastrarCliente(request);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * @api {delete} /cliente Exclui um cliente informado, utilizando o cpf
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
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Response> excluirCliente(@RequestBody Request request) {
		Response response = service.excluirCliente(request.getCpf());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * @api {get} /cliente/{cpf} Realiza a consulta de um cliente
	 * @apiParamExample {json} Request-Example: 
	 * 
	 * http://localhost:8080/cliente/22222222222
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
	@RequestMapping(method = RequestMethod.GET, value = "/{cpf}")
	public ResponseEntity<ResponseCliente> consultarCliente(@PathVariable("cpf") String cpf) {
		ResponseCliente response = service.consultarCliente(cpf);
		return new ResponseEntity<ResponseCliente>(response, HttpStatus.OK);
	}
	
	/**
	 * @api {put} /cliente Altera um cliente existente, utilizando o cpf
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
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ResponseCliente> editarCliente(@RequestBody Cliente request) {
		ResponseCliente response = service.editarCliente(request);
		return new ResponseEntity<ResponseCliente>(response, HttpStatus.OK);
	}

	/**
	 * @api {get} /cliente Lista todos os clientes existentes
	 * @apiParamExample {json} Request-Example: 
	 * 
	 * http://localhost:8080/cliente?page=1&size=1&sort=nome
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
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listarClientes(Pageable page) {
		Object response = service.listarClientes(page);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}
