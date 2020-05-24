import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.scss']
})
export class WithdrawComponent implements OnInit {
  user: User;
  withdrawalAmount: number;

  constructor(private router: Router) {
    this.user = this.router.getCurrentNavigation().extras.state.user;
    this.withdrawalAmount = 0;
  }

  ngOnInit(): void {
  }

  back() {
    this.router.navigate(['home'], {state: {user: this.user}});
  }

  withdraw() {
    this.user.balance -= this.withdrawalAmount;
    this.router.navigate(['home'], {state: {user: this.user}});
  }
}
