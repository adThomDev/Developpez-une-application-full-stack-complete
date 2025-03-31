import { HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { SessionService } from 'src/services/session.service';

@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  constructor(private sessionService: SessionService) {}

  // public intercept(request: HttpRequest<any>, next: HttpHandler) {
  //   if (this.sessionService.isLogged) {
  //     request = request.clone({
  //       setHeaders: {
  //         Authorization: `Bearer ${this.sessionService.sessionInformation!.token}`,
  //       },
  //     });
  //   }
  //   return next.handle(request);
  // }

  public intercept(request: HttpRequest<any>, next: HttpHandler) {
    console.log('JwtInterceptor triggered for:', request.url);
  
    const token = this.sessionService.getToken();
    console.log('Token:', token);
  
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
      console.log('Authorization header added:', request.headers.get('Authorization'));
    } else {
      console.log('No token found, request sent without Authorization header.');
    }
  
    return next.handle(request);
  }
}
