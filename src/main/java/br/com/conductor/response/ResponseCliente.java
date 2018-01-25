package br.com.conductor.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe utilizada para respostas no @ClienteController
 * @author Jhone
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCliente extends Response{

	private String nome;

	private String sexo;

	private Integer idade;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="###.###.###-##")
	private String cpf;

	private String endereco;

	private BigDecimal saldo;
	
	public ResponseCliente() {
	}
	
	public ResponseCliente(String codigo, String mensagem) {
		super(codigo, mensagem);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
