import { Component, OnInit } from '@angular/core';
import { ThemeService } from 'src/services/themeService';
import { UserService } from 'src/services/userService';
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
  user: any = null;

  constructor(
    private themeService: ThemeService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.themeService.getThemes().subscribe((data) => {
      this.themes = data;
    });

    this.userService.getUser().subscribe((data) => {
      this.user = data;
    });
  }

  isSubscribed(themeId: number): boolean {
    return this.user?.subscribedThemes.includes(themeId);
  }
}