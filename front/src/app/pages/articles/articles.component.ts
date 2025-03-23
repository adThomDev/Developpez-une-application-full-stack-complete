import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { ArticleService } from 'src/services/articleService';

@Component({
  selector: 'app-articles',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: any[] = [];

  constructor(private articleService: ArticleService, private router: Router) {}

  ngOnInit(): void {
    this.articleService.getArticles().subscribe((data) => {
      this.articles = data;
    });
  }

  createArticle(): void {
    this.router.navigate(['/create-article']);
  }
}
