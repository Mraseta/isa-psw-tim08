import { Component, OnInit } from '@angular/core';
import { ClinicService } from '../services/clinic/clinic.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-clinic',
  templateUrl: './add-clinic.component.html',
  styleUrls: ['./add-clinic.component.css']
})
export class AddClinicComponent implements OnInit {
  clinicName: string;
  clinicAddress: string;
  clinicDescription: string;

  constructor(
    private clinicService : ClinicService,
    private router : Router
  ) { }

  ngOnInit() {
  }
  addClinic(){
    this.clinicService.addClinic(this.clinicName,this.clinicAddress,this.clinicDescription).subscribe(
      (clinic: any) => {
        this.router.navigate(['/']);
      }, (error) => alert(error.text)
    )

  }

}
