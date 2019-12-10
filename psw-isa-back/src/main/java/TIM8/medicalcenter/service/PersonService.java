package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.AdministratorDTO;
import TIM8.medicalcenter.model.security.Authority;
import TIM8.medicalcenter.model.users.Administrator;
import TIM8.medicalcenter.model.users.ClinicsAdministrator;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.model.users.Person;
import TIM8.medicalcenter.repository.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Cacheable("persons")
    public List<Person> findAll() { return personRepository.findAll(); }
    @Cacheable("persons")
    public Person findOneByUsername(String username) { return personRepository.findOneByUsername(username); }

    @Cacheable("persons")
    public Person findOneById(Long id){ return personRepository.findOneById(id);}
    @Cacheable("persons")
    public List<Person> findByType(String type) { return personRepository.findByDiscriminatorValue(type);}
    @Cacheable("persons")
    public List<Patient> findPatients() { return personRepository.findPatients();}

    public int updatePersonStatus(String status,Long id) {return personRepository.updateUserStatus(status,id);}
    public int updatePassword(String password,Long id) {return personRepository.updatePassword(password,id);}
    public int updateUser(String firstName,String lastName,String address,long id) { return personRepository.updateUser(firstName,lastName,address,id); }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findOneByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return person;
        }
    }
    public Person save(Person person,String status,String role) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setEnabled(true);

        if(status != null){
            person.setStatus(status);
        }
        List<Authority> auth =authorityService.findByname(role);
        person.setAuthorities(auth);

        return personRepository.save(person);

    }
    public Person saveAdministrator(AdministratorDTO a, String status, String role){
        Administrator admin = new Administrator();
        admin.setFirstName(a.getFirstName());
        admin.setLastName(a.getLastName());
        admin.setUsername(a.getUsername());
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setAddress(a.getAddress());
        admin.setClinic(clinicService.findOneById(a.getClinic_id()));
        admin.setStatus(status);
        List<Authority> auth =authorityService.findByname(role);
        admin.setAuthorities(auth);
        admin.setEnabled(true);

        return personRepository.save(admin);

    }
    public Person saveClinicCentreAdministrator(AdministratorDTO a,String status,String role){
        ClinicsAdministrator admin = new ClinicsAdministrator();
        admin.setFirstName(a.getFirstName());
        admin.setLastName(a.getLastName());
        admin.setUsername(a.getUsername());
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setAddress(a.getAddress());
        admin.setStatus(status);
        List<Authority> auth =authorityService.findByname(role);
        admin.setAuthorities(auth);
        admin.setEnabled(true);

        return personRepository.save(admin);

    }
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        String username = currentUser.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,oldPassword));
        } else {

            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

        LOGGER.debug("Changing password for user '" + username + "'");

        Person user = (Person) loadUserByUsername(username);

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setStatus("ACTIVE");
        personRepository.save(user);

    }



}
