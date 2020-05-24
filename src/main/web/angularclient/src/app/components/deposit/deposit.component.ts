import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user/user";

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {

  user: User;
  depositAmount: number;

  constructor(private router: Router) {
    this.user = this.router.getCurrentNavigation().extras.state.user;
    this.depositAmount = 0;
  }

  ngOnInit(): void {
  }

  back() {
    this.router.navigate(['home'], {state: {user: this.user}});
  }

  deposit() {
    this.user.balance += this.depositAmount;
    this.router.navigate(['home'], {state: {user: this.user}});
  }
}
