import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {Router} from "@angular/router";
import {Transaction} from "../../models/transaction/transaction";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.scss']
})
export class WithdrawComponent implements OnInit {
  user: User;
  withdrawalAmount: number;

  constructor(private router: Router, private userService: UserService) {
    this.user = this.router.getCurrentNavigation().extras.state.user;
    this.withdrawalAmount = 0;
  }

  ngOnInit(): void {
  }

  back() {
    this.router.navigate(['home'], {state: {user: this.user}});
  }

  withdraw() {
    if (this.withdrawalAmount > this.user.balance) {
      alert("The withdrawal amount must be less than or equal to the current balance!");
      return;
    }
    const transaction = new Transaction();
    transaction.user = this.user;
    transaction.amount = this.withdrawalAmount;
    this.userService.withdraw(transaction).subscribe(
      result => {
        if (result && result.statusCode === 200) {
          this.user.balance -= this.withdrawalAmount;
          this.router.navigate(['home'], {state: {user: this.user}});
        } else {
          alert("Error: " + result.responseMessage);
        }
      }
    );
  }
}
