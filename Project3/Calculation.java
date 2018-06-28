import java.util.LinkedList;

/**
 * This class handles the calculations for finding the optimal sequence.
 */
public class Calculation
{
   private LinkedList<Node>[] listArr;
   private String op;

   public Calculation( LinkedList<Node>[] l )
   {
      listArr = l;
      for( int i = 0; i < listArr.length; i++ )
         calculateRatios( listArr[i] );
      op = "";
   }

   /**
    * Calculates the ratios for each test in the given linked list.
    */
   public void calculateRatios( LinkedList<Node> list )
   {
      double sumCost = 0;
      double sumProb = 0;
      for( int i = 0; i < list.size(); i++ )
      {
         Node curNode = list.get(i);
         sumCost += curNode.getCost();
         sumProb += curNode.getProb();
         curNode.setRatio( sumCost / sumProb );
      }
   }

   /**
    * Calculates the smallest ratio out of all the test cases; it then removes 
    * the test case from the linked list along with its predecessors.
    */
   public void smallestRatio()
   {
      int arrIndex = 0;
      int listIndex = 0;
      double smallestRatio = -1;

      //finds the smallest ratio
      for( int i = 0; i < listArr.length; i++ )
      {
         for( int j = 0; j < listArr[i].size(); j++ )
         {
            Node curNode = listArr[i].get(j);
            if( smallestRatio == -1 || curNode.getRatio() < smallestRatio )
            {
               arrIndex = i;
               listIndex = j;
               smallestRatio = curNode.getRatio();
            }
         }
      }

      //removes the test case and its predecessors
      for( int i = 0; i <= listIndex; i++ )
      {
         Node n = listArr[arrIndex].remove();
         op = op + n.getName() + " ";
      }

      //recalculates the ratios for the edited linked list
      calculateRatios( listArr[arrIndex] );
   }

   /**
    * Checks if linked lists of the array are all empty; returns true if they 
    * are and false otherwise.
    */
   public boolean isEmpty()
   {
      int empty = 0;
      for( int i = 0; i < listArr.length; i++ )
      {
         if( listArr[i].size() == 0 )
            empty++;
      }
      if( empty == listArr.length )
         return true;
      return false;
   }

   /**
    * Returns the optimal sequence (OP) string.
    */
   public String getOp()
   {
      return op;
   }
}

