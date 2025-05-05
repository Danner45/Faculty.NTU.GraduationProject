package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "enrol")
public class Enrol {

    @Id
    @Column(name = "id_enrol")
    private Integer idEnrol;

    @Column(name = "id_student", length = 10, nullable = false)
    private String idStudent;

    @Column(name = "id_research_topic", nullable = false)
    private Integer idResearchTopic;

    @Column(name = "state_enrol", nullable = false)
    private Integer stateEnrol;

	public Enrol(Integer idEnrol, String idStudent, Integer idResearchTopic, Integer stateEnrol) {
		this.idEnrol = idEnrol;
		this.idStudent = idStudent;
		this.idResearchTopic = idResearchTopic;
		this.stateEnrol = stateEnrol;
	}

	public Enrol() {
	}

	public Integer getIdEnrol() {
		return idEnrol;
	}

	public void setIdEnrol(Integer idEnrol) {
		this.idEnrol = idEnrol;
	}

	public String getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public Integer getIdResearchTopic() {
		return idResearchTopic;
	}

	public void setIdResearchTopic(Integer idResearchTopic) {
		this.idResearchTopic = idResearchTopic;
	}

	public Integer getStateEnrol() {
		return stateEnrol;
	}

	public void setStateEnrol(Integer stateEnrol) {
		this.stateEnrol = stateEnrol;
	}
    
    
}

