import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  user: User;

  constructor(private userService: UserService) {
    this.user = new User();
  }

  ngOnInit(): void {
  }

  registerUser() {
    this.userService.save(this.user).subscribe(result => alert("User Saved!"));
  }
}
