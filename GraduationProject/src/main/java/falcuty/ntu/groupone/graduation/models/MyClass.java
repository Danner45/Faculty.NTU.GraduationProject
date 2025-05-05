package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "class")
public class MyClass {

    @Id
    @Column(name = "id_class", length = 255)
    private String idClass;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "id_majority", nullable = false)
    private Majority majority;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // Constructors
    public MyClass() {}

    public MyClass(String idClass, Course course, Majority majority, String name) {
        this.idClass = idClass;
        this.course = course;
        this.majority = majority;
        this.name = name;
    }

    // Getters and Setters
    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Majority getMajority() {
        return majority;
    }

    public void setMajority(Majority majority) {
        this.majority = majority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}