package falcuty.ntu.groupone.graduation.models;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "enrol")
public class Enrol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enrol")
    private Integer idEnrol;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_research_topic", nullable = false)
    private ResearchTopic researchTopic;

    @Column(name = "state_enrol", nullable = false)
    private Integer stateEnrol;
    
    @Column(name = "report", nullable = true)
    private String report;

    @Column(name = "date_modified", nullable = true)
    private Timestamp dateModified;
    
    // Constructors
    public Enrol() {}

    public Enrol(Student student, ResearchTopic researchTopic, Integer stateEnrol) {
        this.student = student;
        this.researchTopic = researchTopic;
        this.stateEnrol = stateEnrol;
    }

    // Getters and Setters
    public Integer getIdEnrol() {
        return idEnrol;
    }

    public void setIdEnrol(Integer idEnrol) {
        this.idEnrol = idEnrol;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ResearchTopic getResearchTopic() {
        return researchTopic;
    }

    public void setResearchTopic(ResearchTopic researchTopic) {
        this.researchTopic = researchTopic;
    }

    public Integer getStateEnrol() {
        return stateEnrol;
    }

    public void setStateEnrol(Integer stateEnrol) {
        this.stateEnrol = stateEnrol;
    }

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
	
	public Timestamp getDateModified() {
		return dateModified;
	}

	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}

    @PreUpdate
    protected void onUpdate() {
    	dateModified = Timestamp.valueOf(LocalDateTime.now());
    }
    
}
