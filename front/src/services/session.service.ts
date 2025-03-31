import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { SessionInformation } from 'src/app/interfaces/interface';

@Injectable({
  providedIn: 'root'
})

export class SessionService {
  private readonly TOKEN_KEY = 'authToken';
  public sessionInformation: SessionInformation | null = null;

  get isLogged(): boolean {
    return !!this.sessionInformation?.token;
  }

  logIn(sessionInfo: SessionInformation): void {
    this.sessionInformation = sessionInfo;
    localStorage.setItem(this.TOKEN_KEY, JSON.stringify(sessionInfo));
  }

  getToken(): string | null {
    return this.sessionInformation?.token || null;
  }

  logOut(): void {
    this.sessionInformation = null;
    localStorage.removeItem(this.TOKEN_KEY);
  }

  loadSession(): void {
    const session = localStorage.getItem(this.TOKEN_KEY);
    if (session) {
      this.sessionInformation = JSON.parse(session);
    }
  }
}
