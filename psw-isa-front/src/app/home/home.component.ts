import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FirstLoginComponent } from '../first-login/first-login.component';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  userType: any;
  helper: any;
  userPatient = false;
  userAdmin = false;
  userNurse = false;
  userDoctor = false;
  loggedUser: any;
  password: string;
  userMail: string;


  constructor(private cookieService: CookieService,
    private modalService: NgbModal,
    private userService: UserService,
    private router : Router) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if(this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userType = this.helper.decodeToken(this.cookieService.get('token')).type;
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    console.log(this.userMail);
    this.getUser();
    if (this.userType === "P")
      this.userPatient = true;
    if (this.userType === "D")
      this.userDoctor = true;
    if (this.userType === "N")
      this.userNurse = true;
    if (this.userType === "A")
      this.userAdmin = true;
    
  }

  getUser() {
    this.userService.getUserByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
        }
      )
  }

}
