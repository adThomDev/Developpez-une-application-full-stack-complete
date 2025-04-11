import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/constants/constants';
import { Theme, User } from 'src/app/interfaces/interface';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  // mocks :
  // private themesUrl = 'assets/mocks/themes.json';
  private themesUrl = `${BASE_URL}/theme`;
  private themesUrlByUser = `${BASE_URL}/theme/user/`;

  constructor(private http: HttpClient) {}

  getThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(this.themesUrl);
  }

  getThemesByUserId(userId: number): Observable<Theme[]> {
    return this.http.get<Theme[]>(this.themesUrlByUser + userId);
  }
}