import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user/user";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {
  user: User;
  password: string;

  constructor(private router: Router, private userService: UserService) {
    this.user = new User();
  }

  ngOnInit(): void {
  }

  changePassword() {
    if (this.user.password !== this.password) {
      alert("The passwords entered do not match!");
    }
    this.userService.resetPassword(this.user).subscribe(result => {
      if (result && result.statusCode == 200) {
        this.router.navigate(['login']).then(r => {
          alert("Password Reset! Please login to continue");
        });
      } else {
        alert("Error: " + result.responseMessage);
      }
    });
  }
}
