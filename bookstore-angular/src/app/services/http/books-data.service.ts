import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Book } from '../../admin-panel/list-books/list-books.component';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {API_URL} from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class BooksDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${API_URL}/books`);
  }

  retrieveBook(title): Observable<Book> {
    return this.http.get<Book>(`${API_URL}/books/${title}`);
  }

  deleteBook(title): Observable<any> {
    return this.http.delete(`${API_URL}/books/${title}`);
  }

  createBook(book): Observable<any> {
    return this.http.post(`${API_URL}/books`, book).pipe(
      catchError(this.handleError)
    );
  }

  updateBook(title, book): Observable<any> {
    return this.http.put(`${API_URL}/books/${title}`, book).pipe(
      catchError(this.handleError)
    );
  }

  retrieveBooksByCategory(category): Observable<Book[]> {
    return this.http.get<Book[]>(`${API_URL}/books/category/${category}`);
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error);
  }
}
