import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ErrorComponent } from './error/error.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { ListCategoriesComponent} from './admin-panel/list-categories/list-categories.component';
import { CategoryComponent } from './admin-panel/category/category.component';


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'welcome/:name', component: WelcomeComponent},
  {path: 'admin', component: AdminPanelComponent},
  {path: 'admin/categories', component: ListCategoriesComponent},
  {path: 'admin/categories/:categoryName', component: CategoryComponent},


  {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
