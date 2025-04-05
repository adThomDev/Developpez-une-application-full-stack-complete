import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from 'src/app/interceptors/jwt.interceptor';
import { UserService } from 'src/services/userService';
import { ThemeService } from 'src/services/themeService';
import { User, Theme } from 'src/app/interfaces/interface';
import { SessionService } from 'src/services/session.service';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  providers: [
    ThemeService,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
})
export class UserProfileComponent implements OnInit {
  user: User = {
    username: '',
    email: '',
    subscribedThemes: [],
  };
  themes: Theme[] = [];
  private userId: number | null = null;

  constructor(
    private userService: UserService,
    private themeService: ThemeService,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.userId = this.sessionService.getUserId();
    if (!this.userId) {
      console.error('User ID not found in session information');
      return;
    }

    this.userService.getUser().subscribe((data) => {
      this.user = data;
    });

    this.themeService.getThemesByUserId(this.userId).subscribe((data) => {
      this.themes = data;
    });
  }

  isSubscribed(themeId: number): boolean {
    return this.user?.subscribedThemes.includes(themeId);
  }

  unsubscribe(themeId: number): void {
    if (!this.userId) {
      console.error('User ID not found in session');
      return;
    }
  
    this.userService.unsubscribeTheme(themeId).subscribe({
      next: () => {
        console.log(`Successfully unsubscribed from theme with ID: ${themeId}`);
        this.user.subscribedThemes = this.user.subscribedThemes.filter(
          (id: number) => id !== themeId
        );
  
        this.themeService.getThemesByUserId(this.userId!).subscribe((data) => {
          this.themes = data;
        });
      },
      error: (err) => {
        console.error(`Error unsubscribing from theme with ID: ${themeId}`, err);
      },
    });
  }

  saveProfile(): void {
    const updatedUser = {
      username: this.user.username,
      email: this.user.email,
      password: (document.getElementById('password') as HTMLInputElement).value,
    };

    this.userService.saveUserProfile(updatedUser).subscribe({
      next: (response) => {
        console.log('Profile successfully updated :', response);
        alert('Profil sauvegardé');
      },
      error: (err) => {
        console.error('Error updating profile :', err);
        alert("Une erreur s'est produite lors de la sauvegarde.");
      },
    });

    (document.getElementById('password') as HTMLInputElement).value = '';
  }
}