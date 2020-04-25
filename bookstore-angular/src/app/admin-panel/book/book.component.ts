import { Component, OnInit } from '@angular/core';
import { CoverTypeDataService } from '../../services/http/cover-type-data.service';
import { CoverType } from '../list-cover-type/list-cover-type.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../list-categories/list-categories.component';
import { CategoriesDataService } from '../../services/http/categories-data.service';
import { Book } from '../list-books/list-books.component';
import { BooksDataService } from '../../services/http/books-data.service';
import { FileService } from '../../services/http/file.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  public coverTypes: CoverType[];
  public categories: Category[];
  public book: Book;
  public userFile: any = File;
  public title: string;
  public author: string;
  public titleError: string;
  public authorError: string;
  public categoryError: string;
  public pagesError: string;
  public eanError: string;
  public priceError: string;
  public descriptionError: string;
  public publisherError: string;
  public fileUploadError: string;
  public releaseDateError: string;
  public coverTypeError: string;

  constructor(
    private coverTypeService: CoverTypeDataService,
    private categoriesDataService: CategoriesDataService,
    private booksDataService: BooksDataService,
    private fileService: FileService,
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
    if (this.title === '') {

      const formData = new FormData();
      formData.append('file', this.userFile);

      this.booksDataService.createBook(this.book).subscribe(
        data => {
          this.fileService.uploadFile(formData).subscribe(response => {});
          this.router.navigate(['admin/books']);
        }, (error => {
          this.setValidations(error);
        })
      );
    } else {
      this.booksDataService.updateBook(this.title, this.book).subscribe(
        data => {
          this.router.navigate(['admin/books']);
        }, (error => {
          this.setValidations(error);
        })
      );
    }
  }

  setValidations(error) {
    this.titleError = error.title;
    this.authorError = error.author;
    this.categoryError = error.category;
    console.log(error);
    this.pagesError = error.pages;
    this.eanError = error.ean;
    this.priceError = error.price;
    this.descriptionError = error.description;
    this.publisherError = error.publisher;
    this.fileUploadError = error.fileName;
    this.releaseDateError = error.releaseDate;
    this.coverTypeError = error.coverType;
    if (error.title != null) {
      this.titleError = error.title;
      // tslint:disable-next-line:triple-equals
    } else {
      this.titleError = error.name;
    }
  }

  onSelectFile(event) {
    const file = event.target.files[0];
    this.book.fileName = file.name;
    this.userFile = file;
  }
}
