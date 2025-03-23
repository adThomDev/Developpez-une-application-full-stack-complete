import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-article',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, MatIconModule],
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {
  article: any = null;
  comments: any[] = [];
  newComment = '';

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const articleId = Number(this.route.snapshot.paramMap.get('id'));

    this.http.get<any[]>('assets/mocks/articles.json').subscribe((articles) => {
      this.article = articles.find((article) => article.id === articleId);
    });

    this.http.get<any[]>('assets/mocks/comments.json').subscribe((data) => {
      this.comments = data.filter((comment) => comment.articleId === articleId);
    });
  }

  addComment(): void {
    if (this.newComment.trim()) {
      this.comments.push({
        id: this.comments.length + 1,
        articleId: this.article.id,
        usernameId: 'username',
        content: this.newComment
      });
      this.newComment = '';
    }
  }
}