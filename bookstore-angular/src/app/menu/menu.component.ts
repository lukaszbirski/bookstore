import { Component, OnInit } from '@angular/core';
import {CategoriesDataService} from '../services/http/categories-data.service';
import {Category} from '../admin-panel/list-categories/list-categories.component';
import {HardcodedAuthenticationService} from '../services/hardcoded-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  public categories: Category[];

  constructor(
    private categoriesDataService: CategoriesDataService,
    public hardcodedAuthenticationService: HardcodedAuthenticationService
  ) {this.refreshCategories(); }

  ngOnInit(): void {
  }

  refreshCategories() {
    this.categoriesDataService.retrieveAllCategories().subscribe(
      response => {
        this.categories = response;
        console.log(response);
      }
    );
  }
}
