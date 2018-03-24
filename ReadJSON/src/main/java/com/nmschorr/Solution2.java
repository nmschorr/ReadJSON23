package com.nmschorr;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution2 {

	/*
	 * Complete the diagonalDifference function below.
	 */
	static int diagonalDifference(int[][] arrayIn) {
		int first=0;
		int sec =0;
		int v = arrayIn.length;
		int column = 0;
		int row = 0;

		while (  column < v)    { 
			while (  row < v)    { 
				int temp = arrayIn[row][column];
				System.out.println ("\n-----row: " + row);
				System.out.println ("-----column " + column);
				System.out.println ("----------------------val: " + temp);
				first = first + temp;
				column++;
				row++;
			}
		}

		column = 0;
		row = v-1;

		while (  column< v)    { 
			while (  row >= 0 )    { 
				int temp2 = arrayIn[row][column];
				System.out.println ("\n-----row: " + row);
				System.out.println ("-----column " + column);
				System.out.println ("---------------------------val: " + temp2);
				sec = sec + temp2;
				column++;
				row--;
			}
		}
     //second
        
   int answer = first - sec;
   if (answer < 0 ) { answer = answer * -1;   }
   return answer;
 }
    public static void main(String[] args) throws IOException {
        int[]v =  { 11, 2, 4 };
        int[]vv =  {4, 5, 6};
        int[]vvv =  {10, 8, -12};
        
       int[][] a = {v,vv,vvv};


        int result = diagonalDifference(a);
        System.out.println(result);


    }
}

