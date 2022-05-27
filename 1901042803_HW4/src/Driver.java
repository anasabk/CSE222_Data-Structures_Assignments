import java.util.ArrayList;

import Programs.*;

public class Driver {
    public static void main(String[] args) {
        // Q1
        String  container = "A man was running in the corridor, when he slipped and fell while he was running in the corridor, because he was running in the corridor.",
                target = "running in the corridor";

        System.out.printf("Q1\nTarget : %s\nOccurence: 2\nOutput: %d\n\n", target, Programs.Q1.searchStr(target, container, 2));
        System.out.printf("Target : %s\nOccurence: 4\nOutput: %d\n\n", target, Programs.Q1.searchStr(target, container, 4));
        System.out.printf("Target : %s\nOccurence: 1\nOutput: %d\n\n", "he", Programs.Q1.searchStr("he", container, 1));
        System.out.printf("Target : %s\nOccurence: 2\nOutput: %d\n\n", "woman", Programs.Q1.searchStr("woman", container, 2));

        // Q2
        int[] array = new int[20];

        for(int i = 0; i < 20; ++i)
            array[i] = i;

        System.out.printf("Q2\nMinimum : %d\nMaximum : %d\nOutput : %d\n\n", 2, 18, Q2.countInRange(array, 2, 18));
        System.out.printf("Minimum : %d\nMaximum : %d\nOutput : %d\n\n", 2, 17, Q2.countInRange(array, 2, 17));
        System.out.printf("Minimum : %d\nMaximum : %d\nOutput : %d\n\n", 3, 18, Q2.countInRange(array, 3, 18));
        System.out.printf("Minimum : %d\nMaximum : %d\nOutput : %d\n\n", -4, 38, Q2.countInRange(array, -4, 38));
        System.out.printf("Minimum : %d\nMaximum : %d\nOutput : %d\n\n", 5, 5, Q2.countInRange(array, 5, 5));
        try {
            System.out.printf("Minimum : %d\nMaximum : %d\nOutput : %d\n", 10, 5, Q2.countInRange(array, 10, 5));
        } catch (Exception e) { System.out.printf("%s\n", e.getMessage()); }
        System.out.printf("Minimum : %d\nMaximum : %d\nOutput : %d\n", 22, 34, Q2.countInRange(array, 22, 34));
        System.out.printf("Minimum : %d\nMaximum : %d\nOutput : %d\n", -15, -2, Q2.countInRange(array, -15, -2));

        // Q3
            array = new int[] {3, 15, 2, 7, 4, 8, 2, 9, 3, 9, 2, 18, 21, -10,  6, 0, 4, 12, 5};

        ArrayList<int[]> result = Q3.getContiguousSumList(array, 11);
        
        for(int i = 0; i < result.size(); ++i){
            for(int j = result.get(i)[0]; j < result.get(i)[1] + 1; ++j)
                System.out.printf("%d ", array[j]);

            System.out.printf("\n");
        }

        result = Q3.getContiguousSumList(array, 30);
        
        for(int i = 0; i < result.size(); ++i){
            for(int j = result.get(i)[0]; j < result.get(i)[1] + 1; ++j)
                System.out.printf("%d ", array[j]);

            System.out.printf("\n");
        }

        result = Q3.getContiguousSumList(array, 100);
        
        for(int i = 0; i < result.size(); ++i){
            for(int j = result.get(i)[0]; j < result.get(i)[1] + 1; ++j)
                System.out.printf("%d ", array[j]);

            System.out.printf("\n");
        }

        // Q4
        System.out.printf("%d\n", Q4.foo(16, 27));
        System.out.printf("%d\n", Q4.foo(118, -31));
        System.out.printf("%d\n", Q4.foo(3, 8));

        // Q5
        System.out.printf("%d\n", Q5.countConfigs(7, true));
        System.out.printf("%d\n", Q5.countConfigs(4, false));
        System.out.printf("%d\n", Q5.countConfigs(2, false));
        System.out.printf("%d\n", Q5.countConfigs(10, false));
    }
}
