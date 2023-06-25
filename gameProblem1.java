import java.util.Scanner;

public class gameProblem1 { //   Right

        public static void main(String[] args) {
            Scanner scn = new Scanner(System.in);
           System.out.println("please enter the number of rows/largets position ");
           int r1 = scn.nextInt();
           System.out.println("please enter number of columns");
           int c1 = scn.nextInt();

            int[][] arr = new int[r1][c1];

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print("Is there an alive cell at if yes enter '1' or '0' " + i + "," + j + ": ");
                    arr[i][j] = scn.nextInt();
                }
            }
            // Print the result array
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }


            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    find(arr, i, j);
                }
            }
            System.out.println();
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    update(arr, i, j);
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }

//            for (int i = 0; i < arr.length; i++) {
//                for (int j = 0; j < arr[i].length; j++) {
//                    System.out.print(arr[i][j]);
//                }
//                System.out.println();
//            }

            scn.close();
        }

        static void find(int[][] arr, int i, int j) {
            int aliveNeighCount = 0;
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    int posX = i + x;
                    int posY = j + y;

                    if (posX >= 0 && posX < arr.length && posY >= 0 && posY < arr[0].length) {
                        int neighValue = arr[posX][posY];
                        if (x == -1 || (x == 0 && y == -1)) {
                            neighValue = neighValue / 10;
                        }
                        if (neighValue == 1 && !(x == 0 && y == 0)) {
                            aliveNeighCount++;
                        }
                    }
                }
            }
            arr[i][j] = arr[i][j] * 10 + aliveNeighCount;
        }

        public static void update(int[][] arr, int i, int j) {
            int neighCount = arr[i][j] % 10;
            boolean isNeighAlive = arr[i][j] / 10 == 1;

            if (isNeighAlive && (neighCount < 2 || neighCount > 3)) {
                arr[i][j] = 0;
            } else if (isNeighAlive && (neighCount == 2 || neighCount == 3)) {
                arr[i][j] = 1;
            } else if (!isNeighAlive && neighCount == 3) {
                arr[i][j] = 1;
            } else {
                arr[i][j] = isNeighAlive ? 1 : 0;
            }
           // System.out.print(arr[i][j]);
        }
    }

