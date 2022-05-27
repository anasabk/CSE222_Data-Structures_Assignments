package Programs;

public class Q5 {
    /**The borders for printing the combinations */
    public static String borders;

    /**
     * Returns the total number of combinations blocks of 
     * lengths from 3 to the length of the array can make in
     *  an array of the given length.
     * @param length : The length of the array
     * @param printSwitch : A switch to print the combinations found
     * @return The number of combinations
     */
    public static int countConfigs(int length, boolean printSwitch){
        if(printSwitch){
            StringBuilder bordersBuild = new StringBuilder();
            for(int i = 0; i < length; ++i)
                bordersBuild.append("--");

            bordersBuild.append("-\n");
            borders = bordersBuild.toString();
        }

        return countArrayConfigs(length, 3, printSwitch);
    }

    /**
     * Find the number of combinations a block starting
     * from a certain length until the length of the array can make.
     * @param length : The length of the array
     * @param blockLen : The starting length of the block
     * @param printSwitch : A switch to print the combinations found 
     * @return The total number of combinations
     */
    private static int countArrayConfigs(int length, int blockLen, boolean printSwitch){
        if(blockLen == length){
            if(printSwitch){
                for(int i = 0; i < length; ++i)
                    System.out.printf("--");
                System.out.printf("-\n");

                for(int i = 0; i < length; ++i)
                    System.out.printf("|*");
                System.out.printf("|\n");

                for(int i = 0; i < length; ++i)
                    System.out.printf("--");
                System.out.printf("-\n");
            }

            return 1;
        }

        else if(blockLen < length)
            return countBlockConfigs(length, blockLen, 0, new String(), printSwitch) + countArrayConfigs(length, blockLen + 1, printSwitch);

        else 
            return 0;
    }

    /**
     * Find the number of combinations a block of a certain 
     * length can make in an array.
     * @param length : The length of the array
     * @param blockLen : The length of the block
     * @param counter : A counter for moving the block one cell
     * @param previous : String of the static part of the combinations
     * @param printSwitch : A switch to print the combinations found 
     * @return The total number of combinations
     */
    private static int countBlockConfigs(int length, int blockLen, int counter, String previous, boolean printSwitch){
        if(blockLen == length){
            if(printSwitch){
                System.out.printf(borders);
                System.out.printf(previous);

                for(int i = 0; i < length; ++i)
                    System.out.printf("|*");
                System.out.printf("|\n");

                System.out.printf(borders);
            }

            return 1;
        }

        else if(blockLen > length)
            return 0;

        else {
            int total = 0;
            StringBuilder tempString = new StringBuilder();

            if(length >= blockLen*2 + 1){
                if(printSwitch){
                    for(int i = 0; i < blockLen; ++i)
                        tempString.append("|*");
                    tempString.append("| "); 
                }

                total = countBlockConfigs(length - blockLen - 1, blockLen, blockLen, previous + tempString.toString(), printSwitch);
                // total = total*2 - 1;
            }

            if(counter > 0)
                total += countBlockConfigs(length - 1, blockLen, counter - 1, "| " + previous, printSwitch) - 1;

            if(printSwitch)
                for(int i = 0; i < length - blockLen + 1; ++i){
                    System.out.printf(borders);
                    System.out.printf(previous);

                    for(int j = 0; j < i; ++j)
                        System.out.printf("| ");

                    for(int j = 0; j < blockLen; ++j)
                        System.out.printf("|*");

                    for(int j = i + blockLen; j < length; ++j)
                        System.out.printf("| ");

                    System.out.printf("|\n" + borders);
                }

            total += length - blockLen + 1;
            return total;
        }
    }
}
