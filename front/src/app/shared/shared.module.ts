import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon'; // Import MatIconModule
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [HeaderComponent],
  imports: [CommonModule, RouterModule, MatIconModule], // Add MatIconModule here
  exports: [HeaderComponent]
})
export class SharedModule {}

// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { RouterModule } from '@angular/router';

// @NgModule({
//   declarations: [], // No need to declare HeaderComponent
//   imports: [CommonModule, RouterModule], // Keep other necessary imports
//   exports: [] // No need to export HeaderComponent
// })
// export class SharedModule {}

