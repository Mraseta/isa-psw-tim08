package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.PatientDTO;
import TIM8.medicalcenter.model.Users.Patient;
import TIM8.medicalcenter.model.Users.Person;
import TIM8.medicalcenter.service.PersonService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "api/patient")
public class PatientController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<Person> patientList = personService.findByType("P");
        List<PatientDTO> patients = new ArrayList<>();
        for(Person p : patientList){
            if(p.getStatus().equalsIgnoreCase("PENDING")){
                patients.add(new PatientDTO((Patient) p));
            }
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }
    @GetMapping(value = "/getAllPatients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<Person> patientList = personService.findByType("P");
        List<PatientDTO> patients = new ArrayList<>();
        for(Person p : patientList){
            patients.add(new PatientDTO((Patient) p));
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }


}
