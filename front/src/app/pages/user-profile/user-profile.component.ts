import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from 'src/services/userService';
import { ThemeService } from 'src/services/themeService';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  user: any = {
    username: '',
    email: '',
    subscribedThemes: []
  };
  themes: any[] = [];

  constructor(private userService: UserService, private themeService: ThemeService) {}

  ngOnInit(): void {
    this.userService.getUser().subscribe((data) => {
      this.user = data;
    });

    this.themeService.getThemes().subscribe((data) => {
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
    console.log('Profile saved:', this.user);
    // TODO
  }
}
