package br.com.conductor.util;

public enum EnumResponse {
	
	SUCESSO("00", "Realizado com sucesso"),
	INFORMAR_CAMPO("94", "Favor informar todos os campos"),
	INFORMAR_CPF("95", "Favor informar o CPF do cliente"),
	CLIENTE_DUPLICADO("96", "O cliente já existe"),
	CLIENTE_NAO_EXISTE("97","Cliente inexistente"),
	ERRO("99", "Erro Interno");
	
	private String codigo;
	private String mensagem;
	

	private EnumResponse(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
