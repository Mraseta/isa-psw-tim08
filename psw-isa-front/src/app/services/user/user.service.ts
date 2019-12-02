import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt'
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})




export class UserService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router

  ) { }

  postUser(email: string, password: string) {
    return this.http.post("http://localhost:9090/auth/login", {
      username: email,
      password: password
    })
      .pipe(
        map((res: any) => {
          //console.log(response);
          //this.cookieService.set('loggedUser', JSON.stringify(response.json()));
          const helper = new JwtHelperService();
          const token = res.accessToken;
          console.log('token: ' + token)
          this.cookieService.set('token', token);


          const data = res;
          return data;
        }),
        catchError((err: any) => {
          console.log(err)
          return throwError(JSON.parse(err.text))
        })
      )
  }
  RegUser(
    firstname: string,
    lastname: string,
    address: string,
    jmbg: string,
    email: string,
    password: string) {
    return this.http.post("http://localhost:9090/auth/register", {
      firstName: firstname,
      lastName: lastname,
      address: address,
      jmbg: jmbg,
      username: email,
      password: password
    })
      .pipe(
        map((response: any) => {
          //console.log(response);
          //this.cookieService.set('loggedUser', JSON.stringify(response.json()));
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      )
  }
  UpdateUserPassword(
    newPassword: string,
    oldPassword: string) {
    return this.http.post("http://localhost:9090/api/person/changePassword", {
      newPassword: newPassword,
      oldPassword: oldPassword
    })
      .pipe(
        map((response: any) => {
          const data = response;
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      )
  }
  UpdateUser(
    firstname: string,
    lastname: string,
    address: string,
    id: number) {
    return this.http.post("http://localhost:9090/api/person/update", {
      firstName: firstname,
      lastName: lastname,
      address: address,
      id: id
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      )
  }

  getPending() {
    return this.http.get("http://localhost:9090/api/patient")
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      );
  }

  getUserByMail(email: string) {
    return this.http.post("http://localhost:9090/api/person/getByEmail", {
      username: email
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      );
  }

  acceptUser(id) {
    return this.http.put("http://localhost:9090/api/administrator/approveRegistration/" + id, {
      id: id,
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      );
  }

  denyUser(id) {
    return this.http.put("http://localhost:9090/api/administrator/rejectRegistration/" + id, {
      id: id,
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      );
  }
}
