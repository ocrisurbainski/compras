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
public class CompraProduto {

	@NotNull(message = "{compraProduto.produto.NotNull}")
	private Produto produto;

	@NotNull(message = "{compraProduto.valorUnitario.NotNull}")
	@DecimalMin(value = "0.01", message = "{compraProduto.valorUnitario.DecimalMin}")
	private BigDecimal valorUnitario;

	@NotNull(message = "{compraProduto.quantidade.NotNull}")
	@DecimalMin(value = "0.01", message = "{compraProduto.quantidade.DecimalMin}")
	private BigDecimal quantidade;

	@NotNull(message = "{compraProduto.valorTotal.NotNull}")
	@DecimalMin(value = "0.01", message = "{compraProduto.valorTotal.DecimalMin}")
	private BigDecimal valorTotal;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
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

		valorUnitario,

		quantidade,

		valorTotal;

	}

}