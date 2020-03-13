import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router : Router) { }

  username = 'login'
  password = 'haslo'
  errorMessage = 'Invalid credentials'
  invalidLogin = false

  ngOnInit(): void {
  }

  handleLogin(){
    if(this.username === "login" && this.password === "haslo"){
      this.router.navigate(['welcome', this.username])
      this.invalidLogin = false;
    } else {
      this.invalidLogin = true;
    }
  }

}
