import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user/user";
import {EditProfileDetails} from "../../models/editProfileDetails/edit-profile-details";
import {UserService} from "../../services/user/user.service";
import {Subscription} from "rxjs";
import {AuthenticationService} from "../../services/authentication/authentication.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: User;
  newUserInfo: User;
  oldPassword: string;
  newPassword: string;
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
              this.newUserInfo = Object.assign({}, this.user);
            } else {
              alert("Error: " + result.responseMessage);
              return null;
            }
          }
        );
      }
    });
    this.newPassword = "";
    this.oldPassword = "";
  }

  ngOnInit(): void {
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
  }

  updateUser() {
    if (this.oldPassword !== this.user.password) {
      alert("Incorrect Password!");
      return;
    }
    const editProfileDetails = new EditProfileDetails();
    editProfileDetails.newUserDetails = this.newUserInfo;
    editProfileDetails.userDetails = this.user;

    this.userService.editProfile(editProfileDetails).subscribe(result => {
      if (result && result.statusCode == 200) {
        this.user = Object.assign({}, this.newUserInfo);
        this.router.navigate(['login'], {state: {user: this.user}}).then(r => {
          alert("New Profile Changes Saved!");
        });
      } else {
        alert("Error: " + result.responseMessage);
      }
    });
  }
}
