import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DefaultIndexComponent } from './views/default-index/default-index.component';
import { ListaComprasComponent } from './views/compras/lista-compras/lista-compras.component';
import { CadastroClienteComponent } from './views/clientes/cadastro-cliente/cadastro-cliente.component';
import { ListaClientesComponent } from './views/clientes/lista-clientes/lista-clientes.component';
import { ListaProdutosComponent } from './views/produtos/lista-produtos/lista-produtos.component';
import { CadastroProdutoComponent } from './views/produtos/cadastro-produto/cadastro-produto.component';
import { NovaCompraComponent } from './views/compras/nova-compra/nova-compra.component';

const routes: Routes = [
	{ 
		path: '', 
		component: DefaultIndexComponent 
	}, {
		path: 'clientes/cadastrar',
		component: CadastroClienteComponent
	}, { 
		path: 'clientes/listar', 
		component: ListaClientesComponent 
	}, { 
		path: 'produtos/listar', 
		component: ListaProdutosComponent 
	}, { 
		path: 'produtos/cadastrar', 
		component: CadastroProdutoComponent 
	}, { 
		path: 'compras/listar', 
		component: ListaComprasComponent 
	}, { 
		path: 'compras/nova', 
		component: NovaCompraComponent 
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
