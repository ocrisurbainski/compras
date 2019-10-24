import { EnumeratorDTO } from 'src/app/dto/enumerator-dto';

export enum TipoPessoa {

    FISICA = 'FISICA', 
    
    JURIDICA = 'JURIDICA'

}

export const GLOBAL_TIPOPESSOA_VALUES = new Array<EnumeratorDTO>(
    new EnumeratorDTO(TipoPessoa.FISICA, 'Física'),
    new EnumeratorDTO(TipoPessoa.JURIDICA, 'Jurídica')
);