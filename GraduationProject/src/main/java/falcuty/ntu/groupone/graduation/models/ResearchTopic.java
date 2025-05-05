package falcuty.ntu.groupone.graduation.models;

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
    @JoinColumn(name = "teacher_accepted", nullable = false)
    private Supervisor teacherAccepted;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private ProjectType projectType;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "detail", columnDefinition = "LONGTEXT", nullable = false)
    private String detail;

    @Column(name = "max_join", nullable = false)
    private Integer maxJoin;

    @Column(name = "state", nullable = false)
    private Integer state;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "review")
    private String review;
   
    public ResearchTopic() {
    }

    public ResearchTopic(int idResearchTopic, Supervisor teacherCreated, Supervisor teacherAccepted,
                         ProjectType projectType, Course course, String topic, String description,
                         String detail, int maxJoin, int state, Double grade, String review) {
        this.idResearchTopic = idResearchTopic;
        this.teacherCreated = teacherCreated;
        this.teacherAccepted = teacherAccepted;
        this.projectType = projectType;
        this.course = course;
        this.topic = topic;
        this.description = description;
        this.detail = detail;
        this.maxJoin = maxJoin;
        this.state = state;
        this.grade = grade;
        this.review = review;
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

    public Course getIdCourse() {
        return course;
    }

    public void setIdCourse(Course idCourse) {
        this.course = idCourse;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}