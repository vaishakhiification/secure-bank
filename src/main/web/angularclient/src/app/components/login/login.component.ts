import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {User} from "../../models/user/user";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication/authentication.service";
import {CryptoService} from "../../services/crypto/crypto.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: User;
  count: number;
  readonly limit: number = 3;
  
  constructor(private userService: UserService, private router: Router,
              private authenticationService: AuthenticationService,
              private cryptoService: CryptoService) {
  }

  ngOnInit(): void {
    this.user = new User();
    this.count = 0;
  }

  login() {
    this.user.password = this.cryptoService.encrypt(this.user.password);
    this.userService.login(this.user).subscribe(
      result => {
        if (result && result.statusCode === 200) {
          this.authenticationService.login(result.user);
          this.router.navigate(['home'], {state: {user: result.user}});
        } else {
          alert("Error: " + result.responseMessage);
        }
      }
    );
  }
}
