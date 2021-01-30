/**
 * Class CheckForVictory is used for checking the canvas lines,
 * columns and diagonals for a victory condition - field row.
 */

public class CheckForVictory {

    protected boolean checkWin(int signCode, int[] canvas, int dimension){
        return linesCheck(signCode, canvas, dimension)
                    || columnsCheck(signCode, canvas, dimension)
                    || diagonalsCheck(signCode, canvas, dimension);
    }

    private boolean linesCheck(int signCode, int[] canvas, int dimension){
        int res = 0;
        int j = 0, size = dimension;
        for (int i = 0; i < dimension; i++) {
            for (; j < size; j++) {
                if (canvas[j] == signCode) {
                    res++;
                }
            }
            if (res == dimension) {
                return true;
            }
            size += dimension;
            res = 0;
        }
        return false;
    }

    private boolean columnsCheck(int signCode, int[] canvas, int dimension){
        int res = 0;
        int j = 0;
        for (int i = 0; i < dimension; i++, res = 0, j = i) {
            for (;j < canvas.length; j += dimension){
                if (canvas[j] == signCode) {
                    res++;
                }
            }
            if (res == dimension) {
                return true;
            }
        }
        return false;
    }

    private boolean diagonalsCheck(int signCode, int[] canvas, int dimension){
        int minDifference = dimension - 1;
        int maxDifference = dimension + 1;
        int res = 0;

        /* Checking the left diagonal. */
        for (int i = 0; i < canvas.length; i += maxDifference){
            if (canvas[i] == signCode) {
                res++;
            }
        }
        if (res == dimension) {
            return true;
        }
        res = 0;

        /* Checking the right diagonal */
        for (int i = minDifference; i < canvas.length; i += minDifference){
            if (canvas[i] == signCode) {
                res++;
            }
        }
        if (res == dimension) {
            return true;
        }

        return false;
    }
}
