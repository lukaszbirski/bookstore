import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ErrorComponent } from './error/error.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { ListCategoriesComponent} from './admin-panel/list-categories/list-categories.component';
import { CategoryComponent } from './admin-panel/category/category.component';
import { ListCoverTypeComponent } from './admin-panel/list-cover-type/list-cover-type.component';
import { ListBooksComponent } from './admin-panel/list-books/list-books.component';
import { ListCommentsComponent } from './admin-panel/list-comments/list-comments.component';
import { CoverTypeComponent} from './admin-panel/cover-type/cover-type.component';
import { BookDetailsComponent} from './admin-panel/book-details/book-details.component';
import { BookComponent } from './admin-panel/book/book.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'welcome', component: WelcomeComponent},
  {path: 'welcome/:category', component: WelcomeComponent},
  {path: 'admin', component: AdminPanelComponent},
  {path: 'admin/categories', component: ListCategoriesComponent},
  {path: 'admin/categories/:categoryName', component: CategoryComponent},
  {path: 'admin/covertypes', component: ListCoverTypeComponent},
  {path: 'admin/covertypes/:name', component: CoverTypeComponent},
  {path: 'admin/books', component: ListBooksComponent},
  {path: 'admin/comments', component: ListCommentsComponent},
  {path: 'admin/books/details/:title', component: BookDetailsComponent},
  {path: 'admin/books/:title', component: BookComponent},


  {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
