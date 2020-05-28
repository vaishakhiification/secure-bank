import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {User} from "../../models/user/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: User;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.user = new User();
  }

  login() {
    this.userService.login(this.user).subscribe(
      result => {
        if (result && result.statusCode === 200) {
          this.router.navigate(['home'], {state: {user: result.user}});
        } else {
          alert("Error: " + result.responseMessage);
        }
      }
    );
  }
}
