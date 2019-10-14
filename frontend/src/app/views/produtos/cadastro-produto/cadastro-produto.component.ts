import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProdutoService } from 'src/app/service/produto.service';
import { Router } from '@angular/router';
import { Produto } from 'src/app/entity/produto';

@Component({
	selector: 'app-cadastro-produto',
	templateUrl: './cadastro-produto.component.html',
	styleUrls: ['./cadastro-produto.component.css']
})
export class CadastroProdutoComponent implements OnInit {

	public formSubmitted : boolean;

	public formCadastroProduto : FormGroup;

	constructor(
		private formBuilder: FormBuilder,
		private produtoService : ProdutoService,
		private router : Router) { }

	ngOnInit() {
		this.formCadastroProduto = this.formBuilder.group({
			nome: ['', [Validators.required, Validators.minLength(5)]],
			marca: ['', Validators.required],
			preco: ['', [Validators.required, Validators.min(0.01)]]
		});
	}

	get form() { 
		return this.formCadastroProduto.controls; 
	}

	formCadastroProdutoSubmit() {
		this.formSubmitted = true;
		if (this.formCadastroProduto.valid) {
			let produto : Produto = this.formCadastroProduto.value;
			this.produtoService.save(produto).subscribe((res) => {
				alert('Produto salvo com sucesso.');
				this.router.navigate(['/produtos/listar']);
			});
		}
	}

}
