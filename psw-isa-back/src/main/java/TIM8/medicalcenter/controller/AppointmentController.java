package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.AppointmentDTO;
import TIM8.medicalcenter.dto.ClinicDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(consumes = "application/json",value="/findClinic",method = RequestMethod.GET)
    public ResponseEntity<?> findPatient(@RequestParam String date, @RequestParam String type){
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
        List<ClinicDTO> clinics = new ArrayList<>();
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
    @RequestMapping(consumes = "application/json",value="/findDoctors",method = RequestMethod.GET)
    public ResponseEntity<?> findDoctors(@RequestParam String clinic,@RequestParam String date, @RequestParam String type){

        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1;
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        List<Appointment> appointments = appointmentService.findAppointments(date1,type);

        List<AppointmentDTO> apps = new ArrayList<>();
        List<PersonDTO> doctors = new ArrayList<>();
        for (Appointment a:appointments) {
            apps.add(new AppointmentDTO(a));
        }
        for (AppointmentDTO a:apps) {
            System.out.println(a.getDoctor().getFirstName());
            if(doctors.contains(a.getDoctor().getClinic()))
                continue;
            if(!a.getDoctor().getClinic().getName().equals(clinic))
                continue;
            doctors.add(new PersonDTO(a.getDoctor()));
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
