import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { ThemeService } from 'src/services/themeService';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-article-creation',
  standalone: true,
  imports: [CommonModule, FormsModule, MatIconModule, RouterModule],
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

  constructor(private themeService: ThemeService, private router: Router) {}

  ngOnInit(): void {
    this.themeService.getThemes().subscribe((data) => {
      this.themes = data;
    });
  }

  createArticle(): void {
    //TODO
    console.log('Article created:', this.article);
    this.router.navigate(['/articles']);
  }
}
