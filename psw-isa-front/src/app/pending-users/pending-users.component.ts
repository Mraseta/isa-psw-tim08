import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-pending-users',
  templateUrl: './pending-users.component.html',
  styleUrls: ['./pending-users.component.css']
})
export class PendingUsersComponent implements OnInit {
  pendingUsers = [];
  helper: any;

  constructor(private router: Router,
    private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if(this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.getPendingUsers();
  }

  getPendingUsers() {
    this.userService.getPending()
      .subscribe(
        (data) => {
          console.log(data);
          this.pendingUsers = Object.assign([], (data));
        }
      )
  }

  prihvati(id) {
    this.userService.acceptUser(id)
      .subscribe(
        () => {
          this.getPendingUsers();
        }
      )
  }

  odbij(id) {
    this.userService.denyUser(id)
    .subscribe(
      () => {
        this.getPendingUsers();
      }
    )
  }

}
