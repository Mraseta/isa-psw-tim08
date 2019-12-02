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
export class ClinicService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router) { }

  addClinic(
    name: string,
    address: string,
    description: string,
    ) {
    return this.http.post("http://localhost:9090/api/clinic/addClinic", {
      name: name,
      address: address,
      description: description,
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
}
