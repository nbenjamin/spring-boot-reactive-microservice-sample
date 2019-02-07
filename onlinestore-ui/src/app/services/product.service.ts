import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers : new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http : HttpClient) { 

  }

  getProducts() {
    return this.http.get('/service/products');
  }

  getProduct(id : String) {
    return this.http.get('/service/products/'+ id);
  }

  addProduct(product) {
    let body = JSON.stringify(product)
    return this.http.post('/service/products', body, httpOptions);
  }
}
