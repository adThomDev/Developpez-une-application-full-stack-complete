import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
    standalone: true,
    imports: [CommonModule, NgIf, RouterOutlet, RouterModule, HeaderComponent]
})
export class AppComponent {
  title = 'MDD';

  constructor(private router: Router) {}

  get isLandingOrLoginOrRegisterPage(): boolean {
    return this.router.url === '/' || this.router.url === '/login' || this.router.url === '/register';
  }
}