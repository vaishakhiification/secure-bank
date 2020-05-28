import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user/user";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: User;
  newUserInfo: User;

  constructor(private router: Router) {
    let state = this.router.getCurrentNavigation().extras.state;
    if (!state) {
      alert("Please login to proceed!");
      this.router.navigate(['login'], {state: {user: this.user}});
    } else {
      this.user = state.user;
      this.newUserInfo = Object.assign({}, this.user);
    }
  }

  ngOnInit(): void {
  }

  updateUser() {
    this.user = Object.assign({}, this.newUserInfo);
    this.router.navigate(['home'], {state: {user: this.user}});
  }
}
