package macro303.keschet;

import macro303.keschet.pieces.*;

public abstract class Console {
	private static ConsoleColour titleColour = ConsoleColour.MAGENTA;
	private static ConsoleColour importantColour = ConsoleColour.YELLOW;
	private static ConsoleColour messageColour = ConsoleColour.WHITE;
	private static ConsoleColour squareColour = ConsoleColour.GREEN;

	private static void colourConsole(String message, ConsoleColour colour) {
		System.out.print(colour.colourCode);
		System.out.print(message);
		System.out.println(ConsoleColour.RESET.colourCode);
	}

	private static void colourConsole(String title, ConsoleColour titleColour, String message, ConsoleColour messageColour) {
		System.out.print(titleColour.colourCode);
		System.out.print(title);
		colourConsole(message, messageColour);
	}

	public static void showTitle(String title) {
		showTeamTitle(title, titleColour);
	}

	public static void showTeamTitle(String title, ConsoleColour teamColour) {
		colourConsole(title, teamColour);
	}

	public static void showMessage(String message) {
		colourConsole(message, messageColour);
	}

	public static void showError(String message) {
		colourConsole(message, importantColour);
	}

	public static void showValue(String title, String message) {
		colourConsole(title, importantColour, message, messageColour);
	}

	public static void showSquare(Piece piece) {
		System.out.print((piece == null ? squareColour : piece.getTeamColour()).colourCode);
		System.out.print(" " + (piece == null ? "~" : piece.getSymbol()) + " ");
		System.out.print(ConsoleColour.RESET.colourCode);
	}

	public static void showRules() {
		showTitle("Rules");
		showMessage("At the start of the game a player may place any piece in any square in the first 3 rows at the player's end of the board.");
		showMessage("A piece is taken if the square it occupies is occupied by an opposing piece.");
		helpMenu("Help <Archer>");
		helpMenu("Help <Emperor>");
		helpMenu("Help <General>");
		helpMenu("Help <Lancer>");
		helpMenu("Help <Merchant>");
		helpMenu("Help <Scholar>");
		helpMenu("Help <Spearman>");
		helpMenu("Help <Thief>");
	}

	public static void helpMenu(String input) {
		if (input.equalsIgnoreCase("Help")) {
			showMessage("'Help Rules' to show the rules");
			showMessage("'Help All' OR 'Help Pieces' to give you the list of all the pieces");
			showMessage("'Help <Symbol>' OR 'Help <Piece>' for information about piece");
			showValue("Example", "Help <A>");
			showValue("Example", "Help <Archer>");
		} else if (input.toLowerCase().contains("all") || input.toLowerCase().contains("pieces")) {
			showValue("All Pieces", "<A>Archer\n<E>Emperor\n<G>General\n<L>Lancer\n<M>Merchant\n<C>Scholar\n<P>Spearman\n<T>Thief");
		} else if (input.toLowerCase().contains("rules")) {
			showRules();
		} else {
			Piece temp = null;
			if (input.toLowerCase().contains("<a>") || input.contains("<archer>"))
				temp = new Archer(null);
			else if (input.toLowerCase().contains("<e>") || input.contains("<emperor>"))
				temp = new Emperor(null);
			else if (input.toLowerCase().contains("<g>") || input.contains("<general>"))
				temp = new General(null);
			else if (input.toLowerCase().contains("<l>") || input.contains("<lancer>"))
				temp = new Lancer(null);
			else if (input.toLowerCase().contains("<m>") || input.contains("<merchant>"))
				temp = new Merchant(null);
			else if (input.toLowerCase().contains("<c>") || input.contains("<scholar>"))
				temp = new Scholar(null);
			else if (input.toLowerCase().contains("<p>") || input.contains("<spearman>"))
				temp = new Spearman(null);
			else if (input.toLowerCase().contains("<t>") || input.contains("<thief>"))
				temp = new Thief(null);
			if (temp != null) {
				showTitle(temp.getClass().getSimpleName() + " Help");
				showValue("Piece", temp.getClass().getSimpleName());
				showValue("Symbol", temp.getSymbol());
				showValue("Max Distance", String.valueOf(temp.getMaxDistance()));
				showValue("Valid Directions", temp.getValidDirections().toString());
				if (temp instanceof Emperor)
					showValue("Ability", "The game is won if the Emperor is taken or if the Emperor is the only piece remaining to the losing player");
				else if (temp instanceof Merchant)
					showValue("Ability [WIP]", "The Merchant can move to any vacant square surrounding the square occupied by the Emperor, if the route is unobstructed by another piece.");
				else if (temp instanceof Scholar)
					showValue("Ability", "Any piece in an surrounding square to the Scholar is protected and cannot be taken.");
				else if (temp instanceof Thief)
					showValue("Ability", "Any piece taken by the Thief is then placed back on the board under the player's control in one of the surrounding squares.");
			}
		}
	}
}
