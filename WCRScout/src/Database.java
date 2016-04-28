import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	
	private ArrayList<Team> teams;
	
	public Database(){
		teams = new ArrayList<Team>();
	}
	
	public void add(Team t){
		teams.add(t);
		save();
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
		save();
	}
	
	public void remove(String name){
		for(int i = 0; i<teams.size(); i++)
			if(teams.get(i).getName().equals(name))
				teams.remove(i);
		save();
	}
	
	public ArrayList<Team> getArray(){
		return teams;
	}
	
	public boolean isEmpty(){
		return teams.size()==0;
	}
	
	public void numberSort(){
		for(int i = 0; i<teams.size()-1; i++){
			int smallest = i;
			for(int k = i+1; k<teams.size(); k++){
				if(teams.get(k).compareTo(teams.get(smallest))<0)
					smallest = k;
			}
			Team temp = teams.get(smallest);
			teams.set(smallest, teams.get(i));
			teams.set(i, temp);
		}
	}
	
	private void save(){
		PrintWriter out = null;
		try{
			out = new PrintWriter(new File(System.getProperty("user.home")+"\\Scouting Data.txt"));
		}catch(Exception e){
			System.out.println("fak");
		}
		out.println(teams.size());
		out.println();
		for(Team t:teams){
			out.println(t.getName());
			out.println(t.getNumber());
			out.println(t.getColor());
			for(int i = 0; i<Team.DEFENSES_LENGTH; i++){
				out.println(t.getDefense(i));
			}
			for(int i = 0; i<Team.NOTES_LENGTH; i++){
				if(t.getNotes(i).equals(""))
					out.println("!#$%");
				else
					out.println(t.getNotes(i));
			}
			out.println();
		}
		out.close();
	}
	
	public void load(){
		try{
			File f = new File(System.getProperty("user.home")+"\\Scouting Data.txt");
			Scanner in = new Scanner(f);
			int iterations = in.nextInt();
			in.nextLine();
			in.nextLine();
			for(int i = 0; i<iterations; i++){
				String name = in.nextLine();
				int number = in.nextInt();
				in.nextLine();
				String color = in.nextLine();
				boolean[] defenses = new boolean[Team.DEFENSES_LENGTH];
				String[] notes = new String[Team.NOTES_LENGTH];
				for(int j = 0; j<Team.DEFENSES_LENGTH; j++){
					defenses[j] = Boolean.parseBoolean(in.nextLine());
				}
				for(int j = 0; j<Team.NOTES_LENGTH; j++){
					String temp = in.nextLine();
					if(temp.equals("!#$%"))
						notes[j] = "";
					else
						notes[j] = temp;
				}
				teams.add(new Team(name, number, defenses, notes, color));
				in.nextLine();
			}
		}catch(Exception e){}
	}
}
