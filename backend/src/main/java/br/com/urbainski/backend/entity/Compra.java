package br.com.urbainski.backend.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.urbainski.backend.entity.enums.SituacaoEntrega;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public class Compra {

	private String id;

	@NotNull(message = "{compra.cliente.NotNull}")
	private CompraCliente cliente;

	@NotNull(message = "{compra.enderecoEntrega.NotNull}")
	private Endereco enderecoEntrega;

	@NotNull(message = "{compra.valorTotal.NotNull}")
	@DecimalMin(value = "0.01", message = "{compra.valorTotal.DecimalMin}")
	private BigDecimal valorTotal;

	@NotNull(message = "{compra.quantidadeTotal.NotNull}")
	@DecimalMin(value = "0.01", message = "{compra.quantidadeTotal.DecimalMin}")
	private BigDecimal quantidadeTotal;
	
	private Calendar dataCompra;

	@Valid
	@NotNull(message = "{compra.produtos.NotNull}")
	@Size(min = 1, message = "{compra.produtos.Size}")
	private List<CompraProduto> produtos;

	private SituacaoEntrega situacaoEntrega;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CompraCliente getCliente() {
		return cliente;
	}

	public void setCliente(CompraCliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(BigDecimal quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public List<CompraProduto> getProdutos() {
		if (produtos == null) {
			produtos = new ArrayList<CompraProduto>();
		}
		return produtos;
	}

	public void setProdutos(List<CompraProduto> produtos) {
		this.produtos = produtos;
	}
	
	public Calendar getDataCompra() {
		return dataCompra;
	}
	
	public void setDataCompra(Calendar dataCompra) {
		this.dataCompra = dataCompra;
	}

	public SituacaoEntrega getSituacaoEntrega() {
		return situacaoEntrega;
	}

	public void setSituacaoEntrega(SituacaoEntrega situacaoEntrega) {
		this.situacaoEntrega = situacaoEntrega;
	}

	public static enum Campos {

		_id,

		cliente,

		enderecoEntrega,

		valorTotal,

		quantidadeTotal,
		
		dataCompra,

		produtos,

		situacaoEntrega;
	}
}