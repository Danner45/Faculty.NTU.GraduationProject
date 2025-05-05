package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    private Integer idDepartment;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // Constructors
    public Department() {}

    public Department(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}