import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BooksDataService} from '../admin-panel/list-books/books-data.service';
import {Book} from '../admin-panel/list-books/list-books.component';
import {Category} from '../admin-panel/list-categories/list-categories.component';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  public category: Category;
  public books: Book[];
  public categoryName: string;

  constructor(
    private route: ActivatedRoute,
    private booksDataService: BooksDataService
  ) {
    route.params.subscribe(value => {
      this.categoryName = this.route.snapshot.params.category;
      console.log(this.categoryName);
      if (this.categoryName == null) {
        this.refreshBooks();
      } else {
        this.getBooks(this.categoryName);
      }
    });
  }

  ngOnInit(): void {

  }

  refreshBooks() {
    this.booksDataService.retrieveAllBooks().subscribe(
      response => {
        console.log(response);
        this.books = response;
      }
    );
  }

  getBooks(categoryName) {
    this.booksDataService.retrieveBooksByCategory(categoryName).subscribe(
      response => {
        console.log(response);
        this.books = response;
      }
    );
  }
}




