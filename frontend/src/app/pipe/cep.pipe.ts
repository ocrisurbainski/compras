import { Pipe, PipeTransform } from '@angular/core';
import { StringUtils } from '../utils/string-utils';

@Pipe({
	name: 'cep'
})
export class CepPipe implements PipeTransform {

	transform(value: string, ...args: any[]): string {
		if (StringUtils.isEmpty(value)) {
			return value;
		}

		if (value.length == 8) {
			var cep = value.substring(0, 2);
			cep += '.';
			cep += value.substring(2, 5);
			cep += '-';
			cep += value.substring(5, 8);
			return cep;
		}
		
		return value;
	}

}
