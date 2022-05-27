package Hashing;

import KW.KWHashMap;
import Trees.BinarySearchTree;

public class HashMapChain<K extends Comparable<K>, V> implements KWHashMap<K, V> {
    /** Contains key‐value pairs for a hash table. */
    private static class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>>{
        // Data Fields
        /** The key */
        private final K key;
        /** The value */
        private V value;

        // Constructors
        /**
         * Creates a new key‐value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // Methods
        /**
         * Retrieves the key.
         * @return The key
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        @Override
        public int compareTo(Entry<K, V> entry) {
            if(entry == null)
                throw new IllegalArgumentException();
            return hashCode() - entry.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Entry)) return false;
            return hashCode() == o.hashCode();
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

// Data Fields
    /** The table */
    private BinarySearchTree<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The initial capacity */
    private static final int START_CAPACITY = 11;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;

// Constructors
    public HashMapChain() {
        table = new BinarySearchTree[START_CAPACITY];
        numKeys = 0;
    }

//Methods
    @Override
    public V get(Object key) {
    // Convert the hash code to an index
        int index = key.hashCode()%table.length;

    // If the index is smaller than 0, wrap around
        if (index < 0)
            index += table.length;

    // The target is not found
        if (table[index] == null)
            return null;

    // Search the chain in the index for the target
        else {
            Entry<K, V> target = table[index].find(new Entry<K, V>((K) key, null));
            if(target == null)
                return null;

            return target.value;
        }
    }

    @Override
    public boolean isEmpty() {
        for(var temp : table)
            if(temp != null)
                return false;

        return true;
    }

    @Override
    public V put(K key, V value) {
    // Convert the hash code to an index
        int index = key.hashCode()%table.length;

    // If the index is smaller than 0, wrap around
        if (index < 0)
            index += table.length;

    // The target index is empty, create a chain and insert in it
        if (table[index] == null) {
            table[index] = new BinarySearchTree<Entry<K, V>>();
            table[index].add(new Entry<K, V>(key, value));
        }

    // Insert the entry in the chain
        else {
            Entry<K, V> target =
                    table[index].add_replace(new Entry<K, V>(key, value));

            if(target != null)
                return target.value;
        }

    // Increment the number of keys in the table
        ++numKeys;

    // Rehash when the load threshold is exceeded
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }

    @Override
    public V remove(Object key) {
    // Convert the hash code to an index
        int index = key.hashCode()%table.length;

    // If the index is smaller than 0, wrap around
        if (index < 0)
            index += table.length;

    // The target is not found
        if (table[index] == null)
            return null;

    // Remove the target from the chain if found
        else {
            Entry<K, V> target = table[index].delete(new Entry<K, V>((K) key, null));
            if(target == null)
                return null;

            --numKeys;
            return target.value;
        }
    }

    @Override
    public int size() {
        return table.length;
    }

    /**
     * Expands table size when loadFactor exceeds LOAD_THRESHOLD.
     * @post The size of the table is doubled and is an odd integer.
     * Each entry from the original table is reinserted into the
     * expanded table. The value of numKeys is reset to the number
     * of items actually inserted.
     */
    private void rehash() {
        Entry<K, V> tempTarget;

    // Save a reference to oldTable.
        BinarySearchTree<Entry<K, V>>[] oldTable = table;

    // Double capacity of this table.
        table = new BinarySearchTree[maxIntPrimeUnder(2 * oldTable.length + 1)];

    // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        for (BinarySearchTree<Entry<K, V>> entryTree : oldTable)
            if ((entryTree != null))
                // Insert entry in expanded table
                while (!entryTree.isEmpty()) {
                    tempTarget = entryTree.delete(entryTree.getData());
                    put(tempTarget.key, tempTarget.value);
                }
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
}
