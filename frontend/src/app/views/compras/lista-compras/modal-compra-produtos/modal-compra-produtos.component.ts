import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { CompraProduto } from 'src/app/entity/compra-produto';

@Component({
	selector: 'app-modal-compra-produtos',
	templateUrl: './modal-compra-produtos.component.html',
	styleUrls: ['./modal-compra-produtos.component.css']
})
export class ModalCompraProdutosComponent implements OnInit {

	produtos : CompraProduto[];

	constructor(public bsModalRef : BsModalRef) { }

	ngOnInit() {
	}

}
