import { Component, OnInit } from '@angular/core';

import { BooksDataService } from '../../services/http/books-data.service';
import { Router } from '@angular/router';
import {Comment} from '../list-comments/list-comments.component';
import {FileService} from '../../services/http/file.service';

export class Book {
  constructor(
    public author: string,
    public title: string,
    public categories: string[],
    public coverType: string,
    public description: string,
    public ean: string,
    public fileName: string,
    public pages: number,
    public price: number,
    public publisher: string,
    public releaseDate: string,
    public comments: Comment[]
  ) {}
}

@Component({
  selector: 'app-list-books',
  templateUrl: './list-books.component.html',
  styleUrls: ['./list-books.component.css']
})
export class ListBooksComponent implements OnInit {

  public books: Book[];
  public book: Book;
  public deleteError: string;

  constructor(
    private bookService: BooksDataService,
    private fileService: FileService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.refreshBooks();
  }

  refreshBooks() {
    this.bookService.retrieveAllBooks().subscribe(
      response => {
        console.log(response);
        this.books = response;
      }
    );
  }

  createBook() {
    this.router.navigate(['admin/books', '']);
  }

  deleteBook(bookTitle) {
    this.bookService.retrieveBook(bookTitle).subscribe(result => {
      this.book = result;
    });
    this.bookService.deleteBook(bookTitle).subscribe(
      response => {
        this.refreshBooks();
      }, error => {
        this.deleteError = error.error.text;
        console.log(this.book.fileName);
        this.refreshBooks();
        this.fileService.deleteFile(this.book.fileName).subscribe();
      }
    );
  }

  getBookDetails(bookTitle) {
    this.router.navigate(['admin/books/details', bookTitle]);
  }

}
