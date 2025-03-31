import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from 'src/services/userService';
import { ThemeService } from 'src/services/themeService';
import { User } from 'src/app/interfaces/interface';
import { Theme } from 'src/app/interfaces/interface';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  user: User = {
    username: '',
    email: '',
    subscribedThemes: []
  };
  themes: Theme[] = [];

  //TODO temporaire : userId
  private userId: number = 1;

  constructor(private userService: UserService, private themeService: ThemeService) {}

  ngOnInit(): void {
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
    this.user.subscribedThemes = this.user.subscribedThemes.filter((id: number) => id !== themeId);
  }

  saveProfile(): void {
    const updatedUser = {
      username: this.user.username,
      email: this.user.email,
      password: (document.getElementById('password') as HTMLInputElement).value
    };
  
    this.userService.saveUserProfile(updatedUser).subscribe({
      next: (response) => {
        console.log('Profile successfully updated:', response);
        alert('Profil sauvegardé');
      },
      error: (err) => {
        console.error('Error updating profile:', err);
        alert('Une erreur s\'est produite lors de la sauvegarde.');
      }
    });

    (document.getElementById('password') as HTMLInputElement).value = '';
  }
  
  
}
