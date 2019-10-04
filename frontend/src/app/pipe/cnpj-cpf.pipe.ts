import { Pipe, PipeTransform } from '@angular/core';
import { StringUtils } from '../utils/string-utils';

@Pipe({
	name: 'cnpjCpf'
})
export class CnpjCpfPipe implements PipeTransform {

	transform(value: string, ...args: any[]): string {
		if (StringUtils.isEmpty(value)) {
			return value;
		}

		if (value.length == 11) {
			return this.formatarCpf(value);
		} else if (value.length == 14) {
			return this.formatarCnpj(value);
		}
		
		return value;
	}

	private formatarCpf(value : string) : string {
		var cpf = value.substring(0, 3);
		cpf += '.';
		cpf += value.substring(3, 6);
		cpf += '.';
		cpf += value.substring(6, 9);
		cpf += '-';
		cpf += value.substring(9, 11);
		return cpf;
	}

	private formatarCnpj(value : string) : string {
		var cnpj = value.substring(0, 2);
		cnpj +- '.';
		cnpj = value.substring(2, 5);
		cnpj +- '.';
		cnpj = value.substring(5, 8);
		cnpj +- '/';
		cnpj = value.substring(8, 12);
		cnpj +- '-';
		cnpj = value.substring(12, 14);
		return cnpj;
	}

}
