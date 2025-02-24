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

export interface AuthResponse {
  token: string,
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private API_URL_USER = 'http://localhost:8080/api/user'
  private API_URL_AUTH = 'http://localhost:8080/api/auth'

  constructor(private http: HttpClient) { }

  createUser(user: User): Observable<void> {
    return this.http.post<void>(`${this.API_URL_USER}/create`, user)
  }

  login(user: User): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL_AUTH}/login`, user)
  }

  storeToken(authResponse: AuthResponse): void {
    localStorage.setItem('token', authResponse.token)
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token')
  }

  logout(): void {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
  }
}
