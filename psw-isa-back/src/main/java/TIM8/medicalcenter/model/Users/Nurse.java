package TIM8.medicalcenter.model.Users;

import TIM8.medicalcenter.model.Clinic;
import TIM8.medicalcenter.model.Users.Person;

import javax.persistence.*;

@Entity
@DiscriminatorValue("N")
public class Nurse extends Person {
    @Column(name = "worktime_start")
    private String worktimeStart;

    @Column(name="worktime_end")
    private String worktimeEnd;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinic clinic;

    public String getWorktimeStart() {
        return worktimeStart;
    }

    public void setWorktimeStart(String worktimeStart) {
        this.worktimeStart = worktimeStart;
    }

    public String getWorktimeEnd() {
        return worktimeEnd;
    }

    public void setWorktimeEnd(String worktimeEnd) {
        this.worktimeEnd = worktimeEnd;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }



}
