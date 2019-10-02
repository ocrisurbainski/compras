import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './views/main/main.component';
import { ToolbarComponent } from './views/main/toolbar/toolbar.component';
import { ContentComponent } from './views/main/content/content.component';
import { DefaultIndexComponent } from './views/default-index/default-index.component';
import { ListaComprasComponent } from './views/compras/lista-compras/lista-compras.component';
import { NovaCompraComponent } from './views/compras/nova-compra/nova-compra.component';
import { PageTitleComponent } from './componente/page-title/page-title.component';

@NgModule({
	declarations: [
		AppComponent,
		MainComponent,
		ToolbarComponent,
		ContentComponent,
		DefaultIndexComponent,
		ListaComprasComponent,
		NovaCompraComponent,
		PageTitleComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule { }
