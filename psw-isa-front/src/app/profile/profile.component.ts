import { Component, OnInit } from '@angular/core';
import { NgForm, FormsModule, EmailValidator } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { CookieService } from 'ngx-cookie-service';
import { jsonpFactory } from '@angular/http/src/http_module';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FirstLoginComponent } from '../first-login/first-login.component';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  loggedUser: any;
  helper: any;
  userMail: string;
  ime: string;
  prezime: string;
  adresa: string;
  password: string;



  constructor(private http: HttpClient,
    private router: Router,
    private cookieService: CookieService,
    private modalService: NgbModal,
    private userService: UserService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if(this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    this.getUser();



  }

  onSubmit() {
    this.userService.UpdateUser(this.ime, this.prezime, this.adresa, this.loggedUser.id)
      .subscribe(
        (user: any) => {
          this.router.navigate(['/profile']);
        }, (error) => alert(error.text)
      );
  }

  getUser() {
    this.userService.getUserByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
          this.ime = this.loggedUser.firstName;
          this.prezime = this.loggedUser.lastName;
          this.adresa = this.loggedUser.address;
        }
      )
  }

}