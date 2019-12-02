import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatTableModule } from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'profile', component: PatientProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'pending', component: PendingUsersComponent },
  { path: 'patients', component: PatientsComponent },
  { path: 'changePassword',component : ChangePassword},
  { path: 'vacationrequest',component : PendingVacationsComponent},
  { path: 'medicalrecord',component : MedicalRecordComponent},
  { path: 'dodajKliniku',component : AddClinicComponent},
  { path: 'dodajAdministratoraKlinike',component : RegisterAdminComponent},
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
  ],
  imports: [
    
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MatTableModule,
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
