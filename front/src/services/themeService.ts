import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/constants/constants';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  // private themesUrl = 'assets/mocks/themes.json';
  private themesUrl = `${BASE_URL}/theme`;
  private themesUrlById = `${BASE_URL}/theme/user/`;

  constructor(private http: HttpClient) {}

  getThemes(): Observable<any[]> {
    return this.http.get<any[]>(this.themesUrl);
  }

  getThemesByUserId(userId: number): Observable<any[]> {
    return this.http.get<any[]>(this.themesUrlById + userId);
  }
}