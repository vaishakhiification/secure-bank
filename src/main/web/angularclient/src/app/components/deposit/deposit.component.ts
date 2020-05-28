import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user/user";
import {UserService} from "../../services/user/user.service";
import {Transaction} from "../../models/transaction/transaction";

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {

  user: User;
  depositAmount: number;

  constructor(private router: Router, private userService: UserService) {
    this.user = this.router.getCurrentNavigation().extras.state.user;
    this.depositAmount = 0;
  }

  ngOnInit(): void {
  }

  back() {
    this.router.navigate(['home'], {state: {user: this.user}});
  }

  deposit() {
    const transaction = new Transaction();
    transaction.user = this.user;
    transaction.amount = this.depositAmount;
    this.userService.deposit(transaction).subscribe(
      result => {
        if (result && result.statusCode === 200) {
          this.user.balance += this.depositAmount;
          this.router.navigate(['home'], {state: {user: this.user}});
        } else {
          alert("Error: " + result.responseMessage);
        }
      }
    );
  }
}
