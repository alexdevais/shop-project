import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Shop {
  id?: number;
  name: string;
  city: string;
  address: string;
  postalCode: number;
}

@Injectable({
  providedIn: 'root'
})


export class ShopService {

  private API_URL_SHOP_CREATE = 'http://localhost:8080/api/shop/create'

  constructor(private http: HttpClient) { }

  createShop(shopData: Shop): Observable<any> {
    
    const token = localStorage.getItem('token');

    if(!token) {
      alert('no token found')
    }

    const headers = {
      'Authorization': `Bearer ${token}`
    };
    
    return this.http.post(this.API_URL_SHOP_CREATE, shopData, { headers })
  }
  

}
