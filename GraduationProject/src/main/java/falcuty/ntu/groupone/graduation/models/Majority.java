package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "majority")
public class Majority {

    @Id
    @Column(name = "id_majority", length = 10, nullable = false)
    private String idMajority;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    // Constructor mặc định
    public Majority() {
    }

    // Constructor đầy đủ
    public Majority(String idMajority, String name) {
        this.idMajority = idMajority;
        this.name = name;
    }

    // Getters và Setters
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
