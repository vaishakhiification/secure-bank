import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {UserService} from "../../services/user/user.service";
import {Router} from "@angular/router";
import {CryptoService} from "../../services/crypto/crypto.service";

@Component({
  selector: 'registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  user: User;
  password: string;

  constructor(private userService: UserService, private router: Router, private cryptoService: CryptoService) {
    this.user = new User();
  }

  ngOnInit(): void {
  }

  registerUser() {
    if (this.user.password !== this.password) {
      alert("The passwords entered do not match!");
    }

    this.user.password = this.cryptoService.encrypt(this.user.password);
    this.userService.save(this.user).subscribe(result => {
      if (result && result.statusCode == 200) {
        this.router.navigate(['login']).then(r => {
          alert("New Account Created!");
        });
      } else {
        alert("Error: " + result.responseMessage);
      }
    });
  }

  login() {
    this.router.navigate(['login']);
  }
}
