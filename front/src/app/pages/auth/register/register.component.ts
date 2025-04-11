import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { RegisterRequest } from 'src/app/interfaces/interface';
import { AuthService } from 'src/app/pages/auth/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  isSmartphone: boolean = false;
  public onError = false;

  public form = this.fb.group({
    email: [
      '',
      [Validators.required, Validators.email, Validators.maxLength(50)],
    ],
    username: [
      '',
      [Validators.required, Validators.minLength(6), Validators.maxLength(50)],
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(8),
        this.authService.passwordStrengthValidator,
      ],
    ],
  });

  constructor(
    private breakpointObserver: BreakpointObserver,
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .subscribe((result) => {
        this.isSmartphone = result.matches;
      });
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe({
      next: (response) => {
        this.router.navigate(['/login']);
        console.log('Account successfully registered :', response);
        alert('Compte créé avec succés');
      },
      error: (err) => {
        this.onError = true;
        console.error('Error during registration :', err);
      },
    });
  }
}
