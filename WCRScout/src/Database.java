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
	
	private void save(){
		PrintWriter out = null;
		try{
			out = new PrintWriter(new File(System.getProperty("user.home")+"\\Scouting Data.txt"));
		}catch(Exception e){
			System.out.println("fak");
		}
		for(Team t:teams){
			out.print(t.getName());
			out.print(" ");
			out.print(t.getNumber());
			out.print(" ");
			for(int i = 0; i<Team.DEFENSES_LENGTH; i++){
				out.print(t.getDefense(i));
				out.print(" ");
			}
			for(int i = 0; i<Team.NOTES_LENGTH; i++){
				if(t.getNotes(i).equals(""))
					out.print("!#$%");
				else
					out.print(t.getNotes(i));
				out.print(" ");
			}
			out.println();
		}
		out.close();
	}
	
	public void load(){
		try{
			File f = new File(System.getProperty("user.home")+"\\Scouting Data.txt");
			Scanner in = new Scanner(f);
			while(in.hasNextLine()){
				String name = in.next();
				int number = in.nextInt();
				boolean[] defenses = new boolean[Team.DEFENSES_LENGTH];
				String[] notes = new String[Team.NOTES_LENGTH];
				for(int i = 0; i<Team.DEFENSES_LENGTH; i++){
					defenses[i] = Boolean.parseBoolean(in.next());
				}
				for(int i = 0; i<Team.NOTES_LENGTH; i++){
					String temp = in.next();
					if(temp.equals("!#$%"))
						notes[i] = "";
					else
						notes[i] = temp;
				}
				teams.add(new Team(name, number, defenses, notes));
			}
		}catch(Exception e){}
	}
}
