import { Component, OnInit } from '@angular/core';
import { Produto } from 'src/app/entity/produto';
import { ProdutoService } from 'src/app/service/produto.service';
import { BsModalRef } from 'ngx-bootstrap';
import { Subject } from 'rxjs';

@Component({
	selector: 'app-lista-produtos',
	templateUrl: './lista-produtos.component.html',
	styleUrls: ['./lista-produtos.component.css']
})
export class ListaProdutosComponent implements OnInit {

	public totalItens : number = 0;

	public itemsPerPage : number = 20;

	public currentPage : number = 1;

	public produtos : Produto[] = [];

	public onClose : Subject<Produto>;

	public modal : boolean;

	public tituloTela : string;

	constructor(
		private produtoService : ProdutoService,
		public bsModalRef : BsModalRef) { }

	ngOnInit() {
		this.onClose = new Subject();
		this.findAll(this.currentPage, this.itemsPerPage);

		this.tituloTela = 'Seleção de produtos';
		if (!this.modal) {
			this.tituloTela = 'Pesquisa de produtos';
		}
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
