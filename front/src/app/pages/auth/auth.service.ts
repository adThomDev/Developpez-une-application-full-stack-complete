import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from 'src/app/interfaces/interface';
import { RegisterRequest } from 'src/app/interfaces/interface';
import { SessionInformation } from 'src/app/interfaces/interface';
import { BASE_URL } from 'src/app/constants/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = `${BASE_URL}/auth`;

  constructor(private httpClient: HttpClient) { }

  public register(registerRequest: RegisterRequest): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(`${this.pathService}/login`, loginRequest);
  }
}
