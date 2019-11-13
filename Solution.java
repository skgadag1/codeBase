import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int magicSum=15, n=s.length, cost=n*n, tempCost=0, dif=0, ele1=0, ele2=0;
        
        List<Integer>elements=new ArrayList<Integer>();
        List<Integer>costs=new ArrayList<Integer>();
        List<int [][]>magicSquares=new ArrayList<int [][]>();
        int [][] magicSquare=new int [n][n];
        int [][] temp=new int [n][n];
        int mid=(n+1)/2;

        // for(int i=mid; i<n*n; i+=n)
        //     magicSum+=i;
        int midEle=magicSum/n;
        for(int i=0; i<n*n; i++){
            elements.add(i, i+1!=midEle?i+1:0);
        }
        magicSquare[mid-1][mid-1]=midEle;
        magicSquare[mid-1][0]=1;
        magicSquare[mid-1][n-1]=n*n;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                
                if(magicSquare[i][j]==0&&i!=mid-1){
                    dif=magicSum-magicSquare[mid-1][j];
                    for(int k=0; k<elements.size(); k++){
                        ele1=elements.get(k);
                        if(ele1!=0)
                        for(int l=0; l<elements.size(); l++){
                            
                            ele2=elements.get(l);
                             
                            if(k!=l&&ele2!=0){
                                
                                if(ele1+ele2==dif){
                                    
                                    if(j!=mid-1&&ele1>ele2){
                                        magicSquare[i][j]=ele1;
                                        magicSquare[n-1][j]=ele2;
                                    }else if(j==mid-1&&ele1>ele2){
                                        magicSquare[i][j]=ele2;
                                        magicSquare[n-1][j]=ele1;
                                    }else if(j==mid-1&&ele1<ele2){
                                        magicSquare[i][j]=ele1;
                                        magicSquare[n-1][j]=ele2;
                                    }else{
                                        magicSquare[i][j]=ele2;
                                        magicSquare[n-1][j]=ele1;
                                    }
                                   
                                    elements.set(k, 0);
                                    elements.set(l, 0);
                                    
                                }
                            }
                        }
                    }
                }
            }
        }
        magicSquares.add(magicSquare);
        cost=0;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    temp[i][j]=magicSquare[n-i-1][j];
                    if(s[i][j]!=temp[i][j])
                        cost+=Math.abs(s[i][j]-temp[i][j]);
                }
            }
            costs.add(cost);
            magicSquares.add(temp);
            cost=0;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    temp[i][j]=magicSquare[i][n-j-1];
                    if(s[i][j]!=temp[i][j])
                        cost+=Math.abs(s[i][j]-temp[i][j]);
                }
            }
            costs.add(cost);
            magicSquares.add(temp);
            cost=0;
        int temp1[][]=magicSquares.get(magicSquares.size()-1);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                temp[i][j]=temp1[i][n-j-1];
                if(s[i][j]!=temp[i][j])
                    cost+=Math.abs(s[i][j]-temp[i][j]);
            }
        }
        costs.add(cost);
        magicSquares.add(temp);
        cost=0;
        for(int i=3; i<magicSquares.size(); i++, cost=0){
            int temp2[][]=magicSquares.get(i);
            for(int j=0; j<temp2.length; j++)
                for(int k=0; k<temp2.length; k++){
                    temp[j][k]=temp2[k][j];
                    if(s[j][k]!=temp[k][j])
                        cost+=Math.abs(s[j][k]-temp[j][k]);
                }
            costs.add(cost);
            magicSquares.add(temp);
        }
        cost=0;
        for(int i=0; i<n; i++)
                for(int j=0; j<n; j++)
                    if(s[i][j]!=magicSquare[i][j])
                        cost+=Math.abs(s[i][j]-magicSquare[i][j]);
        costs.add(cost);
        cost=Collections.min(costs);
       System.out.println("min->"+cost);
        return cost;
            
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
