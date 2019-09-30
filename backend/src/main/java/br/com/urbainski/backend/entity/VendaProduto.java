package br.com.urbainski.backend.entity;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public class VendaProduto {

	@NotNull(message = "{vendaProduto.produto.NotNull}")
	private Produto produto;

	@NotNull(message = "{vendaProduto.valorVenda.NotNull}")
	@DecimalMin(value = "0.01", message = "{vendaProduto.valorVenda.DecimalMin}")
	private BigDecimal valorVenda;

	@NotNull(message = "{vendaProduto.quantidade.NotNull}")
	@DecimalMin(value = "0.01", message = "{vendaProduto.quantidade.DecimalMin}")
	private BigDecimal quantidade;

	@NotNull(message = "{vendaProduto.valorTotal.NotNull}")
	@DecimalMin(value = "0.01", message = "{vendaProduto.valorTotal.DecimalMin}")
	private BigDecimal valorTotal;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public static enum Campos {

		produto,

		valorVenda,

		quantidade,

		valorTotal;

	}

}