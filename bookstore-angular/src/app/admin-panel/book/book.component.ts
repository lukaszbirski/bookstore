import { Component, OnInit } from '@angular/core';
import { CoverTypeDataService } from '../list-cover-type/cover-type-data.service';
import { CoverType } from '../list-cover-type/list-cover-type.component';
import {ActivatedRoute, Router} from '@angular/router';
import {Category} from '../list-categories/list-categories.component';
import {CategoriesDataService} from '../list-categories/categories-data.service';
import {Book} from '../list-books/list-books.component';
import {BooksDataService} from '../list-books/books-data.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  public coverTypes: CoverType[];
  public categories: Category[];
  public book: Book;
  public title: string;
  public author: string;

  constructor(
    private coverTypeService: CoverTypeDataService,
    private categoriesDataService: CategoriesDataService,
    private booksDataService: BooksDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.title = this.route.snapshot.params.title;
    this.book = new Book('', '', [], '', '', '', '', 0, 0, '', '', []);
    // tslint:disable-next-line:triple-equals
    if (this.title != '') {
      this.booksDataService.retrieveBook(this.title).subscribe(
        response => this.book = response
      );
    }
    this.refreshCoverTypes();
    this.refreshCategories();
  }

  refreshCoverTypes() {
    this.coverTypeService.retrieveAllCoverTypes().subscribe(
      response => {
        this.coverTypes = response;
      }
    );
  }

  refreshCategories() {
    this.categoriesDataService.retrieveAllCategories().subscribe(
      response => {
        this.categories = response;
      }
    );
  }

  saveBook() {
    console.log(this.title);
    if (this.title === '') {
      this.booksDataService.createBook(this.book).subscribe(
        data => {
          this.router.navigate(['admin/books']);
        }
      );
      console.log('save');
    } else {
      this.booksDataService.updateBook(this.title, this.book).subscribe(
        data => {
          this.router.navigate(['admin/books']);
        }
      );
      console.log('update');
    }
    console.log('finished');
  }
}
