import { Component, OnInit } from '@angular/core';
import { CompraService } from 'src/app/service/compra.service';
import { Compra } from 'src/app/entity/compra';
import { BsModalRef, BsModalService, ModalOptions } from 'ngx-bootstrap';
import { ModalCompraProdutosComponent } from './modal-compra-produtos/modal-compra-produtos.component';

@Component({
	selector: 'app-lista-compras',
	templateUrl: './lista-compras.component.html',
	styleUrls: ['./lista-compras.component.css']
})
export class ListaComprasComponent implements OnInit {

	public totalItens : number = 0;

	public itemsPerPage : number = 20;

	public currentPage : number = 1;

	public compras : Compra[] = [];

	private bsModalRef : BsModalRef;

	constructor(
		private compraService : CompraService,
		private modalService : BsModalService) { }

	ngOnInit() {
		this.findAll(this.currentPage, this.itemsPerPage);
	}

	pageChanged(event : any) {
		let page : number = event.page - 1;
		this.findAll(page, this.itemsPerPage);
	}

	openModalProdutos(compra : Compra) {
		let modalOptions = new ModalOptions();
		modalOptions.class = 'modal-xl';
		modalOptions.backdrop = 'static';
		modalOptions.ignoreBackdropClick = true;
		modalOptions.initialState = {
			'produtos' : compra.produtos,
			'class': 'modal-xl'
		};
		this.bsModalRef = this.modalService.show(ModalCompraProdutosComponent, modalOptions);
	}

	private findAll(page : number, size : number) {
		this.compraService.findAll(page, size).subscribe((res) => {
			this.compras = res['data'];
			this.totalItens = res['total'];
		})
	}

}
