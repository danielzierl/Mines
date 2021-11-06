package ppa1;

import java.util.Random;
import java.util.Scanner;

public class Minefield {
    public static Scanner sc;
    public boolean[][] minefieldBoolArray;
    int a, b;


    Minefield() {
        inputMinefieldSize();
        minefieldBoolArray = new boolean[a][b];
        inputMinesNumAndPopulate();
    }

    public void inputMinefieldSize() {
        System.out.print("Enter size");
        a = b = sc.nextInt();
    }

    public void drawArray() {
        for (int i = 0; i < minefieldBoolArray.length; i++) {
            for (int j = 0; j < minefieldBoolArray.length; j++) {
                System.out.print(minefieldBoolArray[i][j]);
            }
            System.out.println();
        }
    }

    public void populateWithMinesSpecified(int numOfMines) {
        Random random = new Random();
        for (int k = 0; k < numOfMines; k++) {
            int i = random.nextInt(this.minefieldBoolArray.length);
            int j = random.nextInt(this.minefieldBoolArray.length);
            if (!this.minefieldBoolArray[i][j]) {
                this.minefieldBoolArray[i][j] = true;
            } else {
                k--;
            }

        }
    }

    public void inputMinesNumAndPopulate() {
        System.out.print("Mines: ");
        populateWithMinesSpecified(sc.nextInt());
    }
}
