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
    this.user = this.router.getCurrentNavigation().extras.state.user;
    this.newUserInfo = Object.assign({}, this.user);
  }

  ngOnInit(): void {
  }

  updateUser() {
    this.user = Object.assign({}, this.newUserInfo);
    this.router.navigate(['home'], {state: {user: this.user}});
  }
}
