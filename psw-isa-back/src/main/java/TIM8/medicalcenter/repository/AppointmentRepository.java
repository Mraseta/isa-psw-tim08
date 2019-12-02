package TIM8.medicalcenter.repository;

import TIM8.medicalcenter.dto.AppointmentDTO;
import TIM8.medicalcenter.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

    @Query("SELECT a FROM Appointment a where a.date=?1 and a.type=?2")
    List<Appointment> findAppointments(Date date, String type);


}