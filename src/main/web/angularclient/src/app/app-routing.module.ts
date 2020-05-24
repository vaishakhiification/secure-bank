import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {HomeComponent} from "./components/home/home.component";
import {DepositComponent} from "./components/deposit/deposit.component";
import {WithdrawComponent} from "./components/withdraw/withdraw.component";
import {ProfileComponent} from "./components/profile/profile.component";


const routes: Routes = [
  {
    path: "",
    redirectTo: "home",
    pathMatch: "full"
  },
  {path: "login", component: LoginComponent},
  {path: "register", component: RegistrationComponent},
  {path: "home", component: HomeComponent},
  {path: "deposit", component: DepositComponent},
  {path: "withdraw", component: WithdrawComponent},
  {path: "profile", component: ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
