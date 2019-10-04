import { TipoPessoa } from './enums/tipo-pessoa.enum';
import { Endereco } from './endereco';

export class Cliente {

    id : string;
    nome : string;
    email : string;
    tipoPessoa : TipoPessoa;
    numeroDocumento : String;
    endereco : Endereco;

}