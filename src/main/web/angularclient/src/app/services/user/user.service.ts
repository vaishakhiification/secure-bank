import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../../models/user/user';
import {Transaction} from "../../models/transaction/transaction";
import {UserResponse} from "../../models/userResponse/user-response";
import {Response} from "../../models/response/response";
import {EditProfileDetails} from "../../models/editProfileDetails/edit-profile-details";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly registerURL: string;
  private readonly loginURL: string;
  private readonly depositURL: string;
  private readonly withdrawURL: string;
  private readonly resetPasswordURL: string;
  private readonly editProfileURL: string;
  private readonly logoutURL: string;

  constructor(private http: HttpClient) {
    this.registerURL = 'http://localhost:8080/register';
    this.loginURL = 'http://localhost:8080/login';
    this.depositURL = 'http://localhost:8080/deposit';
    this.withdrawURL = 'http://localhost:8080/withdraw';
    this.resetPasswordURL = 'http://localhost:8080/resetPassword';
    this.editProfileURL = 'http://localhost:8080/editProfile';
    this.logoutURL = 'http://localhost:8080/logout';
  }

  public save(user: User) {
    return this.http.post<Response>(this.registerURL, user);
  }
  public login(user: User) {
    return this.http.post<UserResponse>(this.loginURL, user);
  }

  public deposit(transaction: Transaction) {
    return this.http.post<Response>(this.depositURL, transaction);
  }

  public withdraw(transaction: Transaction) {
    return this.http.post<Response>(this.withdrawURL, transaction);
  }

  public resetPassword(user: User) {
    return this.http.post<Response>(this.resetPasswordURL, user);
  }

  public editProfile(editProfileDetails: EditProfileDetails) {
    return this.http.post<Response>(this.editProfileURL, editProfileDetails);
  }

  public logout() {
    return this.http.post<Response>(this.logoutURL, null);
  }


}
