import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './views/main/main.component';
import { ToolbarComponent } from './views/main/toolbar/toolbar.component';
import { ContentComponent } from './views/main/content/content.component';
import { DefaultIndexComponent } from './views/default-index/default-index.component';
import { PageTitleComponent } from './componente/page-title/page-title.component';
import { NovaCompraComponent } from './views/compras/nova-compra/nova-compra.component';
import { ListaComprasComponent } from './views/compras/lista-compras/lista-compras.component';
import { ListaClientesComponent } from './views/clientes/lista-clientes/lista-clientes.component';
import { CadastroProdutoComponent } from './views/produtos/cadastro-produto/cadastro-produto.component';
import { ListaProdutosComponent } from './views/produtos/lista-produtos/lista-produtos.component';
import { ModalCompraProdutosComponent } from './views/compras/lista-compras/modal-compra-produtos/modal-compra-produtos.component';

import { CnpjCpfPipe } from './pipe/cnpj-cpf.pipe';
import { CepPipe } from './pipe/cep.pipe';

import { ClienteService } from './service/cliente.service';
import { CompraService } from './service/compra.service';
import { ProdutoService } from './service/produto.service';

import { PaginationModule } from 'ngx-bootstrap/pagination';
import { ModalModule, BsModalRef } from 'ngx-bootstrap/modal';
import { TooltipModule } from 'ngx-bootstrap/tooltip';

@NgModule({
	declarations: [
		AppComponent,
		MainComponent,
		ToolbarComponent,
		ContentComponent,
		DefaultIndexComponent,
		PageTitleComponent,
		NovaCompraComponent,
		ListaComprasComponent,
		ListaClientesComponent,
		CadastroProdutoComponent,
		ListaProdutosComponent,
		ModalCompraProdutosComponent,
		CnpjCpfPipe,
		CepPipe
	],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		AppRoutingModule,

		HttpClientModule,
		FormsModule,
		ReactiveFormsModule,

		PaginationModule.forRoot(),
		ModalModule.forRoot(),
		TooltipModule.forRoot()
	],
	entryComponents: [
		ModalCompraProdutosComponent,
		ListaClientesComponent,
		ListaProdutosComponent
	],
	providers: [
		ClienteService,
		CompraService,
		ProdutoService,
		BsModalRef
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
