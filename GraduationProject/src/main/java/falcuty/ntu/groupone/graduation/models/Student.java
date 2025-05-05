package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id_student", length = 10)
    private String idStudent;

    @Column(name = "id_class", length = 50)
    private String idClass;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;  // Có thể null

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;  // Có thể null

    @Column(name = "cv", columnDefinition = "LONGTEXT")
    private String cv;  // Có thể null

    public Student() {
    }

    public Student(String idStudent, String idClass, String name, String email, String phoneNumber, String cv) {
        this.idStudent = idStudent;
        this.idClass = idClass;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cv = cv;
    }

    // Getters and Setters
    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}