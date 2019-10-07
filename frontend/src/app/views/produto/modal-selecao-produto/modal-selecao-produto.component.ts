import { Component, OnInit } from '@angular/core';
import { Produto } from 'src/app/entity/produto';
import { ProdutoService } from 'src/app/service/produto.service';
import { BsModalRef } from 'ngx-bootstrap';
import { Subject } from 'rxjs';

@Component({
	selector: 'app-modal-selecao-produto',
	templateUrl: './modal-selecao-produto.component.html',
	styleUrls: ['./modal-selecao-produto.component.css']
})
export class ModalSelecaoProdutoComponent implements OnInit {

	public totalItens : number = 0;

	public itemsPerPage : number = 20;

	public currentPage : number = 1;

	public produtos : Produto[] = [];

	public onClose : Subject<Produto>;

	constructor(
		private produtoService : ProdutoService,
		public bsModalRef : BsModalRef) { }

	ngOnInit() {
		this.onClose = new Subject();
		this.findAll(this.currentPage, this.itemsPerPage);
	}

	pageChanged(event : any) {
		let page : number = event.page - 1;
		this.findAll(page, this.itemsPerPage);
	}

	select(produto : Produto) : void {
		this.onClose.next(produto);
		this.bsModalRef.hide();
	}

	private findAll(page : number, size : number) {
		this.produtoService.findAll(page, size).subscribe((res) => {
			this.produtos = res['data'];
			this.totalItens = res['total'];
		})
	}
}
