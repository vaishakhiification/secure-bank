import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../models/user/user";
import {Router} from "@angular/router";

@Component({
  selector: 'topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit {
  @Input() userInfo: User;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  editProfile() {
    this.router.navigate(['profile'], {state: {user: this.userInfo}});
  }
}
