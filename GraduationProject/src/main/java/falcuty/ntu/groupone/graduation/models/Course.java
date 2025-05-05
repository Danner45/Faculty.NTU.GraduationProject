package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Integer idCourse;

    @Column(name = "entry_year", nullable = false)
    private Integer entryYear;

    @Column(name = "graduation_year", nullable = false)
    private Integer graduationYear;

	public Course() {
	}

	public Course(Integer idCourse, Integer entryYear, Integer graduationYear) {
		this.idCourse = idCourse;
		this.entryYear = entryYear;
		this.graduationYear = graduationYear;
	}

	public Integer getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

	public Integer getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(Integer entryYear) {
		this.entryYear = entryYear;
	}

	public Integer getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(Integer graduationYear) {
		this.graduationYear = graduationYear;
	}
    
    
}
