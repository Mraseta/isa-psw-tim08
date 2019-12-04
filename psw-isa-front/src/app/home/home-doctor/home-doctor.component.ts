import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-home-doctor',
  templateUrl: './home-doctor.component.html',
  styleUrls: ['./home-doctor.component.css']
})
export class HomeDoctorComponent implements OnInit {
  //displayedColumns: string[] = ['ime', 'prezime', 'jmbg'];
  patients = [];
  //dataSource = new MatTableDataSource<Pacijent>(this.patients);

  //@ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private patientService: PatientService) { }

  ngOnInit() {
    this.ucitajPacijente();
  }

  ucitajPacijente() {
    this.patientService.getAllPatients()
      .subscribe(
        (data) => {
          console.log(data);
          this.patients = Object.assign([], (data));
        }
      )
  }

  prikaziPacijente() {

  }

}