import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/constants/constants';
import { Article } from 'src/app/interfaces/interface';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  // mocks :
  // private articlesUrl = 'assets/mocks/articles.json';
  private articlesUrl = `${BASE_URL}/article`;

  constructor(private http: HttpClient) {}

  getArticles(): Observable<any[]> {
    return this.http.get<any[]>(this.articlesUrl);
  }

  getArticleById(id: number): Observable<any> {
    const url = `${this.articlesUrl}/${id}`;
    return this.http.get<any>(url);
  }

  addCommentary(articleId: number, content: string): Observable<any> {
    const url = `${this.articlesUrl}/${articleId}/commentary`;
    return this.http.post<any>(url, { content });
  }

  createArticle(article: {
    themeId: string;
    title: string;
    content: string;
  }): Observable<any> {
    const url = `${this.articlesUrl}`;
    return this.http.post<any>(url, article);
  }
}
