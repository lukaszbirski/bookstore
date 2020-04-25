import { Component, OnInit } from '@angular/core';
import {CustomUserService} from '../services/http/custom-user.service';
import {Router} from '@angular/router';

export class User {
  constructor(
    public firstName: string,
    public lastName: string,
    public username: string,
    public zipCode: string,
    public city: string,
    public confirmPassword: string,
    public password: string,
    public address: string
  ) {
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  public user: User;
  public firstNameError: string;
  public lastNameError: string;
  public addressError: string;
  public emailError: string;
  public passwordError: string;
  public zipCodeError: string;
  public cityError: string;

  constructor(
    private customUserService: CustomUserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.user = new User('', '', '', '', '', '', '', '');
  }

  handleSignUp() {
    console.log(this.user);
    this.customUserService.createUser(this.user).subscribe(
      data => {
        this.router.navigate(['login']);
      }, error => {
        this.firstNameError = error.firstName;
        this.lastNameError = error.lastName;
        this.addressError = error.address;
        this.emailError = error.username;
        this.passwordError = error.password;
        this.zipCodeError = error.zipCode;
        this.cityError = error.city;
        console.log(error);
      }
    );
  }
}
