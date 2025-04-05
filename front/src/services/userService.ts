import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/constants/constants';
import { User } from 'src/app/interfaces/interface';
import { SessionService } from 'src/services/session.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUserUrl = `${BASE_URL}/user`;

  constructor(private http: HttpClient, private sessionService: SessionService) {}

  private getUserUrl(): string {
    const userId = this.sessionService.getUserId();
    if (!userId) {
      throw new Error('User ID not found in session information');
    }
    return `${this.baseUserUrl}/${userId}`;
  }

  getUser(): Observable<User> {
    return this.http.get<User>(this.getUserUrl());
  }

  saveUserProfile(user: Partial<User>): Observable<any> {
    return this.http.put(this.getUserUrl(), user);
  }

  unsubscribeTheme(themeId: number): Observable<void> {
    const url = `${this.getUserUrl()}/theme/unsub/${themeId}`;
    return this.http.patch<void>(url, {});
  }

  subscribeTheme(themeId: number): Observable<void> {
    const url = `${this.getUserUrl()}/theme/sub/${themeId}`;
    return this.http.patch<void>(url, {});
  }
}
