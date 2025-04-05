import { Injectable } from '@angular/core';
import { SessionInformation } from 'src/app/interfaces/interface';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  private readonly SESSION_KEY = 'sessionInfo';
  public sessionInformation: SessionInformation | null = null;

  get isLogged(): boolean {
    if (!this.sessionInformation) {
      this.loadSession();
    }
    return !!this.sessionInformation?.token;
  }

  logIn(sessionInfo: SessionInformation): void {
    this.sessionInformation = sessionInfo;
    localStorage.setItem(this.SESSION_KEY, JSON.stringify(sessionInfo));
  }

  getToken(): string | null {
    return this.sessionInformation?.token || this.loadSession()?.token || null;
  }

  getUserId(): number | null {
    return this.sessionInformation?.id || this.loadSession()?.id || null;
  }

  logOut(): void {
    this.sessionInformation = null;
    localStorage.removeItem(this.SESSION_KEY);
  }

  loadSession(): SessionInformation | null {
    if (!this.sessionInformation) {
      const session = localStorage.getItem(this.SESSION_KEY);
      if (session) {
        this.sessionInformation = JSON.parse(session);
      }
    }
    return this.sessionInformation;
  }
}
