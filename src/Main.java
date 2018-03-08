import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    final static int segment[] = new int[4];
    final static int count[] = new int[4];
    final static int limit[] = new int[4];
    final static int letter[] = new int[4];


    public static void main(String[] args) throws IOException {

        int pid = 0;
        int btp = 0;
        int tbtp = 0;

        //initialize array with '-'
        String s1[] = new String[50];
        String s2[] = new String[50];
        String s3[] = new String[50];
        String s4[] = new String[50];


        for (int i = 0; i < 50; i++) {
            s1[i] = "-";
            s2[i] = "-";
            s3[i] = "-";
            s4[i] = "-";
        }

        Path filePath = Paths.get("src/nums.txt");
        Scanner scanner = new Scanner(filePath);

        for (int i = 0; scanner.hasNext(); i++) {
            if (i==3) {
                System.out.println(pid + " " + btp + " " + tbtp);

                //check tbtp if 0;
                if (tbtp > 0) { //means new program

                    //save btp to count
                    //count[assignSegment(pid)] = btp;

                    //save limit
                    assignSegment(pid);
                    limit[getSegment(pid)] = tbtp;
                    letter[getSegment(pid)] = btp;

                } else {

                    //test count number
                    System.out.println("pid in s" + (getSegment(pid)+1));

                    //check if count < limit
                    if (count[getSegment(pid)] < limit[getSegment(pid)]) {

                        // add btp to count
                        count[getSegment(pid)] += btp; //adds 4

                        //check count value
                        System.out.println("Count in s" + (getSegment(pid) + 1)
                                + " " + count[getSegment(pid)]);

                        //if count is >= tbtp, print all segments

                        //check again if = to tbtp
                        if (count[getSegment(pid)] == limit[getSegment(pid)]) {
                            System.out.println("tbtp reached: print segment");

                            // pass which char to print and count
                            print(limit[getSegment(pid)], pid); //print segment and re-initialize to -
                        }
                    }

                }
                i = 0;
            }

            if (scanner.hasNextInt() && i==0) {
                pid = scanner.nextInt();
            }
            if (scanner.hasNextInt() && i==1) {
                btp = scanner.nextInt();
            }
            if (scanner.hasNextInt() && i==2) {
                tbtp = scanner.nextInt();
            }
        }
    }


    // method to print all segments
    public static void print(int pid, int btp) {

        //print number of letters
        System.out.print("S1: ");

        //count of s1
        for (int i = 0; i < limit[getSegment(pid)]; i++) {
            System.out.print(getCharForNumber(letter[getSegment(pid)]));
        }
        // print dashes
        for (int i = 0; i < 50-btp; i++) {
            System.out.print("-");
        }
        System.out.println();


    }

    // find what segment pid is saved
    public static int getSegment(int pid) {

        //find if pid is in segments
        for (int i = 0; i < segment.length; i++) {
            if (pid == segment[i]){
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
            if (segment[i] == 0 ) {
                segment[i] = pid;
                return i;
            }
        }
        System.out.println("No Available Segment");
        return 0;
    }

    public void initializeSegment() {
        for (int i = 0; i < segment.length; i++) {
            segment[i] = 0;
        }
    }

    public static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }



}
