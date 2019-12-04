insert into clinic(name,address,description) values('clinic1','dr klinike 1','very clininac clinic hoho');

--passwordi su 123
insert into person(type,first_name,last_name,username,password,address,status,jmbg,enabled)
values('P','Pera','Peric','pera.peric@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE','0203998800062',true);

insert into person(type,first_name,last_name,username,password,address,status,worktime_start,worktime_end,clinic_id,enabled)
values('D','Marko','Markovic','marko.markovic@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','PENDING','8am','4pm',1,true);

insert into person(type,first_name,last_name,username,password,address,status,worktime_start,worktime_end,clinic_id,enabled)
values('N','Stefan','Stefanovic','stefan.stefanovic@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE','8am','4pm',1,true);

insert into person(type,first_name,last_name,username,password,address,status,enabled)
values('A','Dragan','Draganovic','dragan.draganovic@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE',true);

insert into clinic_patient(clinic_id,patient_id) values (1,1);

insert into medical_record(height,weight,diopter,alergies,blood_type,patient_id) values (176,77,3.2,'polen mleko','O',1);

insert into vacation(start_date,end_date,status,staff_id) values ('2020-06-01','2020-06-10','PENDING',2);
insert into vacation(start_date,end_date,status,staff_id) values ('2020-07-01','2020-07-10','PENDING',3);

insert into appointment(date,discount,duration,price,status,type,doctor_id,room_id) values ('2020-06-01',0,0,0,'','Hirurg',2,null );
insert into appointment(date,discount,duration,price,status,type,doctor_id,room_id) values ('2020-07-01',0,0,0,'','Hirurg',2,null );
insert into appointment(date,discount,duration,price,status,type,doctor_id,room_id) values ('2020-07-01',0,0,0,'','Pedijatar',2,null );

INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 2);


