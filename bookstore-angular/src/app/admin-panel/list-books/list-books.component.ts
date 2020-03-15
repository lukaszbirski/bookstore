import { Component, OnInit } from '@angular/core';
import { Comment } from '@angular/compiler';
import { BooksDataService } from './books-data.service';
import { Router } from '@angular/router';

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
  ){}
}

@Component({
  selector: 'app-list-books',
  templateUrl: './list-books.component.html',
  styleUrls: ['./list-books.component.css']
})
export class ListBooksComponent implements OnInit {

  public books: Book[]

  constructor(
    private bookService : BooksDataService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.refreshBooks();
  }

  refreshBooks(){
    this.bookService.retrieveAllBooks().subscribe(
      response => {
        console.log(response)
        this.books = response;
      }
    )
  }

  createBook(){
    this.router.navigate(['admin/books', ''])
  }

  deleteBook(bookTitle){
    this.bookService.deleteBook(bookTitle).subscribe(
      response => {
        this.refreshBooks();
      }
    )
  }

  getBookDetails(title){
    this.router.navigate(['admin/books/details', title])
  }

}
