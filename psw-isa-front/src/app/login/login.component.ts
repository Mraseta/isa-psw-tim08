import { Component, OnInit, NgModule } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user/user.service';
import { JwtHelperService } from '@auth0/angular-jwt'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email: string;
  password: string;
  helper : any;

  constructor(private http: HttpClient,
    private router: Router,
    private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    this.userService.postUser(this.email, this.password)
    .subscribe(
      (user: any) => {
        this.helper = new JwtHelperService();
        this.cookieService.set('loggedUser', JSON.stringify(user));
       

        let status = this.helper.decodeToken(this.cookieService.get('token')).status;
        let userType = this.helper.decodeToken(this.cookieService.get('token')).type;
        console.log(status + userType)
        if(userType === "P" && status !== "ACTIVE"){
          this.router.navigate(['/login']);
        }else if((userType === 'D' || userType === 'N' || userType === 'CCA' || userType === 'A') && status === 'PENDING'){
          console.log('dosao da rerutujem doktore i sestre')
          this.router.navigate(['/changePassword']);
        }
        else{
          this.router.navigate(['/']);
        }
        
      }, (error) => alert(error.text)
    );
  }

}
