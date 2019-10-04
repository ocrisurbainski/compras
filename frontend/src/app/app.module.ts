import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './views/main/main.component';
import { ToolbarComponent } from './views/main/toolbar/toolbar.component';
import { ContentComponent } from './views/main/content/content.component';
import { DefaultIndexComponent } from './views/default-index/default-index.component';
import { ListaComprasComponent } from './views/compras/lista-compras/lista-compras.component';
import { NovaCompraComponent } from './views/compras/nova-compra/nova-compra.component';
import { PageTitleComponent } from './componente/page-title/page-title.component';
import { CnpjCpfPipe } from './pipe/cnpj-cpf.pipe';
import { CepPipe } from './pipe/cep.pipe';

import { PaginationModule } from 'ngx-bootstrap/pagination';
import { ModalModule } from 'ngx-bootstrap';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalCompraProdutosComponent } from './views/compras/lista-compras/modal-compra-produtos/modal-compra-produtos.component';

@NgModule({
	declarations: [
		AppComponent,
		MainComponent,
		ToolbarComponent,
		ContentComponent,
		DefaultIndexComponent,
		ListaComprasComponent,
		NovaCompraComponent,
		PageTitleComponent,
		CnpjCpfPipe,
		CepPipe,
		ModalCompraProdutosComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,

		HttpClientModule,
		FormsModule,

		PaginationModule.forRoot(),
		ModalModule.forRoot(),
		TooltipModule.forRoot()
	],
	entryComponents: [
		ModalCompraProdutosComponent
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule { }
