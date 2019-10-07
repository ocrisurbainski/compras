import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { Cliente } from 'src/app/entity/cliente';
import { ModalOptions, BsModalService } from 'ngx-bootstrap';
import { ModalSelecaoClienteComponent } from '../../clientes/modal-selecao-cliente/modal-selecao-cliente.component';
import { Endereco } from 'src/app/entity/endereco';
import { CepPipe } from 'src/app/pipe/cep.pipe';
import { CompraProduto } from 'src/app/entity/compra-produto';
import { ModalSelecaoProdutoComponent } from '../../produto/modal-selecao-produto/modal-selecao-produto.component';
import { CompraCliente } from 'src/app/entity/compra-cliente';

@Component({
	selector: 'app-nova-compra',
	templateUrl: './nova-compra.component.html',
	styleUrls: ['./nova-compra.component.css']
})
export class NovaCompraComponent implements OnInit {

	private cepPipe : CepPipe;

	public formSubmitted : boolean;

	public formNovaCompra : FormGroup;

	constructor(
		private formBuilder: FormBuilder,
		private modalService : BsModalService) { }

	ngOnInit() {
		this.formNovaCompra = this.formBuilder.group({
			cliente: [''],
			clienteDesc: ['', Validators.required],
			enderecoEntrega: [''],
			enderecoEntregaDesc: ['', Validators.required],
			quantidadeTotal: [0],
			valorTotal: [0],
			produtos: new FormArray([])
		});

		this.addProdutoCompra();

		this.formSubmitted = false;
		this.cepPipe = new CepPipe();
	}

	get form() { 
		return this.formNovaCompra.controls; 
	}

	get formListaProdutos() {
		return this.form.produtos as FormArray;
	}

	openModalSelectCliente() {
		let modalOptions = new ModalOptions();
		modalOptions.class = 'modal-xl';
		modalOptions.backdrop = 'static';
		modalOptions.ignoreBackdropClick = true;
		modalOptions.initialState = {
		};
		this.modalService.show(ModalSelecaoClienteComponent, modalOptions).content.onClose.subscribe(result => {
			let nome : string = null;
			if (result != null) {
				nome = result.nome;
			}
			
			let clienteClone = JSON.parse(JSON.stringify(result))
			delete clienteClone.endereco;

			this.formNovaCompra.patchValue({
				cliente: clienteClone,
				clienteDesc: nome,
				enderecoEntrega: result.endereco,
				enderecoEntregaDesc: this.getDescricaoEndereco(result)
			});
		});
	}

	openModalSelectProduto(index : number) {
		let modalOptions = new ModalOptions();
		modalOptions.class = 'modal-xl';
		modalOptions.backdrop = 'static';
		modalOptions.ignoreBackdropClick = true;
		modalOptions.initialState = {
		};
		this.modalService.show(ModalSelecaoProdutoComponent, modalOptions).content.onClose.subscribe(result => {
			let nome : string = null;
			let preco : number = null;
			if (result != null) {
				nome = result.nome;
				preco = result.preco;
			}
			
			this.formListaProdutos.controls[index].patchValue({
				produto: result,
				produtoDesc: nome,
				quantidade: 1,
				valorUnitario: preco
			});
		});
	}

	addProdutoCompra() : void {

		var index : number = this.formListaProdutos.length;

		this.formListaProdutos.push(this.formBuilder.group({
			produto: [''],
			produtoDesc: ['', Validators.required],
			quantidade: ['', [Validators.required, Validators.min(1)]],
			valorUnitario: ['', [Validators.required, Validators.min(1)]],
			valorTotal: ['', [Validators.required, Validators.min(1)]]
		}));

		this.formListaProdutos.controls[index].get('quantidade').valueChanges.subscribe(() => {
			this.calcularValorTotal(index);
		});
		this.formListaProdutos.controls[index].get('valorUnitario').valueChanges.subscribe(() => {
			this.calcularValorTotal(index);
		});
	}
	
	removerProdutoCompra(index : number) : void {
		this.formListaProdutos.removeAt(index);
		if (this.formListaProdutos.length == 0) {
			this.addProdutoCompra();
		}
	}

	calcularValorTotal(index : number) {
		let quantidade : number = this.formListaProdutos.controls[index].get('quantidade').value;
		let valorUnitario : number = this.formListaProdutos.controls[index].get('valorUnitario').value;

		var valorTotalCalc : number = null;
		if (quantidade != null || valorUnitario != null) {
			valorTotalCalc = Number((quantidade * valorUnitario).toFixed(2));
		}

		this.formListaProdutos.controls[index].patchValue({
			valorTotal: valorTotalCalc
		});

		this.totalizarValoresProdutos();
	}

	totalizarValoresProdutos() : void {
		let quantidade : number = 0;
		let valor : number = 0;

		for (let i = 0; i < this.formListaProdutos.controls.length; i++) {
			let formGroup : FormGroup = this.formListaProdutos.controls[i];
			let itemQuantidade : number = formGroup.controls.quantidade.value;
			let itemValorTotal : number = formGroup.controls.valorTotal.value;
			
			if (itemQuantidade != null) {
				quantidade += itemQuantidade;
			}

			if (itemValorTotal != null) {
				valor += itemValorTotal;
			}
		}

		this.formNovaCompra.patchValue({
			quantidadeTotal: quantidade,
			valorTotal: valor
		});
	}

	getDescricaoEndereco(cliente : Cliente) : string {
		if (cliente == null || (typeof cliente === "string")) {
			return "";
		}

		let endereco : Endereco = cliente.endereco;
		if (endereco == null) {
			return "";
		}
		let desc : string = endereco.logradouro.toString();
		desc += ', ' + endereco.bairro.toString();
		desc += ', nº ' + endereco.numero.toString();
		desc += ', ' + endereco.municipio.toString();
		desc += ' - ' + endereco.siglaEstado.toString();
		desc += ', CEP ' + this.cepPipe.transform(endereco.cep.toString());
		return desc;
	}

	formNovaCompraSubmit() {
		this.formSubmitted = true;
		if (this.formNovaCompra.valid) {
			alert('ok');
		}
	}

}
