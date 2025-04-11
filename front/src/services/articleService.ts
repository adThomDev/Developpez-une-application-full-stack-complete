import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/constants/constants';
import { Article, ArticleCreation, Commentary } from 'src/app/interfaces/interface';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  // mocks :
  // private articlesUrl = 'assets/mocks/articles.json';
  private articlesUrl = `${BASE_URL}/article`;

  constructor(private http: HttpClient) {}

  getArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(this.articlesUrl);
  }

  getArticleById(id: number): Observable<Article> {
    const url = `${this.articlesUrl}/${id}`;
    return this.http.get<Article>(url);
  }

  addCommentary(articleId: number, content: string): Observable<Commentary> {
    const url = `${this.articlesUrl}/${articleId}/commentary`;
    return this.http.post<Commentary>(url, { content });
  }

  createArticle(article: ArticleCreation): Observable<ArticleCreation> {
    const url = `${this.articlesUrl}`;
    return this.http.post<ArticleCreation>(url, article);
  }
}
