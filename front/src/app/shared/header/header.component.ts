import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon'; // Import MatIconModule
import { CommonModule } from '@angular/common'; // Import CommonModule for Angular directives like *ngIf

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {}

// import { Component } from '@angular/core';
// import { MatIconModule } from '@angular/material/icon';
// import { CommonModule } from '@angular/common';
// import { RouterModule } from '@angular/router';

// @Component({
//   selector: 'app-header',
//   templateUrl: './header.component.html',
//   styleUrls: ['./header.component.scss'],
//   standalone: true,
//   imports: [CommonModule, RouterModule, MatIconModule] // Import dependencies here
// })
// export class HeaderComponent {}

