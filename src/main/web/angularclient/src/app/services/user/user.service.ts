import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly registerURL: string;

  constructor(private http: HttpClient) {
    this.registerURL = 'http://localhost:8080/register';
  }

  public save(user: User) {
    return this.http.post<User>(this.registerURL, user);
  }
}
