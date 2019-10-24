import { Injectable } from '@angular/core';
import { AbstractService } from './abstract-service';
import { Observable } from 'rxjs';
import { HttpParams, HttpClient } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { Cliente } from '../entity/cliente';

@Injectable({
	providedIn: 'root'
})
export class ClienteService extends AbstractService {

	constructor(private http : HttpClient) {
		super();
	}

	save(cliente : Cliente) : Observable<Cliente> {
		let url : string = this.getCompleteUrl('/clientes');
		return this.http.post<Cliente>(url, cliente).pipe(
			retry(1),
			catchError(this.handleError)
		);
	}

	findAll(page : number, size : number) : Observable<Cliente[]> {
		let params = new HttpParams().set('page', String(page)).set('size', String(size));
		let url : string = this.getCompleteUrl('/clientes');
		return this.http.get<Cliente[]>(url, { params }).pipe(
			retry(1),
			catchError(this.handleError)
		);
	}
}
