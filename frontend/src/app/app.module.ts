import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
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
import { ModalSelecaoClienteComponent } from './views/clientes/modal-selecao-cliente/modal-selecao-cliente.component';
import { ModalCompraProdutosComponent } from './views/compras/lista-compras/modal-compra-produtos/modal-compra-produtos.component';
import { ModalSelecaoProdutoComponent } from './views/produto/modal-selecao-produto/modal-selecao-produto.component';

import { CnpjCpfPipe } from './pipe/cnpj-cpf.pipe';
import { CepPipe } from './pipe/cep.pipe';

import { ClienteService } from './service/cliente.service';
import { CompraService } from './service/compra.service';
import { ProdutoService } from './service/produto.service';

import { PaginationModule } from 'ngx-bootstrap/pagination';
import { ModalModule } from 'ngx-bootstrap';
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
		ModalSelecaoClienteComponent,
		ModalSelecaoProdutoComponent,
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

		PaginationModule.forRoot(),
		ModalModule.forRoot(),
		TooltipModule.forRoot()
	],
	entryComponents: [
		ModalCompraProdutosComponent,
		ModalSelecaoClienteComponent,
		ModalSelecaoProdutoComponent
	],
	providers: [
		ClienteService,
		CompraService,
		ProdutoService
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
