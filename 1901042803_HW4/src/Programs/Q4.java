package Programs;

/**
 * A system that finds the product of two integers
 */
public class Q4 {
    public static int foo (int integer1, int integer2){
        if (integer1 < 10 || integer2 < 10)
            return integer1 * integer2;

    //number_of_digit returns the number of digits in an integer
        int digits1 = (int) (Math.log10(integer1) + 1), 
            digits2 = (int) (Math.log10(integer2) + 1),
            half;

        if(digits1 > digits2)
            half = digits1/2;

        else
            half = digits2/2;

    // split_integer splits the integer into returns two integers
    // from the digit at position half. i.e.,
    // first integer = integer / 2^half
    // second integer = integer % 2^half
        int int1 = integer1 / (int) Math.pow(10, half), 
            int2 = integer1 % (int) Math.pow(10, half), 
            int3 = integer2 / (int) Math.pow(10, half), 
            int4 = integer2 % (int) Math.pow(10, half),

            sub0 = foo (int2, int4),
            sub1 = foo ((int1 + int2), (int3 + int4)),
            sub2 = foo (int1, int3);

        return (sub2*(int) Math.pow(10, 2*half))+((sub1-sub2-sub0)*(int) Math.pow(10, half))+(sub0);
    }
}