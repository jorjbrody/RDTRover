package rdtrover;

import java.util.Scanner;

public class RDTrover {
    private int x, y;
    private char direction;
    private static final char[] COMPASS = {'N', 'E', 'S', 'W'};
    private static final Scanner kb = new Scanner(System.in);

    public RDTrover(int x, int y, char direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public static void roverMoving(int numRovers, int maxX, int maxY) {
        for (int i = 1; i <= numRovers; i++) {
            System.out.println("\nRover " + i + " start position:");
            int x = kb.nextInt(), y = kb.nextInt();
            char dir = kb.next().charAt(0);
            kb.nextLine();

            RDTrover rover = new RDTrover(x, y, dir);

            do {
                System.out.println("Commands for Rover " + i + ":");
                rover.moveRover(kb.nextLine().toUpperCase(), maxX, maxY);
                System.out.println("Final position: " + rover.getPosition());
                System.out.println("Move again? (Y/N)");
            } while (kb.nextLine().equalsIgnoreCase("Y"));
        }
    }

    public void moveRover(String commands, int maxX, int maxY) {
        for (char cmd : commands.toCharArray()) {
            if (cmd == 'L' || cmd == 'R') rotate(cmd);
            else if (cmd == 'M' && !move(maxX, maxY)) {
                System.out.println("Rover out of bounds. Connection lost.");
                return;
            }
            System.out.println("Command: " + cmd + " -> Position: " + x + " " + y + " " + direction);
        }
    }

    private void rotate(char turn) {
        int index = new String(COMPASS).indexOf(direction);
        direction = COMPASS[(turn == 'L' ? index + 3 : index + 1) % 4];
    }

    private boolean move(int maxX, int maxY) {
        int newX = x + (direction == 'E' ? 1 : direction == 'W' ? -1 : 0);
        int newY = y + (direction == 'N' ? 1 : direction == 'S' ? -1 : 0);

        if (newX < 0 || newX > maxX || newY < 0 || newY > maxY) return false;
        x = newX;
        y = newY;
        return true;
    }

    public String getPosition() {
        return x + " " + y + " " + direction;
    }

    public static void main(String[] args) {
        System.out.println("Enter plateau size:");
        int maxX = kb.nextInt(), maxY = kb.nextInt();
        kb.nextLine();

        System.out.println("Number of rovers:");
        roverMoving(kb.nextInt(), maxX, maxY);
        kb.close();
    }
}
