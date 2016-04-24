import javafx.scene.image.Image;

public class Obstacle {
	private Image image;
	private int x, y;
	private String name;
	public static final int LEFT_SIDE = 0;
	public static final int RIGHT_SIDE = 1;
	public static final int LEFT_POS_X = 449;
	public static final int LEFT_POS_TWO_Y = 439-35;
	public static final int LEFT_POS_ONE_Y = 517-35;
	public static final int LEFT_POS_THREE_Y = 362-35;
	public static final int LEFT_POS_FOUR_Y = 284-35;
	public static final int LEFT_POS_FIVE_Y = 206-35;
	public static final int RIGHT_POS_X = 763;
	public static final int RIGHT_POS_ONE_Y = 131-35;
	public static final int RIGHT_POS_TWO_Y = 203-35;
	public static final int RIGHT_POS_THREE_Y = 281-35;
	public static final int RIGHT_POS_FOUR_Y = 359-35;
	public static final int RIGHT_POS_FIVE_Y = 436-35;
	final Image lowBar = new Image(getClass().getResourceAsStream("LowBarImage.png"), 0, 74, true, true);
	final Image portCullis = new Image(getClass().getResourceAsStream("PortCullisImage.png"), 0, 78, true, true);
	final Image ramparts = new Image(getClass().getResourceAsStream("RampartsImage.png"), 67, 0, true, true);
	final Image cheval = new Image(getClass().getResourceAsStream("ChevalImage.png"), 67, 0, true, true);
	final Image moat = new Image(getClass().getResourceAsStream("MoatImage.png"), 67, 0, true, true);
	final Image drawbridge = new Image(getClass().getResourceAsStream("DrawbridgeImage.png"), 0, 73, true, true);
	final Image sallyPort = new Image(getClass().getResourceAsStream("SallyPortImage.png"), 67, 0, true, true);
	final Image rockWall = new Image(getClass().getResourceAsStream("RockWallImage.png"), 67, 0, true, true);
	final Image roughTerrain = new Image(getClass().getResourceAsStream("RoughTerrainImage.png"), 67, 0, true, true);
	
	public Obstacle(String name, int position, int side){
		this.name = name;
		switch(name){
		case "Low Bar":
			image = lowBar;
			break;
		case "Portcullis":
			image = portCullis;
			break;
		case "Ramparts":
			image = ramparts;
			break;
		case "Cheval de Frise":
			image = cheval;
			break;
		case "Moat":
			image = moat;
			break;
		case "Drawbridge":
			image = drawbridge;
			break;
		case "Sally Port":
			image = sallyPort;
			break;
		case "Rock Wall":
			image = rockWall;
			break;
		case "Rough Terrain":
			image = roughTerrain;
			break;
		}
		if(side!=0){
			switch(position){
			case 1:
				y = RIGHT_POS_ONE_Y;
				break;
			case 2:
				y = RIGHT_POS_TWO_Y;
				break;
			case 3:
				y = RIGHT_POS_THREE_Y;
				break;
			case 4:
				y = RIGHT_POS_FOUR_Y;
				break;
			case 5:
				y = RIGHT_POS_FIVE_Y;
				break;
			}
			x = RIGHT_POS_X;
		}
		else{
			switch(position){
			case 1:
				y = LEFT_POS_ONE_Y;
				break;
			case 2:
				y = LEFT_POS_TWO_Y;
				break;
			case 3:
				y = LEFT_POS_THREE_Y;
				break;
			case 4:
				y = LEFT_POS_FOUR_Y;
				break;
			case 5:
				y = LEFT_POS_FIVE_Y;
			}
			x = LEFT_POS_X;
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImage(){
		return image;
	}
	
	public String getName(){
		return name;
	}
}
