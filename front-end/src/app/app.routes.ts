import { Routes } from '@angular/router';
import { CreateAccountComponent } from './component/create-account/create-account.component';
import { LoginComponent } from './component/login/login.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';

export const routes: Routes = [
    { path: '', component: LoginComponent }, 
    { path: 'login', component: LoginComponent },
    { path: 'create-account', component: CreateAccountComponent },
     {path: 'dashboard', component: DashboardComponent}
];
