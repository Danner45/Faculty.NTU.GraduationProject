package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "supervisor")
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supervisor")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "cv", columnDefinition = "LONGTEXT")
    private String cv;

    @Column(name = "max_research", nullable = false)
    private Integer maxResearch;

    @Column(name = "max_topic", nullable = false)
    private Integer maxTopic;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    // Constructors
    public Supervisor() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Integer getMaxResearch() {
        return maxResearch;
    }

    public void setMaxResearch(Integer maxResearch) {
        this.maxResearch = maxResearch;
    }

    public Integer getMaxTopic() {
        return maxTopic;
    }

    public void setMaxTopic(Integer maxTopic) {
        this.maxTopic = maxTopic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

