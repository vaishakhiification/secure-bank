import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../../models/user/user";
import {HttpClient} from "@angular/common/http";
import {UserResponse} from "../../models/userResponse/user-response";

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public currentUserValue() {
    const user = this.currentUserSubject.value;
    this.getUser(user).subscribe(
      result => {
        if (result && result.statusCode === 200) {
          return result.user;
        } else {
          alert("Error: " + result.responseMessage);
          return null;
        }
      }
    );
  }

  public getUser(user: User) {
    return this.http.post<UserResponse>('http://localhost:8080/getUser', user);
  }

  login(user: User) {
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
    return user;
    // return this.http.post<any>(`/users/authenticate`, {username, password})
    //   .pipe(map(user => {
    //     // login successful if there's a jwt token in the response
    //     if (user && user.token) {
    //       // store user details and jwt token in local storage to keep user logged in between page refreshes
    //       localStorage.setItem('currentUser', JSON.stringify(user));
    //       this.currentUserSubject.next(user);
    //     }
    //     return user;
    //   }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
