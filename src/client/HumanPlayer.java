package client;

import java.util.Scanner;

public class HumanPlayer extends Player {


    public HumanPlayer(String name, char mark){
        super(name,mark);
    }

    public void makeMove(){
        int row = 0;
        int column = 0;
        boolean check = false;
        Scanner s = new Scanner(System.in);
        while (!check) {
            System.out.println(name + ", make your move! Enter which row you would like to play.");
            row = Integer.parseInt(s.nextLine());
            while (row > 2 || row < 0) {
                System.out.println("Improper row entered. Please try again!");
                System.out.println(name + ", which row would you like to play?");
                row = Integer.parseInt(s.nextLine());
            }

            System.out.println("You have entered row " + Integer.toString(row) + ". " +
                    "Now enter your desired column. ");
            column = Integer.parseInt(s.nextLine());

            while (column > 2 || column < 0) {
                System.out.println("Improper column entered. Please try again!");
                System.out.println(name +", which column would you like to play?");
                column = Integer.parseInt(s.nextLine());
            }
            if (board.getMark(row, column) == 'X' || board.getMark(row, column) == 'O'){
                System.out.println("Space is already taken! Please try again.");
            }
            else
                check = true;
        }
        board.addMark(row, column, mark);
    }
}
