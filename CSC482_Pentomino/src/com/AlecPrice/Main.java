package com.AlecPrice;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        System.out.print("Enter 3, 4, 5 or 6: ");

        Scanner scanner = new Scanner(System.in);
        var input = Integer.parseInt(scanner.nextLine());

        var x = 0;
        var y = 0;

        switch (input) {
            case 3 -> {
                x = 20;
                y = 3;
            }
            case 4 -> {
                x = 15;
                y = 4;
            }
            case 5 -> {
                x = 12;
                y = 5;
            }
            case 6 -> {
                x = 10;
                y = 6;
            }
        }
        //DS used to store the encoding of the shapes
        ArrayList<ArrayList<Integer>> encodings = new ArrayList<>();
        int endOfGrid = (x * y) + 12;
        for(int shape = 0; shape < Shapes.shapes.length; shape++){
            //Start at position 12 because 0-11 identify the Shape
            for(int start = 12; start < endOfGrid; start++ ){
                //Checks the direction the pieces could be in. The alternative I had was just getting all of the results and / 4
                for(int direction = 0; direction < Shapes.shapes[shape].length; direction++){
                    ArrayList<Integer> coordinates = new ArrayList<>();
                    coordinates.add(shape);
                    coordinates.add(start);
                    boolean validGridCoords = true;
                    for(int j = 0; j < Shapes.shapes[shape][direction].length; j += 2){
                        int newX = Shapes.shapes[shape][direction][j];
                        int newY = Shapes.shapes[shape][direction][j + 1];
                        int tempCoords = start + (x * newY) + newX;

                        int offset = start - 12;
                        int startY = offset / x;
                        int startX = offset % x;

                        int endX = startX + newX;
                        int endY = startY + newY;
                        //Checks if the coordinates are in the grid
                        if((endX < x && endY < y && endX >= 0 && endY >= 0)){
                            coordinates.add(tempCoords);

                        }else{
                            validGridCoords = false;
                            break;
                        }
                    }
                    if(validGridCoords){
                        encodings.add(coordinates);
                        //prints the encoding
                        System.out.println(coordinates);
                    }
                }
            }
        }


        var coverDimensions = 12 + y * x;
        var DLX = new DLX(coverDimensions, encodings);
        DLX.run();
        System.out.println("Solutions Found: " + DLX.getNumberOfSolutions());
    }

}
