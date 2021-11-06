package ppa1;

import java.util.Objects;
import java.util.Scanner;

public class Game {
    public static Scanner sc;
    String[][] playBoard;
    boolean running;
    Minefield minefield;
    int hitX, hitY;
    int iterCount;
    boolean[][] zeroesArray;

    Game(Minefield minefield) {
        zeroesArray = new boolean[minefield.a][minefield.b];
        iterCount = 0;
        running = true;
        this.minefield = minefield;
        sc = Minefield.sc;

        createAndFillPlayBoard();
        drawPlayBoard();
        playTurn();
    }

    public void playTurn() {
        if (running&&iterCount==0){
            iterCount++;
            inputHitCoords();
            minefield.populateWithMinesSpecified(minefield.minesNum, hitX, hitY);
            changePlayBoardOnHit();
            System.out.flush();
            drawPlayBoard();
            playTurn();
        }
        if (running){
            iterCount++;
            inputHitCoords();
            changePlayBoardOnHit();
            System.out.flush();
            drawPlayBoard();
            playTurn();
        }


    }

    public void inputHitCoords() {
        //iterCount = 0;
        System.out.print("HitX: ");
        hitX = sc.nextInt();
        System.out.print("HitY");
        hitY = sc.nextInt();
        //hitY=(minefield.minefieldBoolArray.length+1-hitY);
    }

    public String[][] fillPlayBoard(String[][] playBoard) {
        for (int i = 0; i < playBoard.length; i++) {
            for (int j = 0; j < playBoard.length; j++) {
                playBoard[i][j] = " * ";
            }
        }
        return playBoard;
    }

    /*public Boolean[][] fillPlayBoard(Boolean[][] playBoard) {
        for (int i = 0; i < playBoard.length; i++) {
            for (int j = 0; j < playBoard.length; j++) {
                playBoard[i][j] = false;
            }
        }
        return playBoard;
    }*/

    public void createAndFillPlayBoard() {
        playBoard = new String[minefield.a][minefield.b];
        playBoard = fillPlayBoard(playBoard);
    }

    public void drawPlayBoard() {
        for (String[] row : playBoard) {
            for (String tile : row) {
                System.out.print(tile);
            }
            System.out.println();
        }
    }

    public int numberOfNearbyMinesOnTile(int x, int y) {
        int count = 0;
        if (this.minefield.minefieldBoolArray[x][y]) {
            count = 999;
        }

        if (x != 0 && y != 0) {
            if (this.minefield.minefieldBoolArray[x - 1][y - 1])
                count++;
        }
        if (y != 0) {
            if (this.minefield.minefieldBoolArray[x][y - 1])
                count++;
        }
        if (x != minefield.minefieldBoolArray.length - 1 && y != 0) {
            if (this.minefield.minefieldBoolArray[x + 1][y - 1])
                count++;
        }
        if (x != 0) {
            if (this.minefield.minefieldBoolArray[x - 1][y])
                count++;
        }
        if (x != minefield.minefieldBoolArray.length - 1) {
            if (this.minefield.minefieldBoolArray[x + 1][y])
                count++;
        }
        if (x != 0 && y != minefield.minefieldBoolArray.length - 1) {
            if (this.minefield.minefieldBoolArray[x - 1][y + 1])
                count++;
        }
        if (y != minefield.minefieldBoolArray.length - 1) {
            if (this.minefield.minefieldBoolArray[x][y + 1])
                count++;
        }
        if (x != minefield.minefieldBoolArray.length - 1 && y != minefield.minefieldBoolArray.length - 1) {
            if (this.minefield.minefieldBoolArray[x + 1][y + 1])
                count++;
        }

        return count;
    }

    public void changePlayBoardOnHit() {

        int minesNear = numberOfNearbyMinesOnTile(hitX - 1, hitY - 1);
        if ((minesNear < 500)) {
            playBoard[hitY - 1][hitX - 1] = " " + minesNear + " ";
        }
        if (minesNear == 0) {
            lookThroughTheSurroundingTilesAndShowThem(hitX - 1, hitY - 1);
        }

        if (minesNear > 500) {
            showTheWholeGame();
            running=false;
        }
        for (int i = 0; i < playBoard.length; i++) {
            lookThroughGameArrayForZeroAndShowSurroundings();

        }

    }

    public void showTheWholeGame() {
        for (int i = 0; i < playBoard.length; i++) {
            for (int j = 0; j < playBoard.length; j++) {
                int minesNear = numberOfNearbyMinesOnTile(i, j);
                if (!(minesNear > 500)) {
                    playBoard[i][j] = Integer.toString(minesNear);
                } else {
                    playBoard[i][j] = "x";
                }
            }
        }
        drawPlayBoard();
    }

    public void lookThroughTheSurroundingTilesAndShowThem(int x, int y) {
        for (int xChange = -1; xChange < 2; xChange++) {
            for (int yChange = -1; yChange < 2; yChange++) {
                if (xChange != 0 || yChange != 0) {
                    if (x + xChange >= 0 && y + yChange >= 0 && x + xChange < playBoard.length && y + yChange < playBoard.length) {
                        int minesNearNearby = numberOfNearbyMinesOnTile(x + xChange, y + yChange);
                        playBoard[y + yChange][x + xChange] = " " + minesNearNearby + " ";
                    }

                }
            }
        }
    }

    public void lookThroughGameArrayForZeroAndShowSurroundings() {
        for (int i = 0; i < playBoard.length; i++) {
            for (int j = 0; j < playBoard.length; j++) {
                if (Objects.equals(playBoard[j][i], " 0 ")) {
                    lookThroughTheSurroundingTilesAndShowThem(i, j);
                }
            }
        }
    }

}