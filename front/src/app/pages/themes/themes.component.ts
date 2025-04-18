import { Component, OnInit, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from 'src/app/interceptors/jwt.interceptor';
import { ThemeService } from 'src/services/themeService';
import { UserService } from 'src/services/userService';
import { Theme, User } from 'src/app/interfaces/interface';

@Component({
  selector: 'app-themes',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  providers: [
    ThemeService,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss'],
})
export class ThemesComponent implements OnInit {
  themes: Theme[] = [];
  user: User | null = null;

  constructor(
    private themeService: ThemeService,
    private userService: UserService,
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
    if (!this.user) {
      return false;
    }
    return this.user.subscribedThemes.includes(themeId);
  }

  subscribe(themeId: number): void {
    this.userService.subscribeTheme(themeId).subscribe({
      next: () => {
        console.log(`Successfully subscribed to theme with ID: ${themeId}`);
        this.user?.subscribedThemes.push(themeId);
      },
      error: (err) => {
        console.error(`Error subscribing to theme with ID: ${themeId}`, err);
      },
    });
  }

}
