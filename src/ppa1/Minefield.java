package ppa1;

import java.util.Random;
import java.util.Scanner;

public class Minefield {
    public static Scanner sc;
    public boolean[][] minefieldBoolArray;
    int a, b;
    int minesNum;


    Minefield() {
        inputMinefieldSize();
        minefieldBoolArray = new boolean[a][b];
        inputMinesNum();
    }

    public void inputMinefieldSize() {
        System.out.print("Enter size");
        a = b = sc.nextInt();
    }

    public void drawArray() {
        for (boolean[] booleans : minefieldBoolArray) {
            for (int j = 0; j < minefieldBoolArray.length; j++) {
                System.out.print(booleans[j]);
            }
            System.out.println();
        }
    }

    public void populateWithMinesSpecified(int numOfMines, int xHit, int yHit) {
        Random random = new Random();
        for (int k = 0; k < numOfMines; k++) {
            int i = random.nextInt(this.minefieldBoolArray.length);
            int j = random.nextInt(this.minefieldBoolArray.length);
            if (!this.minefieldBoolArray[i][j]&&(j!=xHit-1||i!=yHit-1)) {
                this.minefieldBoolArray[i][j] = true;
            } else {
                k--;
            }

        }
    }

    public void inputMinesNum() {
        System.out.print("Mines: ");
        minesNum=sc.nextInt();
    }
}
