package TIM8.medicalcenter.controller;



import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.model.Users.Person;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "api/person",produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonService personService;


    @RequestMapping(consumes = "application/json",value="/getByEmail",method = RequestMethod.POST)
    public ResponseEntity<?> getByEmail(@RequestBody PersonDTO person){
        Person personRet = personService.findOneByUsername(person.getUsername());
        if(personRet==null)
            return null;
        return new ResponseEntity<PersonDTO>(new PersonDTO(personRet),HttpStatus.OK);

    }
    /*@RequestMapping(consumes = "application/json",value = "/login",method = RequestMethod.POST)
    public ResponseEntity<PersonDTO> Login(@RequestBody PersonDTO person) {
        Person person1 = personService.findOneByUsername(person.getUsername());
        if(person1==null)
            return null;
        if(person1.getPassword().equals(person.getPassword())){
            PersonDTO p= new PersonDTO(person1);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
            return  null;
    }*/
    @RequestMapping(consumes = "application/json",value ="/changePassword",method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(@RequestBody PasswordChanger passwordChanger) {
        System.out.println("U change password request: stari:" + passwordChanger.oldPassword + " novi: " +passwordChanger.newPassword);
        personService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    @PostMapping(consumes = "application/json", value = "/update")
    public ResponseEntity<PersonDTO> updateMedicalStaff(@RequestBody PersonDTO person){
        Person person1 = personService.findOneById(person.getId());
        if(person1.getId() != null){
            personService.updateUser(person.getFirstName(),person.getLastName(),person.getAddress(),person.getId());
            person1 = personService.findOneById(person.getId());
            return new ResponseEntity<>(new PersonDTO(person1),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new PersonDTO(),HttpStatus.BAD_REQUEST);
    }
    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }
    /*@PostMapping(consumes = "application/json",value = "/register")
    public ResponseEntity<Patient> Register(@RequestBody Patient patient) {
        if(personService.findOneByUsername(patient.getUsername())!=null)
            return null;

        patient.setStatus("PENDING");
        personService.save(patient);
        return new ResponseEntity<>(patient, HttpStatus.OK);

    }*/


}
