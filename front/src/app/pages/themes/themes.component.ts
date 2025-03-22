import { Component, OnInit } from '@angular/core';
import { ThemeService } from 'src/services/themeService';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-themes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {
  themes: any[] = [];

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    this.themeService.getThemes().subscribe((data) => {
      this.themes = data;
    });
  }
}
