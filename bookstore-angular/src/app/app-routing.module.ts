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
import {BookCustomerComponent} from './welcome/book-customer/book-customer.component';
import {LogoutComponent} from './logout/logout.component';
import {RouteGuardService} from './services/route-guard.service';
import {RegisterComponent} from './register/register.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'welcome', component: WelcomeComponent, canActivate: [RouteGuardService]},
  {path: 'welcome/:category', component: WelcomeComponent, canActivate: [RouteGuardService]},
  {path: 'admin', component: AdminPanelComponent, canActivate: [RouteGuardService]},
  {path: 'admin/categories', component: ListCategoriesComponent, canActivate: [RouteGuardService]},
  {path: 'admin/categories/:categoryName', component: CategoryComponent, canActivate: [RouteGuardService]},
  {path: 'admin/covertypes', component: ListCoverTypeComponent, canActivate: [RouteGuardService]},
  {path: 'admin/covertypes/:name', component: CoverTypeComponent, canActivate: [RouteGuardService]},
  {path: 'admin/books', component: ListBooksComponent, canActivate: [RouteGuardService]},
  {path: 'admin/comments', component: ListCommentsComponent, canActivate: [RouteGuardService]},
  {path: 'admin/books/details/:title', component: BookDetailsComponent, canActivate: [RouteGuardService]},
  {path: 'admin/books/:title', component: BookComponent, canActivate: [RouteGuardService]},
  {path: 'welcome/books/:title', component: BookCustomerComponent, canActivate: [RouteGuardService]},


  {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
