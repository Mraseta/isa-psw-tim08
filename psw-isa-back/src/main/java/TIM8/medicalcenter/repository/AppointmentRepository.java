package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.dto.AppointmentDTO;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.users.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

    @Query("SELECT a FROM Appointment a where a.type=?1")
    List<Appointment> findAppointments( String type);

    Appointment findOneById(Long id);
    List<Appointment> findAll();
    List<Appointment> findAllByDoctor(Doctor d);

}
