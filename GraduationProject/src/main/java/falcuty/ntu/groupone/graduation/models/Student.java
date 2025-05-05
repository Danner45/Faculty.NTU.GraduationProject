package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id_student")
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_class", nullable = false)
    private MyClass myClass;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cv", columnDefinition = "LONGTEXT")
    private String cv;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avg_grade", nullable = false)
    private Double avgGrade;

    // Constructors
    public Student() {}

    public Student(String id, MyClass myClass, String name, String cv, String email, String phoneNumber,
			String password, Double avgGrade) {
		super();
		this.id = id;
		this.myClass = myClass;
		this.name = name;
		this.cv = cv;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.avgGrade = avgGrade;
	}

	// Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MyClass getClassEntity() {
        return myClass;
    }

    public void setClassEntity(MyClass myClass) {
        this.myClass = myClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }
}