import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VacationService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router) { }

  getPending() {
    return this.http.get("http://localhost:9090/api/patient")
    .pipe(
      map((response: any) => {
        const data = response.json();
        return data;
      }),
      catchError((err: any) => {
        return throwError(JSON.parse(err.text()));
      })
    );
  }

  acceptRequest(id) {
    return this.http.put("http://localhost:9090/api/administrator/acceptRequest/"+id, {
      id: id,
    })
    .pipe(
      map((response: any) => {
        const data = response.json();
        return data;
      }),
      catchError((err: Response) => {
        return throwError(JSON.parse(err.text()));
      })
    );
  }

  denyRequest(id) {
    return this.http.put("http://localhost:9090/api/administrator/denyRequest/"+id, {
      id: id,
    })
    .pipe(
      map((response: Response) => {
        const data = response.json();
        return data;
      }),
      catchError((err: Response) => {
        return throwError(JSON.parse(err.text()));
      })
    );
  }
}
