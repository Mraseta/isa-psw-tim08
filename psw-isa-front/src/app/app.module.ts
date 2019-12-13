import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatTableModule } from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { UserService } from './services/user/user.service';
import { HttpModule } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { PendingUsersComponent } from './pending-users/pending-users.component';
import { HomeComponent } from './home/home.component';
import { HomeNurseComponent } from './home/home-nurse/home-nurse.component';
import { HeaderComponent } from './header/header.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HomePatientComponent } from './home/home-patient/home-patient.component';
import { HomeDoctorComponent } from './home/home-doctor/home-doctor.component';
import { HomeAdminComponent } from './home/home-admin/home-admin.component';
import { PatientsComponent } from './patients/patients.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ChangePassword } from './change-password/change-password.component';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptor} from '../Interceptors/TokenInterceptor';
import { PendingVacationsComponent } from './pending-vacations/pending-vacations.component';
import { MedicalRecordComponent } from './medical-record/medical-record.component';
import { PatientProfileComponent } from './patient-profile/patient-profile.component';
import { HomeClinicCenterAdminComponent } from './home/home-clinic-center-admin/home-clinic-center-admin.component';
import { AddClinicComponent } from './add-clinic/add-clinic.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { RegisterClinicCentreAdminComponent } from './register-clinic-centre-admin/register-clinic-centre-admin.component';
import { CodebookComponent } from './codebook/codebook.component';
import { SearchPatientsComponent } from './search-patients/search-patients.component';
import { SearchClinicsComponent } from './search-clinics/search-clinics.component';
import { MedicalExaminationComponent } from './medical-examination/medical-examination.component';
import { AppointmentReservationComponent } from './appointment-reservation/appointment-reservation.component';
import { EditClinicComponent } from './edit-clinic/edit-clinic.component';
import { ManageAppointTypesComponent } from './manage-appoint-types/manage-appoint-types.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'profile', component: PatientProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'pending', component: PendingUsersComponent },
  { path: 'patients', component: PatientsComponent },
  { path: 'changePassword', component: ChangePassword},
  { path: 'editclinic', component: EditClinicComponent},
  { path: 'managetypes', component: ManageAppointTypesComponent},
  { path: 'vacationrequest', component: PendingVacationsComponent},
  { path: 'medicalrecord/:id', component: MedicalRecordComponent},
  { path: 'medicalExamination/:id',component : MedicalExaminationComponent},
  { path: 'appointmentReservation', component: AppointmentReservationComponent},
  { path: 'searchpatients', component: SearchPatientsComponent},
  { path: 'searchclinics', component: SearchClinicsComponent},
  { path: 'dodajKliniku', component: AddClinicComponent},
  { path: 'dodajAdministratoraKlinike', component: RegisterAdminComponent},
  { path: 'dodajAdministratoraKlinickogCentra', component: RegisterClinicCentreAdminComponent},
  { path: 'dodavanjeUSifarnik', component: CodebookComponent},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    LoginComponent,
    RegisterComponent,
    PendingUsersComponent,
    HomeComponent,
    HomeNurseComponent,
    HomeDoctorComponent,
    HeaderComponent,
    WelcomeComponent,
    HomePatientComponent,
    HomeAdminComponent,
    PatientsComponent,
    PageNotFoundComponent,
    ChangePassword,
    PendingVacationsComponent,
    MedicalRecordComponent,
    PatientProfileComponent,
    HomeClinicCenterAdminComponent,
    AddClinicComponent,
    RegisterAdminComponent,
    RegisterClinicCentreAdminComponent,
    CodebookComponent,
    SearchPatientsComponent,
    SearchClinicsComponent,
    MedicalExaminationComponent,
    AppointmentReservationComponent,
    EditClinicComponent,
    ManageAppointTypesComponent
  ],
  imports: [ 
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MatTableModule,
    NgbModule,
    RouterModule.forRoot(appRoutes)
  ],

  providers: [
    {
    provide : HTTP_INTERCEPTORS,
    useClass : TokenInterceptor,
    multi :true
  },
  CookieService,],
  bootstrap: [AppComponent]
})
export class AppModule { }
