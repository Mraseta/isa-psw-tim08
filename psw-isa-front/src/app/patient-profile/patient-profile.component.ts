import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Inject} from '@angular/core';

import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { CookieService } from 'ngx-cookie-service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JwtHelperService } from '@auth0/angular-jwt';
@Component({
  selector: 'app-patient-profile',
  templateUrl: './patient-profile.component.html',
  styleUrls: ['./patient-profile.component.css']
})
export class PatientProfileComponent implements OnInit {
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
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    this.getUser();
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
