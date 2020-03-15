import { Component, OnInit } from '@angular/core';
import { BooksDataService } from '../list-books/books-data.service';
import { Book } from '../list-books/list-books.component';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentDataService } from '../list-comments/comment-data.service';
import { Comment } from '../list-comments/list-comments.component';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  public book : Book
  public bookTitle : string
  public categories

  constructor(
    private bookService : BooksDataService,

    private route : ActivatedRoute,
    private router : Router 
  ) { }

  ngOnInit(): void {
    this.bookTitle = this.route.snapshot.params['title']
    this.book = new Book('', '', [], '','','','', 0, 0, '', '', []);
    this.retrieveBook(this.bookTitle);
  }

  retrieveBook(bookTitle){
    this.bookService.retrieveBook(bookTitle).subscribe(
      response => {
        console.log(response);
        this.book = response;
      }
    )
  }

  deleteBook(bookTitle){
    this.bookService.deleteBook(bookTitle).subscribe(
      response => {
        this.retrieveBook(bookTitle);
        this.router.navigate(['admin/books'])
      }
    )
  }

  updateBook(bookTitle){
    this.router.navigate(['admin/books', bookTitle])
  }

}
