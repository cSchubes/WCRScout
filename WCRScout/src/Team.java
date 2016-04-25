
public class Team {
	
	public static final int LOWBAR_INDEX = 0;
	public static final int PORTCULLIS_INDEX = 1;
	public static final int RAMPARTS_INDEX = 2;
	public static final int CHEVAL_INDEX = 3;
	public static final int MOAT_INDEX = 4;
	public static final int DRAWBRIDGE_INDEX = 5;
	public static final int SALLY_PORT_INDEX = 6;
	public static final int ROCKWALL_INDEX = 7;
	public static final int ROUGH_TERRAIN_INDEX = 8;
	public static final int LOW_GOAL_INDEX = 9;
	public static final int HIGH_GOAL_INDEX = 10;
	public static final int PARK_INDEX = 11;
	public static final int SCALE_INDEX = 12;
	public static final int GENERAL_NOTES_INDEX = 11;
	public static final int SHOT_INDEX = 9;
	public static final int CHALLENGE_INDEX = 10;
	
	private String name;
	private int number;
	private boolean[] defenses;
	private String[] notes;
	
	public Team(String name, int number, boolean[] info, String[] words){
		defenses = new boolean[13];
		for(int i = 0; i<defenses.length; i++){
			defenses[i] = info[i];
		}
		
		notes = new String[12];
		for(int i = 0; i<notes.length; i++){
			notes[i] = words[i];
		}
		this.name = name;
		this.number = number;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumber(){
		return number;
	}
	
	public boolean getDefense(int i){
		return defenses[i];
	}
	
	public String getNotes(int i){
		return notes[i];
	}
}
