import { Injectable } from '@angular/core';
import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterAuthService implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    const authenticationString = sessionStorage.getItem('token');
    if (authenticationString) {
      request = request.clone({
          setHeaders : {
            Authorization : authenticationString
          }
        });
    }
    return next.handle(request);
  }
}
