import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private cookieService : CookieService,
    private router : Router,
    private userService : UserService

  ) { }

  ngOnInit() {
  }
  homeClick() {
    this.router.navigate(['/']);
  }
  profClick() {
    this.router.navigate(['/profile'])
  }
  logoutClick() {
    this.router.navigate(['/login'])
  }

}
