package br.com.urbainski.backend.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
public class Endereco {

	@Size(min = 8, max = 8, message = "{endereco.cep.Size}")
	@NotEmpty(message = "{endereco.cep.NotEmpty}")
	private String cep;
	
	@NotEmpty(message = "{endereco.logradouro.NotEmpty}")
	private String logradouro;
	
	@NotEmpty(message = "{endereco.bairro.NotEmpty}")
	private String bairro;
	
	@NotEmpty(message = "{endereco.numero.NotEmpty}")
	private String numero;
	
	@NotEmpty(message = "{endereco.municipio.NotEmpty}")
	private String municipio;
	
	@NotEmpty(message = "{endereco.estado.NotEmpty}")
	private String siglaEstado;
	
	private String complemento;
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getSiglaEstado() {
		return siglaEstado;
	}
	
	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", numero=" + numero
				+ ", municipio=" + municipio + ", siglaEstado=" + siglaEstado + ", complemento=" + complemento + "]";
	}
	
	public static enum Campos {
		
		cep,
		
		logradouro,
		
		bairro,
		
		numero,
		
		municipio,
		
		siglaEstado,
		
		complemento;
		
	}
	
}