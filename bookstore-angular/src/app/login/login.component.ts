import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HardcodedAuthenticationService} from '../services/hardcoded-authentication.service';
import {CustomUserService} from '../services/custom-user.service';

export class LoginRequest {
  constructor(
    public username: string,
    public password: string,
  ) {}
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private router: Router,
    private hardcodedAuthenticationService: HardcodedAuthenticationService,
    private customUserService: CustomUserService
  ) { }

  loginRequest: LoginRequest;
  username = 'login';
  password = 'haslo';
  errorMessage = 'Invalid credentials';
  invalidLogin = false;

  ngOnInit(): void {
    this.loginRequest = new LoginRequest('', '');
  }

  handleLogin() {
    console.log(this.loginRequest);
    this.customUserService.getAuthentication(this.loginRequest).subscribe(
      data => {
        console.log(data);
        sessionStorage.setItem('token', data.token);
        this.hardcodedAuthenticationService.authenticate(this.loginRequest);
        this.invalidLogin = false;
        this.router.navigate(['welcome']);
      }, (error => {
        console.log(error);
        this.invalidLogin = true;
      })
    );
  }

}
