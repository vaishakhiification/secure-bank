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
    let state = this.router.getCurrentNavigation().extras.state;
    if (!state) {
      alert("Please login to proceed!");
      this.router.navigate(['login'], {state: {user: this.user}});
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
