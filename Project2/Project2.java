import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * Program that tests two different algorithms for finding the kth smallest element 
 * in an unsorted array.
 */
public class Project2
{
   public static void main( String[] args )
   {
      //prints the times for algorithm #1 to find the 100th smallest element
      System.out.println( "Algorithm #1:" );
      for( int i = 3; i <= 8; i++ )
      {
         int[] arr = initArray( (int)Math.pow( 10, i ));
         long initTime = System.nanoTime();
         int num = alg1( arr, 0, arr.length-1, 100 );
         long finalTime = System.nanoTime();
         System.out.println( (int)Math.pow( 10, i ) + ": " + (finalTime - initTime) );
         System.out.println( "100th Smallest Element: " + num );
      }

      //prints the times for algorithm #2 to find the 100th smallest element
      System.out.println( "\nAlgorithm #2:" );
      for( int i = 3; i <= 8; i++ )
      {
         int[] arr = initArray( (int)Math.pow( 10, i ));
         long initTime = System.nanoTime();
         int num = alg2( arr, 0, arr.length-1, 100 );
         long finalTime = System.nanoTime();
         System.out.println( (int)Math.pow( 10, i ) + ": " + (finalTime - initTime) );
         System.out.println( "100th Smallest Element: " + num );
      }
   }

   //initializes an array of a given size with random values
   private static int[] initArray( int size )
   {
      Random rand = new Random();
      int[] arr = new int[size];
      for( int i = 0; i < arr.length; i++ )
         arr[i] = rand.nextInt( 10000 ) + 1;
      return arr;
   }

   //algorithm #1
   private static int alg1( int[] arr, int f, int l, int k )
   {
      if( l - f == 0 ) //only one element
         return arr[f];
      else
      {
         Random rand = new Random();
         int index = rand.nextInt(l-f) + f; //generates a random index between first and last
         int pivot = arr[index];
         
         //pointers for array
         int a = f;
         int b = f;
         //partitions array into three sets and determines s1 and s2
         while( b <= l )
         {
            if( arr[b] < pivot )
            {
               int temp = arr[a];
               arr[a] = arr[b];
               arr[b] = temp;
               a++;
               b++;
            }
            else
               b++;
         }
         int part1 = a;
         int s1 = a - f;
         b = a;
         while( b <= l )
         {
            if( arr[b] == pivot )
            {
               int temp = arr[a];
               arr[a] = arr[b];
               arr[b] = temp;
               a++;
               b++;
            }
            else
               b++;
         }
         int part2 = a;
         int s2 = part2 - part1;

         if( s1 >= k )
            return alg1( arr, f, part1-1, k );
         else
         {
            if( s1 + s2 >= k )
               return pivot;
            else
               return alg1( arr, part2, l, k-s1-s2 );
         }
      }
   }

   //algorithm #2
   private static int alg2( int[] arr, int f, int l, int k )
   {
      if( arr.length < 50 )
      {
         Arrays.sort(arr);
         return arr[k-1];
      }
      else
      {
         int size = ( arr.length / 5 ) + 1;
         int[] medians = new int[size];
         //divides array into partitions of 5 elements
         for( int i = 0; i < arr.length; i+=5 )
         {
            int[] temp = new int[5];
            if( i + 5 < arr.length )
            {
               temp = Arrays.copyOfRange( arr, i, i+5 );
               Arrays.sort( temp );
               medians[i/5] = temp[3]; //finds the median of each partition and adds it to the medians set
            }
         }
         int pivot = alg2( medians, 0, medians.length-1, medians.length/2 );

         //pointers for array
         int a = f;
         int b = f;
         //partitions array into three sets and determines s1 and s2
         while( b <= l )
         {
            if( arr[b] < pivot )
            {
               int temp = arr[a];
               arr[a] = arr[b];
               arr[b] = temp;
               a++;
               b++;
            }
            else
               b++;
         }
         int part1 = a;
         int s1 = a - f;
         b = a;
         while( b <= l )
         {
            if( arr[b] == pivot )
            {
               int temp = arr[a];
               arr[a] = arr[b];
               arr[b] = temp;
               a++;
               b++;
            }
            else
               b++;
         }
         int part2 = a;
         int s2 = part2 - part1;

         if( s1 >= k )
            return alg1( arr, f, part1-1, k );
         else
         {
            if( s1 + s2 >= k )
               return pivot;
            else
               return alg1( arr, part2, l, k-s1-s2 );
         }
      }
   }
}

