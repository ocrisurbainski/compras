import { Injectable } from '@angular/core';
import { Compra } from '../entity/compra';
import { Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { AbstractService } from './abstract-service';
import { HttpParams, HttpClient } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class CompraService extends AbstractService {

	constructor(private http : HttpClient) {
		super();
	}

	save(compra : Compra) : Observable<Compra> {
		let url : string = this.getCompleteUrl('/compras');
		return this.http.post<Compra>(url, compra).pipe(
			retry(1),
			catchError(this.handleError)
		);
	}

	findAll(page : number, size : number) : Observable<Compra[]> {
		let params = new HttpParams().set('page', String(page)).set('size', String(size));
		let url : string = this.getCompleteUrl('/compras');
		return this.http.get<Compra[]>(url, { params }).pipe(
			retry(1),
			catchError(this.handleError)
		);
	}

}