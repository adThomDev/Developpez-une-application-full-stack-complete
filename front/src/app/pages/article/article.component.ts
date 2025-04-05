import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router';
import {
  HttpClientModule,
  HTTP_INTERCEPTORS,
  HttpClient,
} from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { ArticleService } from 'src/services/articleService';
import { JwtInterceptor } from 'src/app/interceptors/jwt.interceptor';
import { Article } from 'src/app/interfaces/interface';

@Component({
  selector: 'app-article',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    MatIconModule,
  ],
  providers: [
    ArticleService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss'],
})
export class ArticleComponent implements OnInit {
  article: any = null;
  comments: any[] = [];
  newComment = '';

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) {}

  ngOnInit(): void {
    const articleId = Number(this.route.snapshot.paramMap.get('id'));

    this.articleService.getArticleById(articleId).subscribe({
      next: (data) => {
        this.article = data;
        console.log('Fetched article:', this.article);
      },
      error: (err) => {
        console.error('Error fetching article:', err);
      },
    });
  }

  addComment(): void {
    if (this.newComment.trim() && this.article) {
      const articleId = this.article.id;
      const content = this.newComment;
  
      this.articleService.addCommentary(articleId, content).subscribe({
        next: () => {
          console.log('Comment added successfully');
          this.newComment = '';
          this.refreshArticle(articleId);
        },
        error: (err) => {
          console.error('Error adding comment:', err);
        },
      });
    }
  }

  private refreshArticle(articleId: number): void {
    this.articleService.getArticleById(articleId).subscribe({
      next: (data) => {
        this.article = data;
        console.log('Article refreshed:', this.article);
      },
      error: (err) => {
        console.error('Error refreshing article:', err);
      },
    });
  }

}
