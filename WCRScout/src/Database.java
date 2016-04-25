import java.util.ArrayList;

public class Database {
	
	private ArrayList<Team> teams;
	
	public Database(){
		teams = new ArrayList<Team>();
	}
	
	public void add(Team t){
		teams.add(t);
	}
	
	public Team getTeam(int number){
		for(int i = 0; i<teams.size(); i++)
			if(teams.get(i).getNumber() == number)
				return teams.get(i);
		return null;
	}
	
	public Team getTeam(String name){
		for(int i = 0; i<teams.size(); i++)
			if(teams.get(i).getName().equals(name))
				return teams.get(i);
		return null;
	}
	
	public void remove(int number){
		for(int i = 0; i<teams.size(); i++)
			if(teams.get(i).getNumber() == number)
				teams.remove(i);
	}
	
	public void remove(String name){
		for(int i = 0; i<teams.size(); i++)
			if(teams.get(i).getName().equals(name))
				teams.remove(i);
	}
	
	public ArrayList<Team> getArray(){
		return teams;
	}
	
	public boolean isEmpty(){
		return teams.size()==0;
	}
}
