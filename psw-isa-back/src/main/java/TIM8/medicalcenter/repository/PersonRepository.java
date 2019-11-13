package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findAll();

    Person findOneById(Long id);


    List<Person> findByFirstName(String firstName);

    @Query("select s from Person s where s.email = ?1")
    Person findOneByEmail(String mail);

    @Query("SELECT p FROM Person p where p.class=?1")
    public List<Person> findByDiscriminatorValue(String discriminatorValue);
}
