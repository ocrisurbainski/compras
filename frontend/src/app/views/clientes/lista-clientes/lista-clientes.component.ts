import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/entity/cliente';
import { ClienteService } from 'src/app/service/cliente.service';
import { BsModalRef } from 'ngx-bootstrap';
import { Subject } from 'rxjs';

@Component({
	selector: 'app-lista-clientes',
	templateUrl: './lista-clientes.component.html',
	styleUrls: ['./lista-clientes.component.css']
})
export class ListaClientesComponent implements OnInit {

	public totalItens : number = 0;

	public itemsPerPage : number = 20;

	public currentPage : number = 1;

	public clientes : Cliente[] = [];

	public onClose : Subject<Cliente>;

	public modal : boolean;

	public tituloTela : string;

	constructor(
		private clienteService : ClienteService,
		private bsModalRef : BsModalRef) { }

	ngOnInit() {
		this.onClose = new Subject();
		this.findAll(this.currentPage, this.itemsPerPage);

		this.tituloTela = 'Seleção de cliente';
		if (!this.modal) {
			this.tituloTela = 'Pesquisa de clientes';
		}
	}

	pageChanged(event : any) {
		let page : number = event.page - 1;
		this.findAll(page, this.itemsPerPage);
	}

	select(cliente : Cliente) : void {
		this.onClose.next(cliente);
		this.bsModalRef.hide();
	}

	private findAll(page : number, size : number) {
		this.clienteService.findAll(page, size).subscribe((res) => {
			this.clientes = res['data'];
			this.totalItens = res['total'];
		})
	}

}
