import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  id?: number;
  firstName: string;
  lastName: string;
  password: string;
  email: string; 
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private API_URL = 'http://localhost:8080/api/user'

  constructor(private http: HttpClient) { }

  createUser(user: User): Observable<void> {
    return this.http.post<void>(`${this.API_URL}/create`, user)
  }
}
