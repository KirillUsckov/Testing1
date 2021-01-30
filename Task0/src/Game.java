import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  A tic-tac-toe game.
 *
 *  @verion 1.05
 *  @aurhor Kirill Uskov
 */

public class Game {

    private static int dimension;

    public static void main(String[] args) {
        try {
            try {
                setDimension();
                TicTacToe game = new TicTacToe(dimension);
                game.gameStart();
            }catch( InputMismatchException ex){
                System.out.println("Invalid data type");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void setDimension() throws Exception{
        System.out.print("Filed 3x3 - code 3\nField 4x4 - code 4\nField 5x5 - code 5\n");
        System.out.print("Select field dimension\n\nEnter a code: ");

        Scanner scanner = new Scanner(System.in);
        dimension = scanner.nextInt();
        if(dimension < 3) throw new Exception("The number is less than 3");
        if(dimension > 5) throw new Exception("The number is more than 5");
    }



}
