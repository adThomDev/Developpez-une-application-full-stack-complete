import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { ArticleService } from 'src/services/articleService';
import { DateUtilsService } from 'src/services/date-utils.service';
import { Article } from 'src/app/interfaces/interface';

@Component({
  selector: 'app-articles',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [];

  constructor(
    private articleService: ArticleService,
    private dateUtilsService: DateUtilsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.articleService.getArticles().subscribe((data) => {
      this.articles = data.map((article) => ({
        ...article,
        createdAt: this.dateUtilsService.formatDate(article.createdAt)
      }));
    });
  }

  createArticle(): void {
    this.router.navigate(['/create-article']);
  }
}
