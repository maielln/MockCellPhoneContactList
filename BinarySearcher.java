import java.util.ArrayList;
/**
   A class for executing binary searches through an array.
*/
public class BinarySearcher
{
   /**
      Constructs a BinarySearcher.
      @param anArray a sorted array of integers
   */
   public BinarySearcher(ArrayList<contact> myArrayList)
   {
	   a = myArrayList;
   }

   /**
      Finds a value in a sorted array, using the binary
      search algorithm.
      @param v the value to search
      @return the index at which the value occurs, or the negative position
       -1 which represents where it SHOULD go.
      if it does not occur in the array
   */
   public int searchF(String v)
   {
      int low = 0;
      int high = a.size() - 1;
      while (low <= high)
      {
         int mid = (low + high) / 2;
         contact temp = (a.get(mid));
         String val = temp.getFName();

         if (v.compareTo(val) == 0) // a[mid] == v
            return mid;
         else if (v.compareTo(val) > 0) // a[mid] < v
            low = mid + 1;
         else
            high = mid - 1;
      }
      return -low - 1;
   }


   public int searchL(String v)
   {
      int low = 0;
      int high = a.size() - 1;
      while (low <= high)
      {
         int mid = (low + high) / 2;
         contact temp = (a.get(mid));
         String val = temp.getLName();

         if (v.compareTo(val) == 0) // a[mid] == v
            return mid;
         else if (v.compareTo(val) > 0) // a[mid] < v
            low = mid + 1;
         else
            high = mid - 1;
      }
      return -low - 1;
   }

   private ArrayList<contact> a;
}