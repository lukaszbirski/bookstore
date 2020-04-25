import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CustomUserService {

  constructor(
    private http: HttpClient
  ) { }

  createUser(user): Observable<any> {
    return this.http.post(`http://localhost:8070/api/dto/users/register`, user).pipe(
      catchError(this.handleError)
    );
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error);
  }
}
