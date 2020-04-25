import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ErrorComponent } from './error/error.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { ListCategoriesComponent } from './admin-panel/list-categories/list-categories.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { CategoryComponent } from './admin-panel/category/category.component';
import { ListCoverTypeComponent } from './admin-panel/list-cover-type/list-cover-type.component';
import { ListCommentsComponent } from './admin-panel/list-comments/list-comments.component';
import { ListBooksComponent } from './admin-panel/list-books/list-books.component';
import { CoverTypeComponent } from './admin-panel/cover-type/cover-type.component';
import { BookDetailsComponent } from './admin-panel/book-details/book-details.component';
import { BookComponent } from './admin-panel/book/book.component';
import { BookCustomerComponent } from './welcome/book-customer/book-customer.component';
import { LogoutComponent } from './logout/logout.component';
import { RegisterComponent } from './register/register.component';
import {HttpIntercepterAuthService} from './services/http/http-intercepter-auth.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomeComponent,
    ErrorComponent,
    ListBooksComponent,
    AdminPanelComponent,
    MenuComponent,
    FooterComponent,
    ListCategoriesComponent,
    CategoryComponent,
    ListCoverTypeComponent,
    ListCommentsComponent,
    CoverTypeComponent,
    BookDetailsComponent,
    BookComponent,
    BookCustomerComponent,
    LogoutComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterAuthService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
