package falcuty.ntu.groupone.graduation.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "research_topic")
public class ResearchTopic {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_research_topic")
    private Integer idResearchTopic;

    @ManyToOne
    @JoinColumn(name = "teacher_created", nullable = false)
    private Supervisor teacherCreated;

    @ManyToOne
    @JoinColumn(name = "teacher_accepted", nullable = true)
    private Supervisor teacherAccepted;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private ProjectType projectType;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    @Column(name = "topic", nullable = false)
    private String topic;
    
    @Column(name = "detail", columnDefinition = "LONGTEXT", nullable = false)
    private String detail;

    @Column(name = "max_join", nullable = false)
    private Integer maxJoin;

    @Column(name = "state", nullable = false)
    private Integer state;
    
    @Column(name = "is_research", nullable = false)
    private Boolean isResearch;
    
    @Column(name = "last_modify", nullable = true)
    private Date lastModify;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expire_day", nullable = true)
    private Date expireDay;
    
    @Column(name = "review", nullable = true)
    private String review;
    
    @Column(name = "grade", nullable = true)
    private Double grade;
   
    public ResearchTopic() {
    }

    public ResearchTopic(int idResearchTopic, Supervisor teacherCreated, Supervisor teacherAccepted,
                         ProjectType projectType, Course course, String topic, String description,
                         String detail, int maxJoin, int state, Double grade, String review, Boolean isResearch,
                         Date lastModify, Date expireDay) {
        this.idResearchTopic = idResearchTopic;
        this.teacherCreated = teacherCreated;
        this.teacherAccepted = teacherAccepted;
        this.projectType = projectType;
        this.course = course;
        this.topic = topic;
        this.detail = detail;
        this.maxJoin = maxJoin;
        this.state = state;
        this.isResearch = isResearch;
        this.lastModify = lastModify;
        this.expireDay = expireDay;
    }

    // Getters & Setters

    public int getIdResearchTopic() {
        return idResearchTopic;
    }

    public void setIdResearchTopic(int idResearchTopic) {
        this.idResearchTopic = idResearchTopic;
    }

    public Supervisor getTeacherCreated() {
        return teacherCreated;
    }

    public void setTeacherCreated(Supervisor teacherCreated) {
        this.teacherCreated = teacherCreated;
    }

    public Supervisor getTeacherAccepted() {
        return teacherAccepted;
    }

    public void setTeacherAccepted(Supervisor teacherAccepted) {
        this.teacherAccepted = teacherAccepted;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course idCourse) {
        this.course = idCourse;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getMaxJoin() {
        return maxJoin;
    }

    public void setMaxJoin(int maxJoin) {
        this.maxJoin = maxJoin;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

	public Boolean getIsResearch() {
		return isResearch;
	}

	public void setIsResearch(Boolean isResearch) {
		this.isResearch = isResearch;
	}

	public Date getExpireDay() {
		return expireDay;
	}

	public void setExpireDay(Date expireDay) {
		this.expireDay = expireDay;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}
    
	
}