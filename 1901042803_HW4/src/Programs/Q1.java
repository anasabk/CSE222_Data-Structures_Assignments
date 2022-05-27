package Programs;

/**
 * A system that searches for a given occurrence of a string 
 * in a larger string.
 */
public class Q1 {
    /**
     * Return the index of the specified occurrence target string in 
     * the container string, or return -1 if the target's occurrences 
     * in the container are less than the given occurrence.
     * @param target : The string to be found
     * @param container : The string containing the target
     * @param occurrence : The occurrence of the target
     * @return The index of the given occurrence of the target in the string
     * @throws IllegalArgumentException  When either the target or the 
     * container is a null reference, or the given occurrence is smaller than 1.
     */
    public static int searchStr(String target, String container, int occurrence){
        if (occurrence < 1 ||
            target == null ||
            container == null)
            throw new IllegalArgumentException();

        return searchStr_help(target, container, occurrence, 0);
    }

    /**
     * Recursively searches for the given occurrence of the target 
     * string in the container string, return its index, or -1
     * if the target's occurrences in the container are less than 
     * the given occurrence.
     * @param target : The string to be found
     * @param container : The string containing the target
     * @param occurrence : The occurrence of the target
     * @param index : The start index of the search
     * @return The index of the given occurrence of the target in the string
     */
    private static int searchStr_help(String target, String container, int occurrence, int index){
        if(occurrence == 1)
            return container.indexOf(target, index);

        else{
            /**Find the first occurrence of the target */
            index = container.indexOf(target, index);

            /**Return -1 if the target was not found */
            if(index < 0)
                return index;

            /**Return the index of the next occurrence */
            else 
                return searchStr_help(target, container, occurrence - 1, index + target.length());
        }
    }
}
