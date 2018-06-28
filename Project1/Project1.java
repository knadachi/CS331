import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Project1
{
   public static void main( String[] args )
   {
      // prints time for algorithm #1 to run and sorting times (using java sort)
      System.out.println( "\nAlgorithm #1 (using java sort):" );
      for( int i = 3; i <= 8; i++ )
      {
         int[] nums1 = initArray( (int)Math.pow( 10, i ));
         int[] nums2 = initArray( (int)Math.pow( 10, i ));

         long initTime1 = System.nanoTime();
         Arrays.sort( nums1 );
         Arrays.sort( nums2 );          
         long finalTime1 = System.nanoTime();

         System.out.println( "Time for Sorting " + (int)Math.pow( 10, i ) + ": " + (finalTime1 - initTime1) );

         long initTime2 = System.nanoTime();
         ArrayList<Integer> intersection = alg1( nums1, nums2 );
         long finalTime2 = System.nanoTime();
         
         System.out.println( (int)Math.pow( 10, i ) + ": " + (finalTime2 - initTime2) );
      }

      // prints time for algorithm #2 to run (hash map)
      System.out.println( "\nAlgorithm #2 Hash Map:" ); 
      for( int i = 3; i <= 7; i++ )
      {
         int[] nums1 = initArray( (int)Math.pow( 10, i ));
         int[] nums2 = initArray( (int)Math.pow( 10, i ));
         
         long initTime = System.nanoTime();
         ArrayList<Integer> intersection = algHashMap( nums1, nums2 );
         long finalTime = System.nanoTime();
         
         System.out.println( (int)Math.pow( 10, i ) + ": " + (finalTime - initTime) );
      }

      // prints time for algorithm #2 to run (tree set)
      System.out.println( "\nAlgorithm #2 Tree Set:" );
      for( int i = 3; i <= 7; i++ )
      {
         int[] nums1 = initArray( (int)Math.pow( 10, i ));
         int[] nums2 = initArray( (int)Math.pow( 10, i ));
         
         long initTime = System.nanoTime();
         ArrayList<Integer> intersection = algTree( nums1, nums2 );
         long finalTime = System.nanoTime();
         
         System.out.println( (int)Math.pow( 10, i ) + ": " + (finalTime - initTime) );
      }
   }

   // initializes an array of a given size with random values
   private static int[] initArray( int size )
   {
      Random rand = new Random();
      int[] nums = new int[size];
      for( int i = 0; i < nums.length; i++ ){
         nums[i] = rand.nextInt(1000) + 1; //generates a random number between 1 and 1000
      }
      return nums;
   }

   // algorithm #1: finds the intersection of two sorted arrays
   private static ArrayList<Integer> alg1( int[] nums1, int[] nums2 )
   {
      ArrayList<Integer> intersection = new ArrayList<Integer>();
      int count1 = 0;
      int count2 = 0;
      int previous = 0;
      while( count1 < nums1.length && count2 < nums2.length )
      {
         if( nums1[count1] < nums2[count2] )
            count1++;
         else if( nums1[count1] > nums2[count2] )
            count2++;
         else
         {
            if( nums1[count1] != previous )
            {
               previous = nums1[count1];
               intersection.add( nums1[count1] );
            }
            count1++;
            count2++;
         }
      }
      return intersection;
   }

   // algorithm #2: uses a hash map to find the intersection of two unsorted arrays
   private static ArrayList<Integer> algHashMap( int[] nums1, int[] nums2 )
   {
      ArrayList<Integer> intersection = new ArrayList<Integer>();
      HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
      for( int i = 0; i < nums2.length; i++ )
         set.put( nums2[i], nums2[i] );
      for( int i = 0; i < nums1.length; i++ )
         intersection.add( nums1[i] );
      return intersection;
   }

   // algorithm #2: uses a tree set to find the intersection of two unsorted arrays
   private static ArrayList<Integer> algTree( int[] nums1, int[] nums2 )
   { 
      ArrayList<Integer> intersection = new ArrayList<Integer>();
      TreeSet<Integer> set = new TreeSet<Integer>();
      boolean b;
      for( int i = 0; i < nums2.length; i++ )
         b = set.add( nums2[i] );
      for( int i = 0; i < nums1.length; i++ )
         intersection.add( nums1[i] );
      return intersection;
   }
}

