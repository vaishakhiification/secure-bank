import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  user: User;

  constructor(private router: Router) {
    this.user = new User();
    let state = this.router.getCurrentNavigation().extras.state;
    if (!state) {
      this.user.firstName = "Vaishakhi";
      this.user.balance = 1200;
      this.user.userName = "vpilanka";
    } else {
      this.user = state.user;
    }
  }

  ngOnInit(): void {
  }

  deposit() {
    this.router.navigate(['deposit'], {state: {user: this.user}});
  }

  withdraw() {
    this.router.navigate(['withdraw'], {state: {user: this.user}});
  }

}
