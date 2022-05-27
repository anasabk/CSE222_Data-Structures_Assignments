package Programs;

/**
 * A system that finds the number of elements that are 
 * between two given values in a sorted array.
 */
public class Q2 {
    /**
     * Return the number of elements in a range specified 
     * by the given minimum and maximum values in the given array. <P>
     * @param array : The array containing the elements
     * @param minValue : The minimum value of the range
     * @param maxValue : The maximum value of the range
     * @return The number of elements in the range
     * @throws IllegalArgumentException
     * When the minimum value is larger than the maximum value.
     */
    public static int countInRange(int[] array, int minValue, int maxValue)
    {
        if(maxValue < minValue)
            throw new IllegalArgumentException("minimum value is larger than maximum value.");

        int start = getMinIndex(array, minValue, 0, array.length - 1),
            end = getMaxIndex(array, maxValue, 0, array.length - 1);

        return end - start + 1;
    }

    /**
     * Finds the start index of the minimum value of the range.
     * @param array : The array containing the elements
     * @param target : The minimum value.
     * @param start : the start index of the search.
     * @param end : The end index of the range
     * @return : The index of the minimum value.
     */
    private static int getMaxIndex(int[] array, int target, int start, int end){
        if(array[(end + start)/2] == target)
            return (end + start)/2 - 1;

        else if(end == start){
            if(array[end] < target)
                return end;

            else
                return end - 1;
        }

        else if(array[(end + start)/2] > target)
            return getMaxIndex(array, target, start, (end + start)/2);

        else
            return getMaxIndex(array, target, (end + start + 1)/2, end);
    }

    /**
     * Finds the start index of the maximum value of the range.
     * @param array : The array containing the elements
     * @param target : The maximum value.
     * @param start : the start index of the search.
     * @param end : The end index of the range
     * @return : The index of the maximum value.
     */
    private static int getMinIndex(int[] array, int target, int start, int end){
        if(array[(end + start)/2] == target)
            return (end + start)/2 + 1;

        else if(end == start){
            if(array[start] <= target)
                return start + 1;

            else
                return start;
        }

        else if(array[(end + start)/2] > target)
            return getMinIndex(array, target, start, (end + start)/2);

        else
            return getMinIndex(array, target, (end + start + 1)/2, end);
    }
}
