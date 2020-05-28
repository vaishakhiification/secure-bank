import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {Router} from "@angular/router";
import {UserService} from "../../services/user/user.service";
import {AuthenticationService} from "../../services/authentication/authentication.service";

@Component({
  selector: 'topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit {
  @Input() userInfo: User;

  constructor(private router: Router,
              private userService: UserService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
  }

  editProfile() {
    this.router.navigate(['profile'], {state: {user: this.userInfo}});
  }

  logout() {
    this.userService.logout().subscribe(result => {
      if (result && result.statusCode == 200) {
        this.router.navigate(['login']).then(r => {
          this.authenticationService.logout();
          alert("Successfully logged out!");
        });
      } else {
        alert("Error: " + result.responseMessage);
      }
    });
  }

  goToHome() {
    this.router.navigate(['home'], {state: {user: this.userInfo}});
  }
}
