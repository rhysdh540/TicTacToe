public class Game {
    // 0: empty, 1: X, 2: O
    @SuppressWarnings("FieldMayBeFinal")
    private int[][] board = new int[3][3];
    // 0: X: 1: O
    private int currentPlayer = 0;
    public static final String RESET = "\033[0m", UNDERLINE = "\033[4m";

    public Game() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }
    public boolean play(int x, int y) {
        if (board[x][y] == 0) {
            board[x][y] = currentPlayer + 1;
            currentPlayer = (currentPlayer + 1) % 2;
            return true;
        }
        return false;
    }

    /**
     * Checks if the game is won and returns the winner if applicable.
     * @return 0 if no one has won, 1 if X has won, 2 if O has won, 3 if it's a draw
     */
    public int checkForWin() {
        for(int i = 0; i < 3; i++){
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]!=0)
                return board[i][0]; // row check
            if(board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i]!=0)
                return board[0][i]; // column check
        }

        if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!=0)
            return board[0][0]; // top left to bottom right diagonal check
        if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]!=0)
            return board[0][2]; // bottom left to top right diagonal check

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(board[i][j]==0)
                    return 0; // check if any empty squares
        return 3;
    }
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void print(){
        char[][] charBoard = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                charBoard[i][j] = switch(board[i][j]) {
                    case 0 -> ' ';
                    case 1 -> 'X';
                    default -> 'O';
                };
            }
        }
        System.out.println("  1   2   3");
        for(int i = 0; i < 3; i++){
            System.out.println(RESET + (i+1) + " " + (i==2 ? "" : UNDERLINE) + charBoard[i][0] + " | " + charBoard[i][1] + " | " + charBoard[i][2]);
        }
        System.out.print(RESET);
    }
}
