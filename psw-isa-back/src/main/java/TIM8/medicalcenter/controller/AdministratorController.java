package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.AdministratorDTO;
import TIM8.medicalcenter.dto.PersonDTO;
import TIM8.medicalcenter.exception.ResourceConflictException;
import TIM8.medicalcenter.model.Users.Administrator;
import TIM8.medicalcenter.model.Users.Person;
import TIM8.medicalcenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(value = "api/administrator")
public class AdministratorController {

    @Autowired
    PersonService personService;

    @PutMapping(consumes = "application/json",value ="/approveRegistration/{id}")
    public ResponseEntity<PersonDTO> updateStatusApproved(@PathVariable Long id) {

        Person person = personService.findOneById(id);
        if(person != null && person.getDecriminatorValue().equals("P") && person.getStatus().equalsIgnoreCase("PENDING")){
            personService.updatePersonStatus("Active",person.getId());
            //TODO: slanje maila korisniku
            return new ResponseEntity<>(new PersonDTO(person),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PersonDTO(),HttpStatus.NO_CONTENT);
        }
    }



    @PutMapping(consumes = "application/json",value = "/rejectRegistration/{id}")
    public ResponseEntity<PersonDTO> updateStatusRejected(@PathVariable Long id){
        Person person = personService.findOneById(id);
        if(person != null && person.getDecriminatorValue().equals("P") && person.getStatus().equalsIgnoreCase("PENDING")){
            personService.updatePersonStatus("Rejected",person.getId());
            return new ResponseEntity<>(new PersonDTO(person),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PersonDTO(),HttpStatus.NO_CONTENT);
        }

    }
    @RequestMapping(consumes = "application/json",value = "/registerAdmin",method = RequestMethod.POST)
    public ResponseEntity<?> registerAdmin(@RequestBody AdministratorDTO administratorDTO){
        Administrator administrator1 = (Administrator) personService.findOneByUsername(administratorDTO.getUsername());
        if(administrator1 != null){
            throw new ResourceConflictException(administratorDTO.getId(), "Username already exists");
        }
        Administrator person1 = (Administrator) personService.saveAdministrator(administratorDTO,"PENDING","ROLE_ADMIN");
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(person1.getId()).toUri());
        return new ResponseEntity<>(new AdministratorDTO(person1), HttpStatus.CREATED);

    }



}



