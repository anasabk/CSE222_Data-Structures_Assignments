public class PairFinder{
    public static int[] FindPairLoop(int[] arr1, int[] arr2, int sum){
        if(arr2.length != 0)
            for(int i = 0; i < arr1.length; ++i)
                for(int j = 0; j < arr2.length; ++j)
                    if(arr1[i] + arr2[j] == sum)
                        return new int[] {arr1[i], arr2[j]};

        return null;
    }

    public static int[] FindPairRecursive(int[] arr1, int[] arr2, int sum, int index){
        int[] result = null;

        if(index >= arr1.length)
            return null;

        else if((result = Find(sum - arr1[index], arr2, 0)) != null){
            result[1] = arr1[index];
            return result;
        }

        else return FindPairRecursive(arr1, arr2, sum, index + 1);
    }

    private static int[] Find(int target, int[] arr, int index){
        if(index >= arr.length)
            return null;

        else if(arr[index] == target){
            int[] result = new int[2];
            result[0] = arr[index];
            return result;
        }

        else return Find(target, arr, index + 1);
    }

    public static void main(String[] args) {
        int[] arr1 = new int[1000],
              arr2 = new int[1000];

        for(int i = 0; i < arr1.length; ++i)
            arr1[i] = i + 1;

        for(int i = 0; i < arr2.length; ++i)
            arr2[i] = i + 1;

        int sum = 2000;
        int[] result = new int[2];
        Long startTime = 0L, endTime = 0L, AverageTime = 0L;

        startTime = System.nanoTime();
        result = FindPairRecursive(arr1, arr2, sum, 0);
        endTime = System.nanoTime();
        AverageTime = endTime - startTime;

        System.out.printf("n1: %d, n2: %d\nExecution time: %d\n", result[0], result[1], AverageTime);
    }
}