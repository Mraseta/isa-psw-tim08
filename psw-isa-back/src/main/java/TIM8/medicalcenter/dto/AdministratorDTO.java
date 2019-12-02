package TIM8.medicalcenter.dto;

import TIM8.medicalcenter.model.Users.Administrator;

public class AdministratorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;
    private String status;
    private Long clinic_id;

    public AdministratorDTO() {
    }

    public AdministratorDTO(Long id, String firstName, String lastName, String address, String password, String username, String status, Long clinic_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.username = username;
        this.status = status;
        this.clinic_id = clinic_id;
    }
    public AdministratorDTO(Administrator a){
        this(a.getId(),a.getFirstName(),a.getLastName(),a.getAddress(),a.getPassword(),a.getUsername(),a.getStatus(),a.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(Long clinic_id) {
        this.clinic_id = clinic_id;
    }
}
