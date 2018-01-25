package br.com.conductor.request;

import java.math.BigDecimal;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe utilizada como requisição no @ClienteController
 * @author Jhone
 *
 */
public class Request {

	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("sexo")
	private String sexo;

	@JsonProperty("idade")
	private Integer idade;

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("endereco")
	private String endereco;

	@JsonProperty("saldo")
	private BigDecimal saldo;

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
