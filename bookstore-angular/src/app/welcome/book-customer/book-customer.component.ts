import { Component, OnInit } from '@angular/core';
import {BooksDataService} from '../../admin-panel/list-books/books-data.service';
import {Book} from '../../admin-panel/list-books/list-books.component';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-book-customer',
  templateUrl: './book-customer.component.html',
  styleUrls: ['./book-customer.component.css']
})
export class BookCustomerComponent implements OnInit {

  public book: Book;
  public bookTitle: string;

  constructor(
    private bookDataService: BooksDataService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.bookTitle = this.route.snapshot.params.title;
    this.book = new Book('', '', [], '', '', '', '', 0, 0, '', '', []);
    this.retrieveBook(this.bookTitle);
  }

  retrieveBook(bookTitle) {
    this.bookDataService.retrieveBook(bookTitle).subscribe(
      response => {
        console.log(response);
        this.book = response;
      }
    );
  }

}
