import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/constants/constants';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  // private articlesUrl = 'assets/mocks/articles.json';
  private articlesUrl = `${BASE_URL}/article`;

  constructor(private http: HttpClient) {}

  getArticles(): Observable<any[]> {
    return this.http.get<any[]>(this.articlesUrl);
  }
}