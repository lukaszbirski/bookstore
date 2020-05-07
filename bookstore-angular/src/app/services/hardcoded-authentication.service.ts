import { Injectable } from '@angular/core';
import {CustomUserService} from './http/custom-user.service';
import {LoginRequest} from '../login/login.component';

@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() { }

  authenticate(loginRequest) {
      sessionStorage.setItem('authenticatedUser', loginRequest.username);
  }

  authenticateAdmin() {
    sessionStorage.setItem('role', 'ADMIN');
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('authenticatedUser');
    return !(user === null);
  }

  isAdmin() {
    const user = sessionStorage.getItem('role');
    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem('authenticatedUser');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('role');
  }
}
