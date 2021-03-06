package TIM8.medicalcenter.model;

import TIM8.medicalcenter.model.users.Doctor;
import TIM8.medicalcenter.model.users.Patient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Appointment {

    //region column definitions
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "date",nullable = false)
    private Date date;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "status")
    private String status;

    @Version
    @Column(name="version",columnDefinition = "integer DEFAULT 0",nullable = false)
    private int version;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Room room;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Doctor doctor;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    //endregion

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Appointment a = (Appointment) o;
        if (a.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, a.id);
    }
}
