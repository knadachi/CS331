import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * This class maintains the user interface where the user is asked for information 
 * regarding the test cases and also prints out the optimal sequence of the tests.
 */
public class UI
{
   private Scanner input;

   public UI()
   {
      input = new Scanner( System.in );
   }

   public void start()
   {
      System.out.println( "-------------------------------------------" );
      System.out.println( "Programming Assignment #3 by Kristin Adachi" );
      System.out.println( "-------------------------------------------\n" );
      createTests();
      return;
   }

   public void createTests()
   {
      try
      {
         System.out.print( "Enter the number of linked lists: " );
         int answer = input.nextInt();
         LinkedList<Node>[] listArr = new LinkedList[answer];
         for( int i = 0; i < listArr.length; i++ )
            listArr[i] = new LinkedList<Node>();
         int count = 1;
         for( int i = 1; i <= listArr.length; i++ )
         {
            System.out.print( "Enter the size of linked list #" + i + ": " );
            answer = input.nextInt();
            for( int j = 1; j <= answer; j++ )
            {
               System.out.print( "Enter the cost for T" + count + ": " );
               double cost = (double)input.nextInt();
               System.out.print( "Enter the probability for T" + count + ": " );
               double prob = (double)input.nextInt();
               listArr[i-1].add( new Node( cost, prob ) );
               count++;
            }
         }
         Calculation calc = new Calculation( listArr );
         while( !calc.isEmpty() )
         {
            calc.smallestRatio();
         }
         System.out.println( "\nOP: " + calc.getOp() );
         input.close();
         return;
      }
      catch( InputMismatchException e )
      {
         System.out.println( "Please enter a number." );
         input.nextLine();
         createTests();
      }
   }
}

