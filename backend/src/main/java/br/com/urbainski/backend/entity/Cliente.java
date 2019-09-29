package br.com.urbainski.backend.entity;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.urbainski.backend.annotation.NumeroDocumento;
import br.com.urbainski.backend.entity.enums.TipoPessoa;

/**
 * 
 * @author Cristian Urbainski
 * @since 27/09/2019
 *
 */
public class Cliente {

	private String id;

	@NotEmpty(message = "{cliente.nome.NotEmpty}")
	@Size(min = 5, message = "{cliente.nome.Size}")
	private String nome;

	@NotEmpty(message = "{cliente.email.NotEmpty}")
	@Email(message = "{cliente.email.Email}")
	private String email;

	@NotNull(message = "{cliente.tipoPessoa.NotNull}")
	private TipoPessoa tipoPessoa;

	@NumeroDocumento
	@NotEmpty(message = "{cliente.numeroDocumento.NotEmpty}")
	private String numeroDocumento;

	@NotNull
	@Valid
	private Endereco endereco;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", tipoPessoa=" + tipoPessoa
				+ ", numeroDocumento=" + numeroDocumento + ", endereco=" + endereco + "]";
	}

	public static enum Campos {

		_id,

		nome,

		email,

		tipoPessoa,

		numeroDocumento,

		endereco;

	}
}