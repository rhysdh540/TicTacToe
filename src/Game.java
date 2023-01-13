public class Game {
    // 0: empty, 1: X, 2: O
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
            board[x][y] = currentPlayer+1;
            currentPlayer = (currentPlayer + 1) % 2;
            return true;
        }
        return false;
    }
    public int isWon() {
        if(board[0][0]==board[0][1] && board[0][1]==board[0][2] && board[0][0]!=0){
            return board[0][0];
        }
        if(board[1][0]==board[1][1] && board[1][1]==board[1][2] && board[1][0]!=0){
            return board[1][0];
        }
        if(board[2][0]==board[2][1] && board[2][1]==board[2][2] && board[2][0]!=0){
            return board[2][0];
        }
        if(board[0][0]==board[1][0] && board[1][0]==board[2][0] && board[0][0]!=0){
            return board[0][0];
        }
        if(board[0][1]==board[1][1] && board[1][1]==board[2][1] && board[0][1]!=0){
            return board[0][1];
        }
        if(board[0][2]==board[1][2] && board[1][2]==board[2][2] && board[0][2]!=0){
            return board[0][2];
        }
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!=0){
            return board[0][0];
        }
        if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]!=0){
            return board[0][2];
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j]==0)
                    return 0;
            }
        }
        return 3;
    }
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void print(){
        char[][] board = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(this.board[i][j]==0){
                    board[i][j] = ' ';
                } else if(this.board[i][j]==1){
                    board[i][j] = 'X';
                } else {
                    board[i][j] = 'O';
                }
            }
        }
        System.out.println("  1   2   3");
        for(int i = 0; i < 3; i++){
            System.out.println(RESET + (i+1) + " " + (i==2 ? "" : UNDERLINE) + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
        }
        System.out.print(RESET);
    }
}
