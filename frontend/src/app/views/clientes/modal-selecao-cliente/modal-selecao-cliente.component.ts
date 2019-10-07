import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/entity/cliente';
import { ClienteService } from 'src/app/service/cliente.service';
import { BsModalRef } from 'ngx-bootstrap';
import { Subject } from 'rxjs';

@Component({
	selector: 'app-modal-selecao-cliente',
	templateUrl: './modal-selecao-cliente.component.html',
	styleUrls: ['./modal-selecao-cliente.component.css']
})
export class ModalSelecaoClienteComponent implements OnInit {

	public totalItens : number = 0;

	public itemsPerPage : number = 20;

	public currentPage : number = 1;

	public clientes : Cliente[] = [];

	public onClose : Subject<Cliente>;

	constructor(
		private clienteService : ClienteService,
		public bsModalRef : BsModalRef) { }

	ngOnInit() {
		this.onClose = new Subject();
		this.findAll(this.currentPage, this.itemsPerPage);
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
