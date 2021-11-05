package ppa1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Minefield.sc= new Scanner(System.in);
        Minefield minefield = new Minefield();
        minefield.drawArray();
        Game game = new Game(minefield);

    }
}
