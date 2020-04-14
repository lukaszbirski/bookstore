import { Component, OnInit } from '@angular/core';
import { CategoriesDataService } from '../list-categories/categories-data.service';
import { Category } from '../list-categories/list-categories.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categoryName: string;
  category: Category;
  errorMassage: string;

  constructor(
    private categoryService: CategoriesDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.categoryName = this.route.snapshot.params.categoryName;
    this.category = new Category(this.categoryName, []);
    // tslint:disable-next-line:triple-equals
    if (this.categoryName != '') {
    this.categoryService.retrieveCategory(this.categoryName).subscribe(
      data => this.category = data
    );
    }
  }

  saveCategory() {
    if (this.categoryName === '') {
      this.categoryService.createCategory(this.categoryName, this.category).subscribe(
        data => {
          this.router.navigate(['admin/categories']);
        }, error => {
          this.setValidation(error);
        }
      );
    } else {
      this.categoryService.updateCategory(this.categoryName, this.category).subscribe(
        data => {
          this.router.navigate(['admin/categories']);
        }, error => {
          this.setValidation(error);
        }
      );
    }
  }

    setValidation(error) {
    // tslint:disable-next-line:triple-equals
    if (error.name != null) {
      this.errorMassage = error.name;
      // tslint:disable-next-line:triple-equals
    } else {
      this.errorMassage = error.categoryName;
    }
  }
}
