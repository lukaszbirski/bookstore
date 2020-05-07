import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Category } from '../../admin-panel/list-categories/list-categories.component';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {API_URL} from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class CategoriesDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${API_URL}/categories`);
  }

  retrieveCategory(name): Observable<Category> {
    return this.http.get<Category>(`${API_URL}/categories/${name}`);
  }

  createCategory(name, category): Observable<any> {
    return this.http.post(`${API_URL}/categories/${name}`, category).pipe(
      catchError(this.handleError)
    );
  }

  updateCategory(name, category): Observable<any> {
    return this.http.put(`${API_URL}/categories/${name}`, category).pipe(
      catchError(this.handleError)
    );
  }

  deleteCategory(name): Observable<any> {
    return this.http.delete(`${API_URL}/categories/${name}`);
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error);
  }
}
