package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "majority")
public class Majority {

    @Id
    @Column(name = "id_majority", length = 20)
    private String idMajority;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // Constructors
    public Majority() {}

    public Majority(String idMajority, String name) {
        this.idMajority = idMajority;
        this.name = name;
    }

    // Getters and Setters
    public String getIdMajority() {
        return idMajority;
    }

    public void setIdMajority(String idMajority) {
        this.idMajority = idMajority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
