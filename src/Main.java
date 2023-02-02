import java.util.Scanner;

public class Main {
    /**
     * Special escape characters that format printed text.
     */
    public static final String
            RED = "\033[31m",
            EMPTY_SCREEN = "\033[2J\033[H",
            RESET = Game.RESET,
            BOLD = "\033[1m",
            GREEN = "\033[92m",
            ORANGE = "\033[38;2;245;180;40m",
            BLUE = "\033[94m",
            ITALICS = "\033[3m";

    public static void main(String[]args){ runText(); }

    private static void runText() {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();
        System.out.println(EMPTY_SCREEN + "Welcome to Tic Tac Toe!");
        System.out.println("Player 1 is X, Player 2 is O");
        while(game.checkForWin()==0){
            String input = "";
            while(input.equals("")){
                System.out.println("Player " + GREEN + BOLD + (game.getCurrentPlayer()+1) + RESET + "'s turn");
                game.print();
                System.out.print("Enter the coordinates of the square you want to play in (" + RED + BOLD + "x, y" + RESET + "): " + BLUE + ITALICS);
                input = sc.nextLine();
                if(input.equals("quit")) return;
                System.out.print(RESET);
                try{
                    String[] coords = input.split(", | |,");

                    if(coords.length!=2 || !coords[0].matches("\\d+") || !coords[1].matches("\\d+"))
                        throw new Exception("Invalid input! Please enter two numbers.");

                    int x = Integer.parseInt(coords[0]),
                        y = Integer.parseInt(coords[1]);
                    System.out.print(EMPTY_SCREEN);

                    if(!game.play(y-1,x-1))
                        throw new Exception("That square is already taken!");
                } catch (Exception e){
                    System.out.print(EMPTY_SCREEN);
                    System.out.println(RED + e.getMessage() + RESET);
                    input = "";
                }
            }
        }
        if(game.checkForWin()==3){
            System.out.println(ORANGE + "It's a draw!" + RESET);
        } else {
            System.out.println("Player " + GREEN + BOLD + game.checkForWin() + RESET + " won!");
        }
        game.print();
        System.out.print("Would you like to play again? ");
        String end = sc.nextLine();
        if("y".equalsIgnoreCase(end) || "yes".equalsIgnoreCase(end))
            new Thread(Main::runText).start(); // avoid StackOverflowError in case somebody wants to play 10000 games of ttt
        else sc.close();
    }
}