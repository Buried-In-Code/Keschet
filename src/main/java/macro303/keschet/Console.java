package macro303.keschet;

import macro303.keschet.pieces.*;

import java.util.Collections;

public abstract class Console {
	private static Colour titleColour = Colour.MAGENTA;
	private static Colour importantColour = Colour.YELLOW;
	private static Colour messageColour = Colour.WHITE;
	private static Colour squareColour = Colour.GREEN;

	private static void colourConsole(String message, Colour colour) {
		System.out.print(colour.colourCode);
		System.out.print(message);
		System.out.println(Colour.RESET.colourCode);
	}

	private static void colourConsole(String title, Colour titleColour, String message, Colour messageColour) {
		System.out.print(titleColour.colourCode);
		System.out.print(title);
		colourConsole(message, messageColour);
	}

	public static void showTitle(String title) {
		showTeamTitle(title, titleColour);
	}

	public static void showTeamTitle(String title, Colour teamColour) {
		System.out.print(teamColour.colourCode);
		System.out.println(String.join("", Collections.nCopies(title.length() + 4, "=")));
		colourConsole("  " + title + "  ", teamColour);
		System.out.print(teamColour.colourCode);
		System.out.println(String.join("", Collections.nCopies(title.length() + 4, "=")));
		System.out.print(Colour.RESET.colourCode);
	}

	public static void showMessage(String message) {
		colourConsole(message, messageColour);
	}

	public static void showError(String message) {
		colourConsole(message, importantColour);
	}

	public static void showValue(String title, String message) {
		colourConsole(title + ": ", importantColour, message, messageColour);
	}

	public static void showSquare(Piece piece) {
		System.out.print((piece == null ? squareColour : piece.getTeamColour()).colourCode);
		System.out.print(" " + (piece == null ? "~" : piece.getSymbol()) + " ");
		System.out.print(Colour.RESET.colourCode);
	}

	public static void showRules() {
		showTitle("Rules");
		showMessage("At the start of the game a player may place any piece in any square in the first 3 rows at the player's end of the board.");
		showMessage("A piece is taken if the square it occupies is occupied by an opposing piece.");
		helpMenu("Help pieces");
	}

	public static void helpMenu(String input) {
		if (input.equalsIgnoreCase("Help")) {
			showTitle("Help");
			showValue("Help Rules", "Shows the rules.");
			showValue("Help Pieces", "Shows you all the pieces.");
			showValue("Help <Symbol> OR Help Name", "Shows you all the information about the piece with that symbol or name (Symbol must be inside <>).");
		} else if (input.toLowerCase().contains("pieces")) {
			helpMenu("Help <Archer>");
			helpMenu("Help <Emperor>");
			helpMenu("Help <General>");
			helpMenu("Help <Lancer>");
			helpMenu("Help <Merchant>");
			helpMenu("Help <Scholar>");
			helpMenu("Help <Spearman>");
			helpMenu("Help <Thief>");
		} else if (input.toLowerCase().contains("rules")) {
			showRules();
		} else {
			Piece temp = null;
			if (input.toLowerCase().contains("<a>") || input.toLowerCase().contains("archer"))
				temp = new Archer(null);
			else if (input.toLowerCase().contains("<e>") || input.toLowerCase().contains("emperor"))
				temp = new Emperor(null);
			else if (input.toLowerCase().contains("<g>") || input.toLowerCase().contains("general"))
				temp = new General(null);
			else if (input.toLowerCase().contains("<l>") || input.toLowerCase().contains("lancer"))
				temp = new Lancer(null);
			else if (input.toLowerCase().contains("<m>") || input.toLowerCase().contains("merchant"))
				temp = new Merchant(null);
			else if (input.toLowerCase().contains("<c>") || input.toLowerCase().contains("scholar"))
				temp = new Scholar(null);
			else if (input.toLowerCase().contains("<p>") || input.toLowerCase().contains("spearman"))
				temp = new Spearman(null);
			else if (input.toLowerCase().contains("<t>") || input.toLowerCase().contains("thief"))
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

	public enum Colour {
		RESET("\u001B[0m"),
		RED("\u001B[31m"),
		GREEN("\u001B[32m"),
		YELLOW("\u001B[33m"),
		BLUE("\u001B[34m"),
		MAGENTA("\u001B[35m"),
		CYAN("\u001B[36m"),
		WHITE("\u001B[37m");

		String colourCode;

		Colour(String colourCode) {
			this.colourCode = colourCode;
		}
	}
}
