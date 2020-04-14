import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Book } from '../list-books/list-books.component';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BooksDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`http://localhost:8080/api/dto/books`);
  }

  retrieveBook(title): Observable<Book> {
    return this.http.get<Book>(`http://localhost:8080/api/dto/books/${title}`);
  }

  deleteBook(title): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/dto/books/${title}`);
  }

  createBook(book): Observable<any> {
    return this.http.post(`http://localhost:8080/api/dto/books`, book).pipe(
      catchError(this.handleError)
    );
  }

  updateBook(title, book): Observable<any> {
    return this.http.put(`http://localhost:8080/api/dto/books/${title}`, book).pipe(
      catchError(this.handleError)
    );
  }

  retrieveBooksByCategory(category): Observable<Book[]> {
    return this.http.get<Book[]>(`http://localhost:8080/api/dto/books/category/${category}`);
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error);
  }
}
