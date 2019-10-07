import { Injectable } from '@angular/core';
import { AbstractService } from './abstract-service';
import { Produto } from '../entity/produto';
import { HttpClient, HttpParams } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class ProdutoService extends AbstractService {

	constructor(private http : HttpClient) {
		super();
	}

	findAll(page : number, size : number) : Observable<Produto[]> {
		let params = new HttpParams().set('page', String(page)).set('size', String(size));
		let url : string = this.getCompleteUrl('/produtos');
		return this.http.get<Produto[]>(url, { params }).pipe(
			retry(1),
			catchError(this.handleError)
		);
	}

}