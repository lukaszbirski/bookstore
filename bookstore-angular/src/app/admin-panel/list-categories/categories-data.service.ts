import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Category } from './list-categories.component';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoriesDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`http://localhost:8080/api/dto/categories`);
  }

  retrieveCategory(name): Observable<Category> {
    return this.http.get<Category>(`http://localhost:8080/api/dto/categories/${name}`);
  }

  createCategory(name, category): Observable<any> {
    return this.http.post(`http://localhost:8080/api/dto/categories/${name}`, category).pipe(
      catchError(this.handleError)
    );
  }

  updateCategory(name, category): Observable<any> {
    return this.http.put(`http://localhost:8080/api/dto/categories/${name}`, category).pipe(
      catchError(this.handleError)
    );
  }

  deleteCategory(name): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/dto/categories/${name}`);
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error);
  }
}
