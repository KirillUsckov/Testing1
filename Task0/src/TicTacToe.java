import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class TicTacToe is used for control the game logic and to draw the interface.
 */

public class TicTacToe {

    private final static char X = 'x'; //code 1
    private final static char O = 'o'; //code 2
    private final static char SPACE = '-'; //code 0
    private final static String DIVIDE = "\t|\t";
    private int dimension;
    private int[] canvas;
    private Random random;
    private Boolean isGameRun = true;
    private Scanner scanner;
    private CheckForVictory check;

    protected TicTacToe(int size) {
        canvas = new int[(int) Math.pow(size, 2)];
        for (int i = 0; i < canvas.length; i++) {
            canvas[i] = 0;
        }

        dimension = size;
        random = new Random();
        check = new CheckForVictory();
    }

    void gameStart() {
        drawCanvasPosition();
        drawCanvas();

        while (isGameRun) {
            gamerTurn();
            if (check.checkWin(1, canvas, dimension)) {
                System.out.println("\nGamer WIN!");
                break;
            }
            if (isTableFull()) {
                System.out.println("\nTABLE IS FULL!");
                break;
            }

            consoleTurn();
            drawCanvasPosition();
            drawCanvas();

            if (check.checkWin(2, canvas, dimension)) {
                System.out.println("\nComputer WIN!");
                break;
            }
            if (isTableFull()) {
                System.out.println("\nTABLE IS FULL");
                break;
            }
        }
        drawCanvas();
    }

    void drawCanvasPosition() {
        System.out.println("\nPosition codes:\n");
        for (int i = 0; i < canvas.length; i++) {
            System.out.print(i + 1);
            if ( (i + 1) % dimension == 0) {
                System.out.println();
            } else{
                System.out.print(DIVIDE);
            }
        }
    }

    void drawCanvas() {
        System.out.println();
        for (int i = 0; i < canvas.length; i++) {
            if (canvas[i] == 1){
                System.out.print(X);
            } else if (canvas[i] == 2) {
                System.out.print(O);
            } else {
                System.out.print(SPACE);
            }

            if ( (i + 1) % dimension == 0) {
                System.out.println();
            } else {
                System.out.print(DIVIDE);
            }
        }
    }


    void gamerTurn() {
        boolean error = false;
        int positionOfSigh = 0;
        do {

            try {
                scanner = new Scanner(System.in);
                System.out.print("\nEnter a code of position: ");
                positionOfSigh = scanner.nextInt() - 1;
            } catch (InputMismatchException e) {
                error = true;
                System.out.println("\nInvalid data type!");
            }

        }while (!isSignValid(positionOfSigh));

        if (error) {
            scanner = new Scanner(System.in);
            System.out.print("\nEnter a code of position: ");
            positionOfSigh = scanner.nextInt() - 1;
        }
        canvas[positionOfSigh] = 1;
    }

    void consoleTurn() {
        int position;
        do {
            position = random.nextInt(canvas.length - 1);
        } while (!isSignValid(position));
        canvas[position] = 2;
    }

    boolean isSignValid(int position) {
        if (position > canvas.length || position < 0) {
            return false;
        }
        return canvas[position] == 0;
    }

    boolean isTableFull() {
        for (int i = 0; i < canvas.length; i++) {
            if (canvas[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
