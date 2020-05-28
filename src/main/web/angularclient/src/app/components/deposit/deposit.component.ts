import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user/user";
import {UserService} from "../../services/user/user.service";
import {Transaction} from "../../models/transaction/transaction";
import {AuthenticationService} from "../../services/authentication/authentication.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {
  currentUserSubscription: Subscription;
  user: User;
  depositAmount: number;

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
    this.depositAmount = 0;
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
