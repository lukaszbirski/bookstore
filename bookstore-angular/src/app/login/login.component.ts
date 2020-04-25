import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HardcodedAuthenticationService} from '../services/hardcoded-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private router: Router,
    private hardcodedAuthenticationService: HardcodedAuthenticationService
  ) { }

  username = 'login';
  password = 'haslo';
  errorMessage = 'Invalid credentials';
  invalidLogin = false;

  ngOnInit(): void {
  }

  handleLogin() {
    if (this.hardcodedAuthenticationService.authenticate(this.username, this.password)) {
      this.router.navigate(['welcome']);
      this.invalidLogin = false;
    } else {
      this.invalidLogin = true;
    }
  }

}
