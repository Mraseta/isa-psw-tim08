import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin/admin.service';


@Component({
  selector: 'app-register-admin',
  templateUrl: './register-admin.component.html',
  styleUrls: ['./register-admin.component.css']
})
export class RegisterAdminComponent implements OnInit {

  firstName:string;
  lastName : string;
  username:string;
  password : string;  
  address :string;
  clinic : any;

  constructor(private http: HttpClient,
    private router: Router,
    private adminService : AdminService) { }

  ngOnInit() {
  }

  addAdmin() {
    this.adminService.registerAdmin(this.firstName,this.lastName,this.address,this.username,this.clinic)
    .subscribe(
      (user: any) => {
        this.router.navigate(['/']);
      }, (error) => alert(error.text)
    )
  }

}
