import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {API_URL} from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class CustomUserService {

  constructor(
    private http: HttpClient
  ) { }

  createUser(user): Observable<any> {
    return this.http.post(`${API_URL}/users/register`, user).pipe(
      catchError(this.handleError)
    );
  }

  getAuthentication(loginRequest): Observable<any> {
    return  this.http.post(`${API_URL}/users/login`, loginRequest).pipe(
      catchError(this.handleError)
    );
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error);
  }
}
