package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "supervisor")
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supervisor")
    private int idSupervisor;

    @Column(name = "id_department")
    private int idDepartment;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private int position;

    @Column(name = "cv", columnDefinition = "LONGTEXT")
    private String cv;

    @Column(name = "max_research")
    private int maxResearch;

    @Column(name = "max_topic")
    private int maxTopic;

    public Supervisor() {
    }

    public Supervisor(int idSupervisor, int idDepartment, String name, int position,
                      String cv, int maxResearch, int maxTopic) {
        this.idSupervisor = idSupervisor;
        this.idDepartment = idDepartment;
        this.name = name;
        this.position = position;
        this.cv = cv;
        this.maxResearch = maxResearch;
        this.maxTopic = maxTopic;
    }

    // Getters and Setters

    public int getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public int getMaxResearch() {
        return maxResearch;
    }

    public void setMaxResearch(int maxResearch) {
        this.maxResearch = maxResearch;
    }

    public int getMaxTopic() {
        return maxTopic;
    }

    public void setMaxTopic(int maxTopic) {
        this.maxTopic = maxTopic;
    }
}

