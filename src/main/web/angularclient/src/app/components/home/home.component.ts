import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {AuthenticationService} from "../../services/authentication/authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  currentUserSubscription: Subscription;
  user: User;

  constructor(private router: Router,
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
  }

  ngOnInit(): void {
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
  }

  deposit() {
    this.router.navigate(['deposit'], {state: {user: this.user}});
  }

  withdraw() {
    this.router.navigate(['withdraw'], {state: {user: this.user}});
  }

}
