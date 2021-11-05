package ppa1;

import java.util.Scanner;

public class Game {
    String[][] playBoard;
    boolean running;
    Minefield minefield;
    public static Scanner sc;
    int hitX, hitY;
    
    Game(Minefield minefield){
        running=true;
        this.minefield = minefield;
        sc=minefield.sc;
        createAndFillPlayBoard();
        drawPlayBoard();
        playTurn();
    }
    public void playTurn(){
        inputHitCoords();
        changePlayBoardOnHit();
        System.out.flush();
        drawPlayBoard();
        playTurn();
        
    }
    public void inputHitCoords(){
        System.out.print("HitX: ");
        hitX=sc.nextInt();
        System.out.print("HitY");
        hitY=sc.nextInt();
        hitY=(minefield.minefieldBoolArray.length+1-hitY);
    }
    public String[][] fillPlayBoard(String[][] playBoard){
        for (int i = 0; i < playBoard.length; i++) {
            for (int j = 0; j < playBoard.length; j++) {
                playBoard[i][j]="* ";
            }
        }
        return  playBoard;
    }
    public void createAndFillPlayBoard(){
        playBoard = new String[minefield.a][minefield.b];
        playBoard= fillPlayBoard(playBoard);
    }
    public void drawPlayBoard(){
        for (String[] row: playBoard) {
            for(String tile: row){
                System.out.print(tile);
            }
            System.out.println();
        }
    }
    public int numberOfNearbyMinesOnTile(int x, int y){
        int count=0;
        if (this.minefield.minefieldBoolArray[x][y]){
            count=999;
        }

        if (x!=0&&y!=0){
            if (this.minefield.minefieldBoolArray[x-1][y-1])
                count++;
        }
        if (y!=0){
            if (this.minefield.minefieldBoolArray[x][y-1])
                count++;
        }
        if (x!=minefield.minefieldBoolArray.length-1&&y!=0){
            if (this.minefield.minefieldBoolArray[x+1][y-1])
                count++;
        }
        if (x!=0){
            if (this.minefield.minefieldBoolArray[x-1][y])
                count++;
        }
        if (x!=minefield.minefieldBoolArray.length-1){
            if (this.minefield.minefieldBoolArray[x+1][y])
                count++;
        }
        if (x!=0&&y!=minefield.minefieldBoolArray.length-1){
            if (this.minefield.minefieldBoolArray[x-1][y+1])
                count++;
        }
        if (y!=minefield.minefieldBoolArray.length-1){
            if (this.minefield.minefieldBoolArray[x][y+1])
                count++;
        }
        if (x!=minefield.minefieldBoolArray.length-1&&y!=minefield.minefieldBoolArray.length-1){
            if (this.minefield.minefieldBoolArray[x+1][y+1])
                count++;
        }

        return count;
    }
    public void changePlayBoardOnHit(){
        int minesNear = numberOfNearbyMinesOnTile(hitX-1, hitY-1);
        if (!(minesNear>500)){
            playBoard[hitY-1][hitX-1]=Integer.toString(minesNear);
        }else {
            showTheWholeGame();
        }

    }
    public void showTheWholeGame(){
        for (int i = 0; i < playBoard.length; i++) {
            for (int j = 0; j < playBoard.length; j++) {
                int minesNear = numberOfNearbyMinesOnTile(i, j);
                if (!(minesNear>500)){
                    playBoard[i][j]=Integer.toString(minesNear);
                }else {
                    playBoard[i][j]="x";
                }
            }
        }
        drawPlayBoard();
    }
}
