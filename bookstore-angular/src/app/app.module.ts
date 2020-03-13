import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ErrorComponent } from './error/error.component';
import { ListBooksComponent } from './list-books/list-books.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { ListCategoriesComponent } from './admin-panel/list-categories/list-categories.component';
import { HttpClientModule } from '@angular/common/http';
import { CategoryComponent } from './admin-panel/category/category.component';

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
    CategoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
