import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TopbarComponent} from './components/topbar/topbar.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {LoginComponent} from './components/login/login.component';
import {FormsModule} from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { DepositComponent } from './components/deposit/deposit.component';
import { WithdrawComponent } from './components/withdraw/withdraw.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NumericDirective } from './directives/numeric/numeric.directive';
import { CurrencyDirective } from './directives/currency/currency.directive';
import { CredentialsDirective } from './directives/credentails/credentials.directive';

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    RegistrationComponent,
    LoginComponent,
    HomeComponent,
    DepositComponent,
    WithdrawComponent,
    ProfileComponent,
    NumericDirective,
    CurrencyDirective,
    CredentialsDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
