import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LeaderBoard {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        List<Integer> gRanks=new ArrayList<Integer>();
        for(int i=0; i<scores.length-1; i++){
            if(scores[i]!=scores[i+1])
                gRanks.add(scores[i]);
        }
        gRanks.add(scores[scores.length-1]); 
        
        List<Integer> aRanks=new ArrayList<Integer>();

            for(int j=0; j<alice.length; j++){
                int rSize=gRanks.size();
                int low=0, high=gRanks.size()-1, mid=0;
                // System.out.println("@j="+j+" Ranks->"+gRanks.toString());

                while(low<=high){
                    mid=low+(high-low)/2;
                    // System.out.println("@j="+j+" mid->"+mid);
                    if(alice[j]==gRanks.get(mid)){
                        aRanks.add(mid+1);
                        break;
                    }
                    else if(alice[j]<gRanks.get(mid)){
                        low=mid;
                    }else if(alice[j]>gRanks.get(mid)){
                        high=mid;
                    }

                    if(low+1==high||low==high){
                        if(alice[j]>gRanks.get(low)){
                            aRanks.add(low+1);
                            gRanks.add(low, alice[j]); 
                            break;   
                        }else if(alice[j]<gRanks.get(high)){
                            aRanks.add(high+2);
                            gRanks.add(high+1, alice[j]);    
                            break;
                        }else if(alice[j]==gRanks.get(high)){
                            aRanks.add(high+1);
                            break;
                        }else if(alice[j]<gRanks.get(low)&&alice[j]>gRanks.get(high)){
                            aRanks.add(low+2);
                            gRanks.add(low+1, alice[j]);    
                            break;
                        }
                    }  
                }
            }
            int arr[]=aRanks.stream().mapToInt(m->m).toArray();
            return arr;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
