import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { ArticleService } from 'src/services/articleService';
import { DateUtilsService } from 'src/services/date-utils.service';
import { Article } from 'src/app/interfaces/interface';
import { JwtInterceptor } from 'src/app/interceptors/jwt.interceptor';

@Component({
  selector: 'app-articles',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule, MatIconModule],
  providers: [
    ArticleService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [];
  isDescending: boolean = true;

  constructor(
    private articleService: ArticleService,
    private dateUtilsService: DateUtilsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.articleService.getArticles().subscribe((data) => {
      this.articles = data
        .map((article) => ({
          ...article,
          createdAt: this.dateUtilsService.formatDate(article.createdAt),
        }))
        .sort((a, b) => this.compareDates(a.createdAt, b.createdAt));
    });
  }

  createArticle(): void {
    this.router.navigate(['/create-article']);
  }

  toggleSortOrder(): void {
    this.isDescending = !this.isDescending;
    this.articles.reverse();
  }

  private compareDates(date1: string, date2: string): number {
    const d1 = new Date(date1).getTime();
    const d2 = new Date(date2).getTime();
    return this.isDescending ? d2 - d1 : d1 - d2;
  }
}
