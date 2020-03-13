import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from './list-categories.component';

@Injectable({
  providedIn: 'root'
})
export class CategoriesDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllCategories(){
    return this.http.get<Category[]>(`http://localhost:8080/api/dto/categories`);
  }

  deleteCategory(name){
    return this.http.delete(`http://localhost:8080/api/dto/categories/${name}`)
  }

}
