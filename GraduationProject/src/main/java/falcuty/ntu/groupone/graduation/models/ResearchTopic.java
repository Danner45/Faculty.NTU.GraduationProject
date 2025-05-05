package falcuty.ntu.groupone.graduation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "research_topic")
public class ResearchTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_research_topic")
    private int idResearchTopic;

    @Column(name = "teacher_created")
    private int teacherCreated;

    @Column(name = "teacher_accepted")
    private Integer teacherAccepted;

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    private ProjectType projectType;

    @Column(name = "id_course")
    private int idCourse;

    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Column(name = "detail", columnDefinition = "TEXT")
    private String detail;

    @Column(name = "max_join")
    private int maxJoin;

    @Column(name = "state")
    private int state;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    public ResearchTopic() {
    }

    public ResearchTopic(int idResearchTopic, int teacherCreated, Integer teacherAccepted,
                         ProjectType projectType, int idCourse, String topic, String description,
                         String detail, int maxJoin, int state, Double grade, String review) {
        this.idResearchTopic = idResearchTopic;
        this.teacherCreated = teacherCreated;
        this.teacherAccepted = teacherAccepted;
        this.projectType = projectType;
        this.idCourse = idCourse;
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

    public int getTeacherCreated() {
        return teacherCreated;
    }

    public void setTeacherCreated(int teacherCreated) {
        this.teacherCreated = teacherCreated;
    }

    public Integer getTeacherAccepted() {
        return teacherAccepted;
    }

    public void setTeacherAccepted(Integer teacherAccepted) {
        this.teacherAccepted = teacherAccepted;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
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