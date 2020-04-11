import { Component, OnInit } from '@angular/core';
import {CategoriesDataService} from '../admin-panel/list-categories/categories-data.service';
import {Category} from '../admin-panel/list-categories/list-categories.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  public categories: Category[];

  constructor(
    private categoriesDataService: CategoriesDataService,
  ) { }

  ngOnInit(): void {
    this.refreshCategories();
  }

  refreshCategories() {
    this.categoriesDataService.retrieveAllCategories().subscribe(
      response => {
        console.log(response);
        this.categories = response;
      }
    );
  }
}
