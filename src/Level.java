import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
    int width, height, playerX, playerY, cubeX, cubeY, targetX, targetY;
    Tile[][] levelTiles;
    char type;
    String levelLine;
    public void getLevelInfo(String file){
        try {
            File myObj = new File(file);
            Scanner levelReader = new Scanner(myObj);
            width = Integer.parseInt(levelReader.nextLine());
            height = Integer.parseInt(levelReader.nextLine());
            levelTiles = new Tile[width][height];

            for (int h=0;h<height;h++){
                levelLine = levelReader.nextLine();
                for (int w=0;w<width;w++){
                    levelTiles[w][h] = new Tile(levelLine.charAt(w));
                    switch (levelTiles[w][h].type){
                        case 'P'-> {
                            playerX = w;
                            playerY = h;
                        }
                        case 'T' -> {
                            targetX = w;
                            targetY = h;
                        }
                        case 'C' -> {
                            cubeX = w;
                            cubeY = h;
                        }
                    }
                }
            }
            System.out.println("player x: "+playerX+", y: "+playerY);
            System.out.println("cube x: "+cubeX+", y: "+cubeY);
            System.out.println("target x: "+targetX+", y: "+targetY);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
