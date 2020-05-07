import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HardcodedAuthenticationService} from '../services/hardcoded-authentication.service';
import {CustomUserService} from '../services/http/custom-user.service';

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
        this.checkIfAdmin(data);
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

  checkIfAdmin(data) {
    const jwtData = data.token.split('.')[1];
    const decodedJwtJsonData = window.atob(jwtData);
    const decodedJwtData = JSON.parse(decodedJwtJsonData);
    let step;
    for (step = 0; step < decodedJwtData.roles.length; step++) {
      const isAdmin = decodedJwtData.roles[step].authority;
      if (isAdmin === 'ROLE_ADMIN') {
        this.hardcodedAuthenticationService.authenticateAdmin();
      }
    }
  }


}
