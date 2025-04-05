import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ThemeService } from 'src/services/themeService';
import { MatIconModule } from '@angular/material/icon';
import { ArticleService } from 'src/services/articleService';
import { JwtInterceptor } from 'src/app/interceptors/jwt.interceptor';

@Component({
  selector: 'app-article-creation',
  standalone: true,
  imports: [CommonModule, FormsModule, MatIconModule, RouterModule, HttpClientModule],
  providers: [
    ThemeService,
    ArticleService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  templateUrl: './article-creation.component.html',
  styleUrls: ['./article-creation.component.scss'],
})
export class ArticleCreationComponent implements OnInit {
  themes: any[] = [];
  article = {
    themeId: '',
    title: '',
    content: '',
  };

  constructor(

    private themeService: ThemeService,
    private articleService: ArticleService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.themeService.getThemes().subscribe({
      next: (data) => {
        this.themes = data;
      },
      error: (err) => {
        console.error('Error fetching themes:', err);
      },
    });
  }

  createArticle(): void {
    if (!this.article.themeId || !this.article.title || !this.article.content) {
      alert('Veuillez remplir tous les champs.');
      return;
    }

    this.articleService.createArticle(this.article).subscribe({
      next: () => {
        console.log('Article created successfully:', this.article);
        this.router.navigate(['/articles']);
      },
      error: (err) => {
        console.error('Error creating article:', err);
        alert("Une erreur s'est produite lors de la création de l'article.");
      },
    });
  }
}