import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/service/cliente.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TipoPessoa, GLOBAL_TIPOPESSOA_VALUES } from 'src/app/entity/enums/tipo-pessoa.enum';
import { EnumeratorDTO } from 'src/app/dto/enumerator-dto';
import { Cliente } from 'src/app/entity/cliente';

@Component({
	selector: 'app-cadastro-cliente',
	templateUrl: './cadastro-cliente.component.html',
	styleUrls: ['./cadastro-cliente.component.css']
})
export class CadastroClienteComponent implements OnInit {

	public formSubmitted : boolean;

	public formCadastroCliente : FormGroup;

	public tipoPessoaValues : EnumeratorDTO[];

	public listaEstados : any[];

	constructor(
		private formBuilder: FormBuilder,
		private clienteService : ClienteService,
		private router : Router) { }

	ngOnInit() {
		this.formCadastroCliente = this.formBuilder.group({
			nome: ['', [Validators.required, Validators.minLength(5)]],
			email: ['', [Validators.required, Validators.email]],
			tipoPessoa: [TipoPessoa.FISICA, Validators.required],
			cpf: ['', Validators.required],
			cnpj: [''],
			endereco: this.formBuilder.group({
				cep: ['', Validators.required],
				logradouro: ['', Validators.required],
				bairro: ['', Validators.required],
				numero: ['', Validators.required],
				municipio: ['', Validators.required],
				siglaEstado: ['', Validators.required],
				complemento: ['', Validators.maxLength(100)]
			})
		});

		this.formPrincipal.tipoPessoa.valueChanges.subscribe(newValue => {

			this.formPrincipal.cpf.setValidators(null);
			this.formPrincipal.cnpj.setValidators(null);

			if (newValue == TipoPessoa.FISICA) {
				this.formPrincipal.cpf.setValidators([Validators.required]);
			} else if (newValue == TipoPessoa.JURIDICA) {
				this.formPrincipal.cnpj.setValidators([Validators.required]);
			}
		});

		this.tipoPessoaValues = GLOBAL_TIPOPESSOA_VALUES;
		this.listaEstados = this.criarListaEstados();
	}

	get formPrincipal() {
		return this.formCadastroCliente.controls;
	}

	get formEndereco() {
		return this.formPrincipal.endereco['controls'];
	}

	get isTipoPessoaFisica() {
		return this.formCadastroCliente.value.tipoPessoa == TipoPessoa.FISICA;
	}

	get isTipoPessoaJuridica() {
		return this.formCadastroCliente.value.tipoPessoa == TipoPessoa.JURIDICA;
	}

	formCadastroClienteSubmit() {
		this.formSubmitted = true;
		if (this.formCadastroCliente.valid) {
			
			let cliente : Cliente = JSON.parse(JSON.stringify(this.formCadastroCliente.value));
			if (cliente.tipoPessoa == TipoPessoa.FISICA) {
				cliente.numeroDocumento = cliente['cpf'];
			} else {
				cliente.numeroDocumento = cliente['cnpj'];
			}
			
			delete cliente['cpf'];
			delete cliente['cnpj'];
			
			this.clienteService.save(cliente).subscribe((res) => {
				alert('Cliente salvo com sucesso.');
				this.router.navigate(['/clientes/listar']);
			});
		}
	}

	private criarListaEstados() : any[] {
		var dados = [];
		dados.push({
			'label': 'Acre - AC',
			'value': 'AC'
		});
		dados.push({
			'label': 'Alagoas - AL',
			'value': 'AL'
		});
		dados.push({
			'label': 'Amapá - AM',
			'value': 'AM'
		});
		dados.push({
			'label': 'Bahia - BA',
			'value': 'BA'
		});
		dados.push({
			'label': 'Ceará - CE',
			'value': 'CE'
		});
		dados.push({
			'label': 'Distrito Ferederal - DF',
			'value': 'DF'
		});
		dados.push({
			'label': 'Espiríto Santa - ES',
			'value': 'ES'
		});
		dados.push({
			'label': 'Goiás - GO',
			'value': 'GO'
		});
		dados.push({
			'label': 'Maranhão - MA',
			'value': 'MA'
		});
		dados.push({
			'label': 'Mato Grosso - MT',
			'value': 'MT'
		});
		dados.push({
			'label': 'Mato Grosso do Sul - MS',
			'value': 'MS'
		});
		dados.push({
			'label': 'Minas Gerais - MG',
			'value': 'MG'
		});
		dados.push({
			'label': 'Pará - PA',
			'value': 'PA'
		});
		dados.push({
			'label': 'Paraíba - PB',
			'value': 'PB'
		});
		dados.push({
			'label': 'Paraná - PR',
			'value': 'PR'
		});
		dados.push({
			'label': 'Pernambuco - PE',
			'value': 'PE'
		});
		dados.push({
			'label': 'Piauí - PI',
			'value': 'PI'
		});
		dados.push({
			'label': 'Rio de Janeiro - RJ',
			'value': 'RJ'
		});
		dados.push({
			'label': 'Rio Grande do Norte - RN',
			'value': 'RN'
		});
		dados.push({
			'label': 'Rio Grande do Sul - RS',
			'value': 'RS'
		});
		dados.push({
			'label': 'Rondônia - RO',
			'value': 'RO'
		});
		dados.push({
			'label': 'Roraima - RR',
			'value': 'RR'
		});
		dados.push({
			'label': 'Santa Cataria - SC',
			'value': 'SC'
		});
		dados.push({
			'label': 'São Paulo - SP',
			'value': 'SP'
		});
		dados.push({
			'label': 'Sergipe - SE',
			'value': 'SE'
		});
		dados.push({
			'label': 'Tocantins - TO',
			'value': 'TO'
		});
		return dados;
	}

}