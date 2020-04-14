import { Component, OnInit } from '@angular/core';
import { CategoriesDataService } from './categories-data.service';
import { Router } from '@angular/router';

export class Category {
  constructor(
      public categoryName: string,
      public books: Book[],
  ) {}
}

export class Book {
  constructor(
      public name: string,
  ) {}
}

@Component({
  selector: 'app-list-categories',
  templateUrl: './list-categories.component.html',
  styleUrls: ['./list-categories.component.css']
})
export class ListCategoriesComponent implements OnInit {

  public categories: Category[];
  public deleteError: string;

  constructor(
    private categoriesService: CategoriesDataService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.refreshCategories();
  }

  refreshCategories() {
    this.categoriesService.retrieveAllCategories().subscribe(
      response => {
        console.log(response);
        this.categories = response;
      }
    );
  }

  deleteCategory(categoryName) {
    console.log(`delete ${categoryName}`);
    this.categoriesService.deleteCategory(categoryName).subscribe(
      response => {

        this.refreshCategories();
      }, error => {
        this.deleteError = error.error.text;
        this.refreshCategories();
      }
    );
  }

  updateCategory(categoryName) {
    console.log(`update ${categoryName}`);
    this.router.navigate(['admin/categories', categoryName]);
  }

  createCategory() {
    this.router.navigate(['admin/categories', '']);
  }


}
