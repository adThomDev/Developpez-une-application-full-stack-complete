import { Component, HostListener, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule, MatDrawer } from '@angular/material/sidenav';
import { SessionService } from 'src/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    MatSidenavModule,
    MatDrawer,
  ],
})
export class HeaderComponent {
  isSmallScreen: boolean = false;
  isProfileActive: boolean = false;
  @ViewChild('drawer') drawer!: MatDrawer;

  constructor(private sessionService: SessionService, private router: Router) {}

  @HostListener('window:resize', [])
  onResize() {
    this.checkScreenSize();
  }

  ngOnInit() {
    this.checkScreenSize();
    this.router.events.subscribe(() => {
      this.isProfileActive = this.router.url === '/profile';
    });
  }

  logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['/']);
  }

  checkScreenSize() {
    this.isSmallScreen = window.innerWidth <= 500;
  }

  toggleSidePanel() {
    if (this.drawer) this.drawer.toggle();
  }
}
