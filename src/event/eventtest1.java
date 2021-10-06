package event;

public class eventtest1 {
	private int id;
	private int people_num;
	private String name, time, content;

	public eventtest1() {
	}

	public eventtest1(int people_num, String name, String time, String content) {
		this.people_num = people_num;
		this.name = name;
		this.time = time;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String gettime() {
		return time;
	}

	public void settime(String time) {
		this.time = time;
	}

	public String getcontent() {
		return content;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	public int getpeople_num() {
		return people_num;
	}

	public void setpeople_num(int people_num) {
		this.people_num = people_num;
	}
}
