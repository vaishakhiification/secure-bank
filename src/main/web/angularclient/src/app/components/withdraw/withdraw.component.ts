import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {Router} from "@angular/router";
import {Transaction} from "../../models/transaction/transaction";
import {UserService} from "../../services/user/user.service";
import {Subscription} from "rxjs";
import {AuthenticationService} from "../../services/authentication/authentication.service";

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.scss']
})
export class WithdrawComponent implements OnInit {
  user: User;
  withdrawalAmount: number;
  currentUserSubscription: Subscription;

  constructor(private router: Router,
              private userService: UserService,
              private authenticationService: AuthenticationService) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      if (user == null) {
        alert("Please login to proceed!");
        this.router.navigate(['login'],);
      } else {
        this.authenticationService.getUser(user).subscribe(
          result => {
            if (result && result.statusCode === 200) {
              this.user = result.user;
            } else {
              alert("Error: " + result.responseMessage);
              return null;
            }
          }
        );
      }
    });
    this.withdrawalAmount = 0;
  }

  ngOnInit(): void {
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
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
