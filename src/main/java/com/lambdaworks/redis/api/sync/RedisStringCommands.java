package com.lambdaworks.redis.api.sync;

import java.util.List;
import java.util.Map;
import com.lambdaworks.redis.output.ValueStreamingChannel;
import com.lambdaworks.redis.SetArgs;

/**
 * Synchronous executed commands for Strings.
 * 
 * @param <K> Key type.
 * @param <V> Value type.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 4.0
 * @generated by com.lambdaworks.apigenerator.CreateSyncApi
 */
public interface RedisStringCommands<K, V> {

    /**
     * Append a value to a key.
     * 
     * @param key the key
     * @param value the value
     * @return Long integer-reply the length of the string after the append operation.
     */
    Long append(K key, V value);

    /**
     * Count set bits in a string.
     * 
     * @param key the key
     * 
     * @return Long integer-reply The number of bits set to 1.
     */
    Long bitcount(K key);

    /**
     * Count set bits in a string.
     * 
     * @param key the key
     * @param start the start
     * @param end the end
     * 
     * @return Long integer-reply The number of bits set to 1.
     */
    Long bitcount(K key, long start, long end);

    /**
     * Find first bit set or clear in a string.
     * 
     * @param key the key
     * @param state the state
     * 
     * @return Long integer-reply The command returns the position of the first bit set to 1 or 0 according to the request.
     * 
     *         If we look for set bits (the bit argument is 1) and the string is empty or composed of just zero bytes, -1 is
     *         returned.
     * 
     *         If we look for clear bits (the bit argument is 0) and the string only contains bit set to 1, the function returns
     *         the first bit not part of the string on the right. So if the string is tree bytes set to the value 0xff the
     *         command {@code BITPOS key 0} will return 24, since up to bit 23 all the bits are 1.
     * 
     *         Basically the function consider the right of the string as padded with zeros if you look for clear bits and
     *         specify no range or the <em>start</em> argument <strong>only</strong>.
     * 
     *         However this behavior changes if you are looking for clear bits and specify a range with both
     *         <strong>start</strong> and <strong>end</strong>. If no clear bit is found in the specified range, the function
     *         returns -1 as the user specified a clear range and there are no 0 bits in that range.
     */
    Long bitpos(K key, boolean state);

    /**
     * Find first bit set or clear in a string.
     * 
     * @param key the key
     * @param state the bit type: long
     * @param start the start type: long
     * @param end the end type: long
     * @return Long integer-reply The command returns the position of the first bit set to 1 or 0 according to the request.
     * 
     *         If we look for set bits (the bit argument is 1) and the string is empty or composed of just zero bytes, -1 is
     *         returned.
     * 
     *         If we look for clear bits (the bit argument is 0) and the string only contains bit set to 1, the function returns
     *         the first bit not part of the string on the right. So if the string is tree bytes set to the value 0xff the
     *         command {@code BITPOS key 0} will return 24, since up to bit 23 all the bits are 1.
     * 
     *         Basically the function consider the right of the string as padded with zeros if you look for clear bits and
     *         specify no range or the <em>start</em> argument <strong>only</strong>.
     * 
     *         However this behavior changes if you are looking for clear bits and specify a range with both
     *         <strong>start</strong> and <strong>end</strong>. If no clear bit is found in the specified range, the function
     *         returns -1 as the user specified a clear range and there are no 0 bits in that range.
     */
    Long bitpos(K key, boolean state, long start, long end);

    /**
     * Perform bitwise AND between strings.
     * 
     * @param destination result key of the operation
     * @param keys operation input key names
     * @return Long integer-reply The size of the string stored in the destination key, that is equal to the size of the longest
     *         input string.
     */
    Long bitopAnd(K destination, K... keys);

    /**
     * Perform bitwise NOT between strings.
     * 
     * @param destination result key of the operation
     * @param source operation input key names
     * @return Long integer-reply The size of the string stored in the destination key, that is equal to the size of the longest
     *         input string.
     */
    Long bitopNot(K destination, K source);

    /**
     * Perform bitwise OR between strings.
     * 
     * @param destination result key of the operation
     * @param keys operation input key names
     * @return Long integer-reply The size of the string stored in the destination key, that is equal to the size of the longest
     *         input string.
     */
    Long bitopOr(K destination, K... keys);

    /**
     * Perform bitwise XOR between strings.
     * 
     * @param destination result key of the operation
     * @param keys operation input key names
     * @return Long integer-reply The size of the string stored in the destination key, that is equal to the size of the longest
     *         input string.
     */
    Long bitopXor(K destination, K... keys);

    /**
     * Decrement the integer value of a key by one.
     * 
     * @param key the key
     * @return Long integer-reply the value of {@code key} after the decrement
     */
    Long decr(K key);

    /**
     * Decrement the integer value of a key by the given number.
     * 
     * @param key the key
     * @param amount the decrement type: long
     * @return Long integer-reply the value of {@code key} after the decrement
     */
    Long decrby(K key, long amount);

    /**
     * Get the value of a key.
     * 
     * @param key the key
     * @return V bulk-string-reply the value of {@code key}, or {@literal null} when {@code key} does not exist.
     */
    V get(K key);

    /**
     * Returns the bit value at offset in the string value stored at key.
     * 
     * @param key the key
     * @param offset the offset type: long
     * @return Long integer-reply the bit value stored at <em>offset</em>.
     */
    Long getbit(K key, long offset);

    /**
     * Get a substring of the string stored at a key.
     * 
     * @param key the key
     * @param start the start type: long
     * @param end the end type: long
     * @return V bulk-string-reply
     */
    V getrange(K key, long start, long end);

    /**
     * Set the string value of a key and return its old value.
     * 
     * @param key the key
     * @param value the value
     * @return V bulk-string-reply the old value stored at {@code key}, or {@literal null} when {@code key} did not exist.
     */
    V getset(K key, V value);

    /**
     * Increment the integer value of a key by one.
     * 
     * @param key the key
     * @return Long integer-reply the value of {@code key} after the increment
     */
    Long incr(K key);

    /**
     * Increment the integer value of a key by the given amount.
     * 
     * @param key the key
     * @param amount the increment type: long
     * @return Long integer-reply the value of {@code key} after the increment
     */
    Long incrby(K key, long amount);

    /**
     * Increment the float value of a key by the given amount.
     * 
     * @param key the key
     * @param amount the increment type: double
     * @return Double bulk-string-reply the value of {@code key} after the increment.
     */
    Double incrbyfloat(K key, double amount);

    /**
     * Get the values of all the given keys.
     * 
     * @param keys the key
     * @return List&lt;V&gt; array-reply list of values at the specified keys.
     */
    List<V> mget(K... keys);

    /**
     * Stream over the values of all the given keys.
     * 
     * @param channel the channel
     * @param keys the keys
     * 
     * @return Long array-reply list of values at the specified keys.
     */
    Long mget(ValueStreamingChannel<V> channel, K... keys);

    /**
     * Set multiple keys to multiple values.
     * 
     * @param map the null
     * @return String simple-string-reply always {@code OK} since {@code MSET} can't fail.
     */
    String mset(Map<K, V> map);

    /**
     * Set multiple keys to multiple values, only if none of the keys exist.
     * 
     * @param map the null
     * @return Boolean integer-reply specifically:
     * 
     *         {@code 1} if the all the keys were set. {@code 0} if no key was set (at least one key already existed).
     */
    Boolean msetnx(Map<K, V> map);

    /**
     * Set the string value of a key.
     * 
     * @param key the key
     * @param value the value
     * 
     * @return String simple-string-reply {@code OK} if {@code SET} was executed correctly.
     */
    String set(K key, V value);

    /**
     * Set the string value of a key.
     * 
     * @param key the key
     * @param value the value
     * @param setArgs the setArgs
     * 
     * @return String simple-string-reply {@code OK} if {@code SET} was executed correctly.
     */
    String set(K key, V value, SetArgs setArgs);

    /**
     * Sets or clears the bit at offset in the string value stored at key.
     * 
     * @param key the key
     * @param offset the offset type: long
     * @param value the value type: string
     * @return Long integer-reply the original bit value stored at <em>offset</em>.
     */
    Long setbit(K key, long offset, int value);

    /**
     * Set the value and expiration of a key.
     * 
     * @param key the key
     * @param seconds the seconds type: long
     * @param value the value
     * @return String simple-string-reply
     */
    String setex(K key, long seconds, V value);

    /**
     * Set the value and expiration in milliseconds of a key.
     * 
     * @param key the key
     * @param milliseconds the milliseconds type: long
     * @param value the value
     * @return String simple-string-reply
     */
    String psetex(K key, long milliseconds, V value);

    /**
     * Set the value of a key, only if the key does not exist.
     * 
     * @param key the key
     * @param value the value
     * @return Boolean integer-reply specifically:
     * 
     *         {@code 1} if the key was set {@code 0} if the key was not set
     */
    Boolean setnx(K key, V value);

    /**
     * Overwrite part of a string at key starting at the specified offset.
     * 
     * @param key the key
     * @param offset the offset type: long
     * @param value the value
     * @return Long integer-reply the length of the string after it was modified by the command.
     */
    Long setrange(K key, long offset, V value);

    /**
     * Get the length of the value stored in a key.
     * 
     * @param key the key
     * @return Long integer-reply the length of the string at {@code key}, or {@code 0} when {@code key} does not exist.
     */
    Long strlen(K key);
}
