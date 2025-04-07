import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { JwtInterceptor } from 'src/app/interceptors/jwt.interceptor';
import { UserService } from 'src/services/userService';
import { ThemeService } from 'src/services/themeService';
import { SessionService } from 'src/services/session.service';
import { User, Theme } from 'src/app/interfaces/interface';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
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
  public form = this.fb.group({
    username: ['', [Validators.minLength(6), Validators.maxLength(50)]],
    email: ['', [Validators.email, Validators.maxLength(50)]],
    password: ['', [Validators.minLength(6), Validators.maxLength(50)]],
  });

  user: User = {
    username: '',
    email: '',
    subscribedThemes: [],
  };
  themes: Theme[] = [];
  private userId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private themeService: ThemeService,
    private sessionService: SessionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userId = this.sessionService.getUserId();
    if (!this.userId) {
      console.error('User ID not found in session information');
      return;
    }

    this.userService.getUser().subscribe((data) => {
      this.user = data;
      this.form.patchValue({
        username: this.user.username,
        email: this.user.email,
      });
    });

    this.themeService.getThemesByUserId(this.userId).subscribe((data) => {
      this.themes = data;
    });
  }

  saveProfile(): void {
    if (this.form.invalid) {
      console.error('Form is invalid');
      return;
    }

    const updatedUser: Partial<User> = Object.fromEntries(
      Object.entries(this.form.value).map(([key, value]) => [key, value ?? undefined])
    );

    this.userService.saveUserProfile(updatedUser).subscribe({
      next: () => {
        console.log('Profile successfully updated.');
        alert('Profil sauvegardé avec succès.');

        this.sessionService.logOut();
        alert('Vos informations ont été mises à jour. Veuillez vous reconnecter.');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Error updating profile:', err);
        alert("Une erreur s'est produite lors de la sauvegarde.");
      },
    });

    this.form.get('password')?.reset();
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
}