package Test;

import Hashing.HashMapChain;
import Hashing.HashMapCoalesced;

public class Driver {

    public static void main(String[] args) {
        Integer temp, temp2;
        long time, timeC;
        HashMapChain<Integer, Integer> hashTable = new HashMapChain<Integer, Integer>();
        HashMapCoalesced<Integer, Integer> hashTableC = new HashMapCoalesced<Integer, Integer>();

        int[][][] small = new int[100][100][2],
                  med = new int[100][1000][2],
                  large = new int[100][10000][2];

        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; ++j){
                small[i][j][0] = (int) (32000 * Math.random());
                small[i][j][1] = (int) (32000 * Math.random());
            }

        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 1000; ++j){
                med[i][j][0] = (int) (32000 * Math.random());
                med[i][j][1] = (int) (32000 * Math.random());
            }

        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 10000; ++j){
                large[i][j][0] = (int) (32000 * Math.random());
                large[i][j][1] = (int) (32000 * Math.random());
            }



        for (int i = 0; i < 100; i++){
            hashTable = new HashMapChain<>();
            hashTableC = new HashMapCoalesced<>();
            for (int j = 0; j < 100; ++j){
                hashTable.put(small[i][j][0], small[i][j][1]);
                hashTableC.put(small[i][j][0], small[i][j][1]);
            }
        }

        System.out.print("Testing Coalesced and Chaining Hashing Techniques:\n\n");
        System.out.print("\nInserting the small sets to both tables.\n\n");
        System.out.printf("Removing:\nThe entry with the key %d is removed from both tables.\n", small[99][67][0]);
        timeC = System.nanoTime();
        temp = hashTableC.remove(small[99][67][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(small[99][67][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the removed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is not in the table, so it can not be removed.\n", 32421);
        timeC = System.nanoTime();
        temp = hashTableC.remove(32421);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(32421);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the removed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);


        System.out.printf("Accessing:\nThe entry with the key %d is sought from both tables.\n", small[99][37][0]);
        timeC = System.nanoTime();
        temp = hashTableC.remove(small[99][37][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(small[99][37][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the accessed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is not in the tables, so can not be accessed.\n", 32421);
        timeC = System.nanoTime();
        temp = hashTableC.get(32421);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.get(32421);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the accessed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);


        System.out.printf("Adding:\nThe entry with the key %d and value %d is added to the tables.\n", small[99][51][1], small[99][37][0]);
        timeC = System.nanoTime();
        temp = hashTableC.put(small[99][51][1], small[99][37][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.put(small[99][51][1], small[99][37][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The replaced values associated with the key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is modified with the value %d.\n", small[99][51][1], small[99][37][0] + 231);
        timeC = System.nanoTime();
        temp = hashTableC.put(small[99][51][1], small[99][37][0] + 231);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.put(small[99][51][1], small[99][37][0] + 231);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The replaced values associated with the key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);



        for (int i = 0; i < 100; i++) {
            hashTable = new HashMapChain<>();
            hashTableC = new HashMapCoalesced<>();
            for (int j = 0; j < 1000; ++j) {
                hashTable.put(med[i][j][0], med[i][j][1]);
                hashTableC.put(med[i][j][0], med[i][j][1]);
            }
        }

        System.out.print("\nInserting the medium sets to both tables.\n\n");
        System.out.printf("Removing:\nThe entry with the key %d is removed from both tables.\n", med[99][670][0]);
        timeC = System.nanoTime();
        temp = hashTableC.remove(med[99][670][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(med[99][670][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the removed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is not in the table, so it can not be removed.\n", 32421);
        timeC = System.nanoTime();
        temp = hashTableC.remove(32421);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(32421);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the removed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);


        System.out.printf("Accessing:\nThe entry with the key %d is sought from both tables.\n", med[99][370][0]);
        timeC = System.nanoTime();
        temp = hashTableC.remove(med[99][370][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(med[99][370][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the accessed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is not in the tables, so can not be accessed.\n", 32421);
        timeC = System.nanoTime();
        temp = hashTableC.get(32421);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.get(32421);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the accessed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);


        System.out.printf("Adding:\nThe entry with the key %d and value %d is added to the tables.\n", med[99][510][1], med[99][370][0]);
        timeC = System.nanoTime();
        temp = hashTableC.put(med[99][510][1], med[99][370][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.put(med[99][510][1], med[99][370][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The replaced values associated with the key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is modified with the value %d.\n", med[48][510][1], med[76][370][0] + 231);
        timeC = System.nanoTime();
        temp = hashTableC.put(med[99][510][1], med[99][370][0] + 231);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.put(med[99][510][1], med[99][370][0] + 231);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The replaced values associated with the key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);



        for (int i = 0; i < 100; i++) {
            hashTable = new HashMapChain<>();
            hashTableC = new HashMapCoalesced<>();
            for (int j = 0; j < 10000; ++j) {
                hashTable.put(large[i][j][0], large[i][j][1]);
                hashTableC.put(large[i][j][0], large[i][j][1]);
            }
        }

        System.out.print("\nInserting the large sets to both tables.\n\n");
        System.out.printf("Removing:\nThe entry with the key %d is removed from both tables.\n", large[99][6700][0]);
        timeC = System.nanoTime();
        temp = hashTableC.remove(large[99][6700][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(large[99][6700][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the removed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is not in the table, so it can not be removed.\n", 32421);
        timeC = System.nanoTime();
        temp = hashTableC.remove(32421);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(32421);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the removed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);


        System.out.printf("Accessing:\nThe entry with the key %d is sought from both tables.\n", large[99][3700][0]);
        timeC = System.nanoTime();
        temp = hashTableC.remove(large[99][3700][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.remove(large[99][3700][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the accessed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is not in the tables, so can not be accessed.\n", 32421);
        timeC = System.nanoTime();
        temp = hashTableC.get(32421);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.get(32421);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The values associated with the accessed key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);


        System.out.printf("Adding:\nThe entry with the key %d and value %d is added to the tables.\n", large[99][5100][1], large[99][3700][0]);
        timeC = System.nanoTime();
        temp = hashTableC.put(large[99][5100][1], large[99][3700][0]);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.put(large[99][5100][1], large[99][3700][0]);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The replaced values associated with the key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);

        System.out.printf("The entry with the key %d is modified with the value %d.\n", large[99][5100][1], large[99][3700][0] + 231);
        timeC = System.nanoTime();
        temp = hashTableC.put(large[99][5100][1], large[99][3700][0] + 231);
        timeC = System.nanoTime() - timeC;
        time = System.nanoTime();
        temp2 = hashTable.put(large[99][5100][1], large[99][3700][0] + 231);
        time = System.nanoTime() - time;
        System.out.printf("Time taken for the operation:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", timeC, time);
        System.out.printf("The replaced values associated with the key are:\n Coalesced hash table: %d\n Chaining hash table: %d\n\n", temp, temp2);
    }
}
