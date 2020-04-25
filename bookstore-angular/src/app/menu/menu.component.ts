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
  // public isUserLoggedIn = false;

  constructor(
    private categoriesDataService: CategoriesDataService,
    public hardcodedAuthenticationService: HardcodedAuthenticationService
  ) { }

  ngOnInit(): void {
    this.refreshCategories();
    // this.isUserLoggedIn = this.hardcodedAuthenticationService.isUserLoggedIn();
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
