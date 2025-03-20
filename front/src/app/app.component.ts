import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'MDD';

  constructor(private router: Router) {}

  get isLandingOrLoginOrRegisterPage(): boolean {
    return this.router.url === '/' || this.router.url === '/login' || this.router.url === '/register';
  }
}
