package Programs;

import java.util.ArrayList;

/**
 * A system that finds the indeces that represent the subarrays
 * of contiguous numbers that are in total equal to a given sum in a given array.
 */
public class Q3 {
    /**
     * Returns the indeces of the subarrays as 2-element integer
     * arrays in an ArrayList. Each array in the ArrayList represents 
     * the following: {index of the first integer, index of the last integer}.
     * @param array : An unsorted integer array.
     * @param sum : The target sum.
     * @return  An ArrayList containing arrays of indeces
     * @throws IllegalArgumentException
     * When the array is null.
     */
    public static ArrayList<int[]> getContiguousSumList(int[] array, int sum){
        if(array == null)
            throw new IllegalArgumentException();

        return getContiguousList(array, sum, 0);
    }

    /**
     * Recursively The array element by element, if the sequence
     * of numbers match the sum, store it's indeces, else go for 
     * the next element.
     * @param array : An unsorted integer array.
     * @param sum : The target sum.
     * @param index : The start index of the search operation
     * @return An ArrayList containing arrays of indeces
     */
    private static ArrayList<int[]> getContiguousList(int[] array, int sum, int index){
        if(index == array.length)
            return new ArrayList<int[]>();

        int index2 = getContiguousSubArray(array, sum, index);
        ArrayList<int[]> result = new ArrayList<int[]>();

        if(index2 != -1)
            result.add(new int[] {index, index2});

        result.addAll(getContiguousList(array, sum, index + 1));
        return result;
    }

    /**
     * Recursively sum the contiguous numbers in the array
     * starting from the given index. If the sum was matched,
     *  return the index of the last number, else return -1.
     * @param array : An unsorted integer array.
     * @param sum : The target sum.
     * @param index : The start index.
     * @return The index of the last number
     */
    private static int getContiguousSubArray(int[] array, int sum, int index){
        if(index == array.length)
            return -1;

        else if(sum == array[index])
            return index;

        else
            return getContiguousSubArray(array, sum - array[index], index + 1);
    }
}
