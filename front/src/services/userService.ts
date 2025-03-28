import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/constants/constants';
import { User } from 'src/app/interfaces/interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // private userUrl = 'assets/mocks/user.json';
  //TODO passer le bon user lorsque l'auth sera en place
  private userUrl = `${BASE_URL}/user/1`;

  constructor(private http: HttpClient) {}

  getUser(): Observable<any> {
    return this.http.get<any>(this.userUrl);
  }

  saveUserProfile(user: Partial<User>): Observable<any> {
    return this.http.put(`${this.userUrl}`, user);
  }
  
}