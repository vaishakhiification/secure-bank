import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {HomeComponent} from './components/home/home.component';
import {DepositComponent} from './components/deposit/deposit.component';
import {WithdrawComponent} from './components/withdraw/withdraw.component';
import {ProfileComponent} from './components/profile/profile.component';
import {ResetPasswordComponent} from "./components/reset-password/reset-password.component";
import {AuthGuard} from "./helper/auth-guard.service";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'home', component: HomeComponent},
  {path: 'deposit', component: DepositComponent, canActivate: [AuthGuard]},
  {path: 'withdraw', component: WithdrawComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'resetPassword', component: ResetPasswordComponent},
  {path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
