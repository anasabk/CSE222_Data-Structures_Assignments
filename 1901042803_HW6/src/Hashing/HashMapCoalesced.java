package Hashing;

import KW.KWHashMap;

public class HashMapCoalesced<K, V> implements KWHashMap<K, V> {
    /** Contains key‐value pairs for a hash table. */
    private static class Entry<K, V> {
        /** The key */
        private final K key;
        /** The value */
        private V value;

        private int nextIndex;

        /** Creates a new key‐value pair.
         @param key The key
         @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            nextIndex = -1;
        }

        /** Retrieves the key.
         @return The key
         */
        public K getKey() {
            return key;
        }

        /** Retrieves the value.
         @return The value
         */
        public V getValue() {
            return value;
        }

        /** Sets the value.
         @param val The new value
         @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        public int setNext(int index) {
            int oldIndex = nextIndex;
            nextIndex = index;
            return oldIndex;
        }

        public int getNext() {
            return nextIndex;
        }
    }

// Data Fields
    /** The table */
    private Entry<K, V>[] table;
    /** The number of keys */
    private int numKeys;
    /** The initial capacity */
    private static final int START_CAPACITY = 11;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 0.75;
    /** The number of probes in a search action */
    private static long probe = 0;

// Constructors
    public HashMapCoalesced() {
        table = new Entry[START_CAPACITY];
        numKeys = 0;
    }

// Methods
    @Override
    public V get(Object key) {
    // Find the index of the target or the entry before it.
        int index = find(key);

    // If the search is successful, return the value.
        if (table[index] != null){
        // The target is found.
            if(key.equals(table[index]))
                return table[index].getValue();

        // The target is the next entry in the chain.
            else if (table[index].nextIndex != -1 &&
                key.equals(table[table[index].nextIndex]))
                return table[index].getValue();
        }

        // key not found.
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (Entry<K, V> Entry : table) {
            if (Entry != null)
                return false;
        }

        return true;
    }

    @Override
    public V put(K key, V value) {
    // Find the index of the target or the entry before it.
        int index = find(key),
            newIndex = index;

        Entry<K, V> target = table[index];

    // Decide where to add the entry
        if (target != null) {
        // An entry with the key was not found.
        // Find an index to insert the new entry.
            if (!key.equals(target.key) &&
                target.nextIndex == -1){
            // Search for an empty slot.
                probe = 0;
                while (table[newIndex] != null) {
                    ++probe;
                // The double hashing formula.
                    newIndex = DoubleHash(key.hashCode());

                    if (newIndex >= table.length)
                        newIndex -= table.length;
                }

                target.nextIndex = newIndex;
            }

        // An entry that contains the key was found.
        // Replace the value for this key.
            else {
                if (target.nextIndex != -1 &&
                    key.equals(table[table[index].nextIndex].key))
                    index = table[index].nextIndex;

                V oldV = table[index].value;
                table[index].value = value;
                return oldV;
            }
        }

    // Insert the entry.
        table[newIndex] = new Entry(key, value);
        numKeys++;

    // Rehash if the load factor is exceeded.
        double loadFactor =
                (double) numKeys / table.length;
        if (loadFactor > LOAD_THRESHOLD)
            rehash();
        return null;
    }

    @Override
    public V remove(Object key) {
    // Find the index of the target or the entry before it.
        int index = find(key);

    // Find the exact entry and return its value after deleting it.
        if (table[index] != null){
            V oldV = null;
            int targetIndex = table[index].nextIndex;

        // There is an entry after the target in the chain
            if (table[index].nextIndex != -1) {
            // The entry is the head of the chain.
            // Store its value and replace it with the next
            // entry in the chain.
                if (key.equals(table[index].key)) {
                    oldV = table[index].value;
                    table[index] = table[table[index].nextIndex];
                }

            // The entry is in the middle of a chain.
            // Point the next index of the previous entry
            // to the entry after the target.
                else if (key.equals(table[table[index].nextIndex].key)) {
                    oldV = table[targetIndex].value;
                    table[index].nextIndex =
                            table[table[index].nextIndex].nextIndex;
                }

            // The target was not found.
                else return null;
            }

        // The entry is alone in the chain.
            else if (key.equals(table[index].key)){
                oldV = table[index].value;
                targetIndex = index;
            }

        // The entry was not found.
            else return null;

        // Delete the entry and return it's value.
            table[targetIndex] = null;
            return oldV;
        }

    // The entry was not found.
        return null;
    }

    @Override
    public int size() {
        return table.length;
    }

    /**
     * Finds the index of the target key, or the last entry before the
     * target or an empty slot in a chain.
     * @pre The table is not full.
     * @param key The key of the target object
     * @return The position of the target or the last entry before the
     * target or an empty slot in a chain.
     */
    private int find(Object key) {
    // Calculate the starting index.
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length; // Make it positive.

    // Traverse the indexes of the entries in the chain
    // until the target is found.
        while(table[index] != null){
        // The target is found.
            if (key.equals(table[index].getKey()))
                return index;

        // There are more entries in the chain.
            else if(table[index].nextIndex != -1){
                if(key.equals(
                        table[table[index].nextIndex].getKey()))
                    return index;

                index = table[index].nextIndex;
            }

            else
                return index;
        }

    // The target was not found, return the index of
    // the last entry in the chain.
        return index;
    }

    /**
     * Expands table size when loadFactor exceeds LOAD_THRESHOLD.
     * @post The size of the table is doubled and is an odd integer.
     * Each entry from the original table is reinserted into the
     * expanded table. The value of numKeys is reset to the number
     * of items actually inserted.
     */
    private void rehash() {
        // Save a reference to oldTable.
        Entry<K, V>[] oldTable = table;
        // Double capacity of this table.
        table = new Entry[maxIntPrimeUnder(2 * oldTable.length + 1)];
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++)
            if ((oldTable[i] != null))
                // Insert entry in expanded table
                put(oldTable[i].getKey(), oldTable[i].getValue());
    }

    /**
     * Returns the last prime number that is less than
     * or equal to N.
     * @param N The upper bound,
     * @return the last prime number that is less than
     * or equal to N.
     */
    private static int maxIntPrimeUnder(double N) {
        int i, n = (int)N;
        while(n > 0){
            for(i = 2; i < n/2; ++i)
                if(n%i == 0) break;
            if(i == n/2)
                return n;
            --n;
        }

        throw new IllegalArgumentException();
    }

    /**
     * Return the index of the key for initial hashing.
     * @param key The hash code of the key.
     * @return the index of the key for initial hashing.
     */
    private int Hash1(int key) {
        return key % table.length;
    }

    /**
     * Used in the double hashing algorithm.
     * @param key The hash code of the key.
     */
    private int Hash2(int key) {
        int prime = maxIntPrimeUnder(table.length * 0.8);
        return prime - (key % prime);
    }

    /**
     * Returns the new index for double hashing.
     * @param key The hashcode of the key.
     * @return The new index for double hashing.
     */
    private int DoubleHash(int key) {
        return (int) (Hash1(key) + probe * Hash2(key)) % table.length;
    }
}
