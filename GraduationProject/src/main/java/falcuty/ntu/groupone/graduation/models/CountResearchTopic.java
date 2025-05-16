package falcuty.ntu.groupone.graduation.models;

public class CountResearchTopic {
	private ResearchTopic project;
	private int count;
	public CountResearchTopic(ResearchTopic project, int count) {
		this.project = project;
		this.count = count;
	}
	public ResearchTopic getProject() {
		return project;
	}
	public void setproject(ResearchTopic project) {
		this.project = project;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
