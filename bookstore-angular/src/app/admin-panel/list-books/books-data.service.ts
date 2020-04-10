import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Book } from '../list-books/list-books.component';

@Injectable({
  providedIn: 'root'
})
export class BooksDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllBooks() {
    return this.http.get<Book[]>(`http://localhost:8080/api/dto/books`);
  }

  retrieveBook(title) {
    return this.http.get<Book>(`http://localhost:8080/api/dto/books/${title}`);
  }

  deleteBook(title) {
    return this.http.delete(`http://localhost:8080/api/dto/books/${title}`);
  }

  createBook(book) {
    return this.http.post(`http://localhost:8080/api/dto/books`, book);
  }

  updateBook(title, book) {
    return this.http.put(`http://localhost:8080/api/dto/books/${title}`, book);
  }
}
