package me.soxey6.engine.main;


/**
 * The entry point of the game that then sets up the game object making almost everything non-static.
 * @author Pat Childs || Soxey6
 * @version Dev-0.0.2
 */
public class Main
{
	private static Game game;
	private final static String gameName = "";
	
	private static Repairer repairer;
	public static void main(String[] args)
	{
		if(args.length>0&&args[0].equalsIgnoreCase("repair"))
				repair();
		setGame(new Game(gameName));

	}
	public static Game getGame() {
		return game;
	}
	public static void setGame(Game game) {
		Main.game = game;
	}
	
	private static void repair()
	{
		(repairer = new Repairer()).startRepairs();
		if(repairer.isSuccessful())
		{
			System.out.println("[Repairer] Program was repaired successfully");
			repairer.markSuccessful();
			System.exit(0);
		}else
		{
			System.out.println("[Repairer] Program was not repaired. Please contact the dev for further assistance");
			repairer.dump();
			System.exit(-1);
		}
		
	}
	
}