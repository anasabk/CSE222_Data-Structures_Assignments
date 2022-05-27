package KW;

public interface KWHashMap <K, V> {
    /**
     * Returns the value associated with the given key
     * if found. Return null if the key is not in the table.
     * @param key The key of the target value.
     * @return the value associated with this key if found.
     * Otherwise, null.
     */
    V get(Object key);

    /**
     * Returns true if this table contains no key‐value mappings.
     * @return True if the table is empty.
     */
    boolean isEmpty();

    /**
     * Associates the specified value with the specified key.
     * Returns the previous value associated with the specified
     * key, or null if there was no mapping for the key.
     * @param key The key of the entry to be inserted.
     * @param value The value associated with this key.
     * @return The old value associated with this key if found;
     * otherwise, null.
     */
    V put(K key, V value);

    /**
     * Removes the mapping for this key from this table if it is present.
     * Returns the previous value associated with the specified key, or
     * null if there was no mapping
     * @param key The key of the entry to be removed.
     * @return The value associated with this key if found;
     * otherwise, null.
     */
    V remove(Object key);

    /**
     * Returns the size of the table
     * @return The size of the table.
     */
    int size();
}
