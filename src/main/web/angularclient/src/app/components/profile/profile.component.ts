import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user/user";
import {EditProfileDetails} from "../../models/editProfileDetails/edit-profile-details";
import {UserService} from "../../services/user/user.service";

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

  constructor(private router: Router, private userService: UserService) {
    let state = this.router.getCurrentNavigation().extras.state;
    if (!state) {
      alert("Please login to proceed!");
      this.router.navigate(['login'], {state: {user: this.user}});
    } else {
      this.user = state.user;
      this.newUserInfo = Object.assign({}, this.user);
    }
  }

  ngOnInit(): void {
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
        this.router.navigate(['home'], {state: {user: this.user}}).then(r => {
          alert("New Profile Changes Saved!");
        });
      } else {
        alert("Error: " + result.responseMessage);
      }
    });
  }
}
