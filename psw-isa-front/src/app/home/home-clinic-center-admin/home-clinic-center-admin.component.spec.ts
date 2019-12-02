import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeClinicCenterAdminComponent } from './home-clinic-center-admin.component';

describe('HomeClinicCenterAdminComponent', () => {
  let component: HomeClinicCenterAdminComponent;
  let fixture: ComponentFixture<HomeClinicCenterAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeClinicCenterAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeClinicCenterAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
