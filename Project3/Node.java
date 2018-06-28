/**
 * This class maintains a node that represents a test case; it contains 
 * accessor methods for its name, cost, probability, and ratio as well as a 
 * mutator method to update the ratio.
 */
public class Node
{
   private String name;
   private static int nextName = 1;
   private double cost;
   private double prob;
   private double ratio;

   public Node( double c, double p )
   {
      name = "T" + nextName;
      nextName++;
      cost = c;
      prob = p;
      ratio = 0;
   }
   
   public String getName()
   {
      return name;
   }

   public double getCost()
   {
      return cost;
   }

   public double getProb()
   {
      return prob;
   }

   public double getRatio()
   {
      return ratio;
   }

   public void setRatio( double r )
   {
      ratio = r;
   }
}

