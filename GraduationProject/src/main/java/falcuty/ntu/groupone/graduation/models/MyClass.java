package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "`class`")
public class MyClass {

    @Id
    @Column(name = "id_class", length = 50, nullable = false)
    private String idClass;

    @Column(name = "id_course", nullable = false)
    private Integer idCourse;

    @Column(name = "id_majority", length = 10, nullable = false)
    private String idMajority;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

	public MyClass() {
	}

	public MyClass(String idClass, Integer idCourse, String idMajority, String name) {
		this.idClass = idClass;
		this.idCourse = idCourse;
		this.idMajority = idMajority;
		this.name = name;
	}


	public String getIdClass() {
		return idClass;
	}

	public void setIdClass(String idClass) {
		this.idClass = idClass;
	}

	public Integer getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

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
