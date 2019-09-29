package br.com.urbainski.backend.entity;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public class Produto {

	private String id;

	@NotEmpty(message = "{produto.nome.NotEmpty}")
	@Size(min = 5, message = "{produto.nome.Size}")
	private String nome;

	@NotEmpty(message = "{produto.marca.NotEmpty}")
	private String marca;

	@NotNull(message = "{produto.valorVenda.NotNull}")
	@DecimalMin(value = "0.01", message = "{produto.valorVenda.DecimalMin}")
	private BigDecimal valorVenda;

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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", marca=" + marca + ", valorVenda=" + valorVenda + "]";
	}
	
	public static enum Campos {
		
		_id,
		
		nome,
		
		marca,
		
		valorVenda;
		
	}

}