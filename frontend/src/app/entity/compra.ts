import { CompraCliente } from './compra-cliente';
import { Endereco } from './endereco';
import { SituacaoEntrega } from './enums/situacao-entrega.enum';
import { CompraProduto } from './compra-produto';

export class Compra {

    id : String;
    cliente : CompraCliente;
    enderecoEntrega : Endereco;
    valorTotal : number;
    quantidadeTotal : number;
    dataCompra : Date;
    situacaoEntrega : SituacaoEntrega;
    produtos : Array<CompraProduto>

}