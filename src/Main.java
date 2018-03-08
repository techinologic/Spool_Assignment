// Paolo T. Inocencion
// COSC306 Operating Systems
// Spool Programming Assignment
// March 8, 2018

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    final static int segment[] = new int[4];
    final static int count[] = new int[4];
    final static int limit[] = new int[4];
    final static String letter[] = new String[4];

    final static int bytePerSegment = 50;


    public static void main(String[] args) throws IOException {

        int pid = 0;
        int btp = 0;
        int tbtp = 0;

        Path filePath = Paths.get("src/nums.txt");
        Scanner scanner = new Scanner(filePath);

        for (int i = 0; scanner.hasNext(); i++) {
            if (i == 3) {
                System.out.println(pid + " " + btp + " " + tbtp);

                //check tbtp if 0;
                if (tbtp > 0) { //means new program

                    // if new program assign pid to segment
                    assignSegment(pid);

                    //save limit
                    limit[getSegment(pid)] = tbtp;

                    //save btp, which will be the letter to be printed
                    letter[getSegment(pid)] = getCharForNumber(btp);

                    //just to test count number
                    System.out.println("pid " + pid + " assigned to S" + (getSegment(pid) + 1));
                } else {


                    //check if count < limit(tbtp)
                    if (count[getSegment(pid)] < limit[getSegment(pid)]) {

                        // add btp to count
                        count[getSegment(pid)] += btp; //adds 4

                        //to check count value
                        System.out.println("Count in S" + (getSegment(pid) + 1)
                                + ": " + count[getSegment(pid)]);

                        //if count is >= tbtp, print all segments

                        //check again if = to tbtp
                        if (count[getSegment(pid)] >= limit[getSegment(pid)]) {
                            System.out.println("tbtp in S" + (getSegment(pid) + 1) + " reached: print whole segment\n");

                            // print all segments then clear which segment is full
                            printAll();
                            System.out.println();
                            System.out.println("reinitialize S" + (getSegment(pid) + 1));

                            reinitializeSegment(getSegment(pid));
                            System.out.println();
                            printAll();

                            System.out.println();
                            System.out.println("PIDs:");
                            System.out.println("S1: " + segment[0]);
                            System.out.println("S2: " + segment[1]);
                            System.out.println("S3: " + segment[2]);
                            System.out.println("S4: " + segment[3]);
                            System.out.println();

                        }
                    }

                }
                i = 0;
            }

            if (scanner.hasNextInt() && i == 0) {
                pid = scanner.nextInt();
            }
            if (scanner.hasNextInt() && i == 1) {
                btp = scanner.nextInt();
            }
            if (scanner.hasNextInt() && i == 2) {
                tbtp = scanner.nextInt();
            }
        }
    }

    // method to print all segments
    public static void printAll() {

        System.out.print("S1: ");
        for (int i = 0; i < bytePerSegment; i++) {
            if (i < limit[0]) {
                // move to nextline 25 bytes
                if (i == 25) {
                    System.out.println();
                }
                // print the letter
                System.out.print(letter[0] + " ");
            } else {
                if (i == 25) {
                    System.out.println();
                }
                //print dashes for unused spaces
                System.out.print("- ");
            }

        }
        System.out.println();

        System.out.print("S2: ");
        for (int i = 0; i < bytePerSegment; i++) {
            if (i < limit[1]) {
                // move to nextline 25 bytes
                if (i == 25) {
                    System.out.println();
                }
                // print the letter
                System.out.print(letter[1] + " ");
            } else {
                if (i == 25) {
                    System.out.println();
                }
                //print dashes for unused spaces
                System.out.print("- ");
            }
        }

        System.out.println();
        System.out.print("S3: ");
        for (int i = 0; i < bytePerSegment; i++) {
            if (i < limit[2]) {
                // move to nextline 25 bytes
                if (i == 25) {
                    System.out.println();
                }
                // print the letter
                System.out.print(letter[2] + " ");
            } else {
                if (i == 25) {
                    System.out.println();
                }
                //print dashes for unused spaces
                System.out.print("- ");
            }

        }
        System.out.println();

        System.out.print("S4: ");
        for (int i = 0; i < bytePerSegment; i++) {
            if (i < limit[3]) {
                // move to nextline 25 bytes
                if (i == 25) {
                    System.out.println();
                }
                // print the letter
                System.out.print(letter[3] + " ");
            } else {
                if (i == 25) {
                    System.out.println();
                }
                //print dashes for unused spaces
                System.out.print("- ");
            }

        }
        System.out.println();
    }

    // method to reinitialize a segment to - and clear PID
    public static void reinitializeSegment(int segmentNum) {

        for (int i = 0; i < bytePerSegment; i++) {

            //change all contents to -
            letter[segmentNum] = "-";

        }
        segment[segmentNum] = 0;

    }


    // get which segment number the pid is assigned
    public static int getSegment(int pid) {

        //find if pid is in segments
        for (int i = 0; i < segment.length; i++) {
            if (pid == segment[i]) {
                return i;
            }
        }

        // if pid is not found in segments
        // save it in free segment

        return 0;
    }

    //assign to segment and return segment number.
    public static int assignSegment(int pid) {

        //find free segment and save pid to it.
        for (int i = 0; i < segment.length; i++) {
            if (segment[i] == 0) {
                segment[i] = pid;
                return i;
            }
        }
        System.out.println("No Available Segment");
        return 0;
    }


    // method to convert a number to an Alphabet
    public static String getCharForNumber(int i) {

        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }

}
