import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  loggedUser: any;
  helper: any;
  userMail: string;

  constructor(private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
    //this.loggedUser = JSON.parse(this.cookieService.get('loggedUser'));
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
        }
      )
  }
}
