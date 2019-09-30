package br.com.urbainski.backend.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
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
public class Venda {

	private String id;

	@NotNull(message = "{venda.cliente.NotNull}")
	private VendaCliente cliente;

	@NotNull(message = "{venda.enderecoEntrega.NotNull}")
	private Endereco enderecoEntrega;

	@NotNull(message = "{venda.valorTotal.NotNull}")
	@DecimalMin(value = "0.01", message = "{venda.valorTotal.DecimalMin}")
	private BigDecimal valorTotal;

	@NotNull(message = "{venda.quantidadeTotal.NotNull}")
	@DecimalMin(value = "0.01", message = "{venda.quantidadeTotal.DecimalMin}")
	private BigDecimal quantidadeTotal;

	@Valid
	@NotNull(message = "{venda.produtos.NotNull}")
	@Size(min = 1, message = "{venda.produtos.Size}")
	private List<VendaProduto> produtos;

	private SituacaoEntrega situacaoEntrega;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VendaCliente getCliente() {
		return cliente;
	}

	public void setCliente(VendaCliente cliente) {
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

	public List<VendaProduto> getProdutos() {
		if (produtos == null) {
			produtos = new ArrayList<VendaProduto>();
		}
		return produtos;
	}

	public void setProdutos(List<VendaProduto> produtos) {
		this.produtos = produtos;
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

		produtos,

		situacaoEntrega;
	}
}