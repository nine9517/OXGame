import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        OX ox = new OX();
        while (true) {
            int row,col;
            do {
                System.out.print(ox.getTableString());
                do {
                    System.out.print(ox.getCurrentPlayer() + " (col): ");
                    col = kb.nextInt();
                    System.out.print(ox.getCurrentPlayer() + " (row): ");
                    row = kb.nextInt();
                } while (!ox.put(col,row));
                ox.switchPlayer();

            }while (!ox.checkWin(col,row) && ox.checkSpace());
            ox.reset();
            System.out.println("X win "+ox.getCountO());
            System.out.println("O win "+ox.getCountX());
            System.out.println("Draw "+ox.getCountDraw());
        }
    }
}
