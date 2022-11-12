import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WalkingLevels {
    static ConsoleColors colors = new ConsoleColors();
    static final int GAME_WIDTH = 10;
    static final int GAME_HEIGHT = 10;
    boolean startGame;
    static char PLAYER = 'P';
    static char CUBE = 'C';
    static char TARGET = 'T';
    static char WALL = 'W';
    static char FLOOR = 'F';
    Random random = new Random();
    static int playerX;
    static int playerY;
    static int cubeX;
    static int cubeY;
    static int targetX;
    static int targetY;
    static int score = 0;
    static char direction;
    static Tile[][] levelTiles;
    static Level level;
    static final int LEVELS_AMOUNT = 4;
    static int actualLevel = 0;
    static ArrayList<String> levels = new ArrayList<>();
    static  boolean gameWon = false;
    public static void main(String[] args){

        for(int l = 0; l<LEVELS_AMOUNT;l++){
            levels.add("levels/level"+ l + ".txt");
        }

        level = new Level();
        level.getLevelInfo(levels.get(actualLevel));

        playerX = level.playerX;
        playerY = level.playerY;
        cubeX = level.cubeX;
        cubeY = level.cubeY;
        targetX = level.targetX;
        targetY =level.targetY;
        levelTiles = level.levelTiles;
        draw(level);
    }

    public void start(){
//        gameTiles[playerX][playerY].type = PLAYER;
//        gameTiles[cubeX][cubeY].type = CUBE;
//        gameTiles[targetX][targetY].type = TARGET;
    }
    public static void draw(Level level){
        for (int h=0;h< level.height;h++) {
            for (int w=0;w< level.width;w++) {
                switch (level.levelTiles[w][h].type) {
                    case 'P' -> System.out.print(colors.RED_BACKGROUND_BRIGHT + "   " + colors.RESET);
                    case 'F' -> System.out.print(colors.BLACK_BACKGROUND_BRIGHT + "   " + colors.RESET);
                    case 'T' -> System.out.print(colors.PURPLE_BACKGROUND_BRIGHT + "   " + colors.RESET);
                    case 'C' -> System.out.print(colors.GREEN_BACKGROUND_BRIGHT + "   " + colors.RESET);
                    case 'W' -> System.out.print(colors.BLACK_BACKGROUND+"   "+colors.RESET);
                }
            }
            System.out.println();
        }
        move();
    }

    public static void move() {
        System.out.println("score: " + score);
        System.out.println("enter your direction (w/a/s/d)");
        System.out.println("enter r to restart");
        try (Scanner getAnswer = new Scanner(System.in)) {
            direction = getAnswer.next().toLowerCase().charAt(0);
            levelTiles[playerX][playerY].type = FLOOR;

            switch (direction) {
                case 'r' -> {
                    main(null);
                }
                case 'w' -> {
                    playerY--;
                    if (levelTiles[playerX][playerY].type == WALL) {
                        playerY++;
                    }
                }
                case 's' -> {
                    playerY++;
                    if (levelTiles[playerX][playerY].type == WALL) {
                        playerY--;
                    }
                }
                case 'a' -> {
                    playerX--;
                    if (levelTiles[playerX][playerY].type == WALL) {
                        playerX++;
                    }
                }
                case 'd' -> {
                    playerX++;
                    if (levelTiles[playerX][playerY].type == WALL) {
                        playerX--;
                    }
                }
            }

            if (levelTiles[playerX][playerY].type == CUBE) {
                switch (direction) {
                    case 'w' -> {
                        cubeY--;
                        if (levelTiles[cubeX][cubeY].type == WALL) {
                            cubeY++;
                            playerY++;
                        }
                    }
                    case 's' -> {
                        cubeY++;
                        if (levelTiles[cubeX][cubeY].type == WALL) {
                            cubeY--;
                            playerY--;
                        }
                    }
                    case 'a' -> {
                        cubeX--;
                        if (levelTiles[cubeX][cubeY].type == WALL) {
                            cubeX++;
                            playerX++;
                        }
                    }
                    case 'd' -> {
                        cubeX++;
                        if (levelTiles[cubeX][cubeY].type == WALL) {
                            cubeX--;
                            playerX--;
                        }
                    }
                }
            }

            if (levelTiles[cubeX][cubeY].type == TARGET) {
                System.out.println("you scored a point!");
                score++;
                actualLevel++;
                if (actualLevel==LEVELS_AMOUNT){
                    gameWon = true;
                }
                else {
                    main(null);
                }
            }

            //UNCHANGEABLE
            levelTiles[targetX][targetY].type = TARGET;
            levelTiles[playerX][playerY].type = PLAYER;
            levelTiles[cubeX][cubeY].type = CUBE;
            if(!gameWon) {
                draw(level);
            }
            else{
                System.out.println("you won the game!!");
                System.exit(0);
            }
//        }
        }
    }
}
