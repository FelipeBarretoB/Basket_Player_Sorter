package model;

public class Player {

	private String name;
	private String team;
	private int age;
	private int points;
	private int reBounds;
	private int blocks;
	private int assists;
	private int steals;

	public Player(String name, String team, int age, int points, int reBounds, int blocks, int assists, int steals) {
		this.name = name;
		this.team = team;
		this.age = age;
		this.points = points;
		this.reBounds = reBounds;
		this.blocks = blocks;
		this.assists = assists;
		this.steals = steals;
	}

	public String get(String search) {
		if(search.equals("name")) {
			return getName();
		}else if(search.equals("team")) {
			return getTeam();
		}else if(search.equals("age")) {
			return ""+getAge();
		}else if(search.equals("points")) {
			return ""+getPoints();
		}else if(search.equals("reBounds")) {
			return ""+getReBounds();
		}
		else if(search.equals("blocks")) {
			return ""+getBlocks();
		}
		else if(search.equals("assists")) {
			return ""+getAssists();
		}
		else if(search.equals("steals")) {
			return ""+getSteals();
		}else {
			return "";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getReBounds() {
		return reBounds;
	}

	public void setReBounds(int reBounds) {
		this.reBounds = reBounds;
	}

	public int getBlocks() {
		return blocks;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getSteals() {
		return steals;
	}

	public void setSteals(int steals) {
		this.steals = steals;
	}




}
