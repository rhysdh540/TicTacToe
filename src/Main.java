public class Main {
    public static final String RED = "\033[31m",
            EMPTY_SCREEN = "\033[2J\033[H",
            RESET = Game.RESET,
            BOLD = "\033[1m",
            GREEN = "\033[92m",
            ORANGE = "\033[38;2;245;180;40m";

    public static void main(String[]a){run();}
    public static void run() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        java.io.PrintStream so = System.out;
        Game g = new Game();
        so.println(EMPTY_SCREEN + "Welcome to Tic Tac Toe!");
        so.println("Player 1 is X, Player 2 is O");
        while(g.isWon()==0){
            String input = "";
            while(input.equals("") && g.isWon() == 0){
                so.println("Player " + GREEN + BOLD + (g.getCurrentPlayer()+1) + RESET + "'s turn");
                g.print();
                so.print("Enter the coordinates of the square you want to play in (ex: " + RED + BOLD + "\"1, 1\"" + RESET + "): " + RED + BOLD);
                input = sc.nextLine();
                so.print(RESET);
                try{
                    String[] coords;
                    String[] in1 = input.split(", ");
                    String[] in2 = input.split(" ");
                    String[] in3 = input.split(",");
                    if(in1.length==2)
                        coords = in1;
                    else if(in2.length==2)
                        coords = in2;
                    else if(in3.length==2)
                        coords = in3;
                    else throw new Exception();
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    if(!g.play(y-1,x-1)){
                        so.print(EMPTY_SCREEN);
                        so.println(RED + "That square is already taken!" + RESET);
                        input = "";
                    } else so.println(EMPTY_SCREEN);
                } catch (Exception e){
                    so.print(EMPTY_SCREEN);
                    so.println(RED + "Invalid input!" + RESET);
                    input = "";
                }
            }
        }
        if(g.isWon()==3){
            so.println(ORANGE + "It's a draw!" + RESET);
        } else {
            so.println("Player " + GREEN + BOLD + g.isWon() + RESET + " won!");
        }
        g.print();
        so.print("Would you like to play again? ");
        String end = sc.nextLine();
        if("y".equalsIgnoreCase(end) || "yes".equalsIgnoreCase(end))
            new Thread(Main::run).start(); // avoid StackOverflowError in case somebody wants to play 10000 games of ttt
        else sc.close();
    }
}