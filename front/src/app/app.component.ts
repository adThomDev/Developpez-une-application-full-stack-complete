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

// import { Component } from '@angular/core';
// import { Router, RouterModule } from '@angular/router';
// import { CommonModule } from '@angular/common'; // Import CommonModule for *ngIf
// import { HeaderComponent } from './shared/header/header.component'; // Import the standalone HeaderComponent

// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.scss'],
//   standalone: true, // Mark AppComponent as standalone
//   imports: [
//     CommonModule, // Import CommonModule for *ngIf
//     RouterModule, // Import RouterModule for router-outlet
//     HeaderComponent // Import HeaderComponent
//   ]
// })
// export class AppComponent {
//   title = 'MDD';

//   constructor(private router: Router) {} // Inject the Router service

//   get isLandingOrLoginOrRegisterPage(): boolean {
//     return this.router.url === '/' || this.router.url === '/login' || this.router.url === '/register';
//   }
// }

