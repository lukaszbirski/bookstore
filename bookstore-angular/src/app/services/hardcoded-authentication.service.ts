import { Injectable } from '@angular/core';
import {CustomUserService} from './custom-user.service';
import {LoginRequest} from '../login/login.component';

@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() { }

  authenticate(loginRequest) {
      sessionStorage.setItem('authenticatedUser', loginRequest.username);
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('authenticatedUser');
    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem('authenticatedUser');
    sessionStorage.removeItem('token');
  }
}
