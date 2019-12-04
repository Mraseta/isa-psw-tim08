package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.AppointmentDTO;
import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.PatientDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Users.Patient;
import TIM8.medicalcenter.service.AppointmentService;
import TIM8.medicalcenter.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ClinicService clinicService;

    @GetMapping(value="/findClinic")
    public ResponseEntity<?> findClinics(@RequestParam String date, @RequestParam String type){
        List<ClinicDTO> clinics = new ArrayList<>();
        if(date.equals("")&&type.equals("")){
            List<Clinic> clinic = clinicService.findAll();
            for (Clinic c:clinic) {
                clinics.add(new ClinicDTO(c));
            }
            return new ResponseEntity<>(clinics, HttpStatus.OK);
        }

        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            return null;
        }
        List<Appointment> appointments = appointmentService.findAppointments(date1,type);
        System.out.println(appointments);
        List<AppointmentDTO> apps = new ArrayList<>();
        for (Appointment a:appointments) {
            apps.add(new AppointmentDTO(a));

        }
        for (AppointmentDTO a:apps) {
            if(clinics.contains(a.getDoctor().getClinic()))
                continue;
            clinics.add(new ClinicDTO(a.getDoctor().getClinic()));
        }

        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }
    @RequestMapping(value="/findClinic/doctors",method = RequestMethod.GET)
    public ResponseEntity<?> findDoctors(@RequestParam String clinicName,@RequestParam String date, @RequestParam String type){
        System.out.println(clinicName);
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        List<Appointment> appointments = appointmentService.findAppointments(date1,type);
        List<AppointmentDTO> apps = new ArrayList<>();
        List<PersonDTO> doctors = new ArrayList<>();
        for (Appointment a:appointments) {
            apps.add(new AppointmentDTO(a));
        }
        for (AppointmentDTO a:apps) {
            System.out.println(a.getDoctor().getClinic().getName());
            if(doctors.contains(a.getDoctor().getClinic()) || !a.getDoctor().getClinic().getName().equals(clinicName))
                continue;
            doctors.add(new PersonDTO(a.getDoctor()));
        }

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
