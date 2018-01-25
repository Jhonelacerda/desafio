package br.com.conductor.response;

/**
 * Classe utilizada para respostas no @ClienteController
 * @author Jhone
 *
 */
public class Response {
	
	private String codigo;
	private String mensagem;
	
	public Response(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public Response() {
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
