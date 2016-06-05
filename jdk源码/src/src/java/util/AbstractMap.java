/*
 * @(#)AbstractMap.java	1.50 06/06/16
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.util;
import java.util.Map.Entry;

/**
 * This class provides a skeletal implementation of the <tt>Map</tt>
 * interface, to minimize the effort required to implement this interface.
 *
 * <p>To implement an unmodifiable map, the programmer needs only to extend this
 * class and provide an implementation for the <tt>entrySet</tt> method, which
 * returns a set-view of the map's mappings.  Typically, the returned set
 * will, in turn, be implemented atop <tt>AbstractSet</tt>.  This set should
 * not support the <tt>add</tt> or <tt>remove</tt> methods, and its iterator
 * should not support the <tt>remove</tt> method.
 *
 * <p>To implement a modifiable map, the programmer must additionally override
 * this class's <tt>put</tt> method (which otherwise throws an
 * <tt>UnsupportedOperationException</tt>), and the iterator returned by
 * <tt>entrySet().iterator()</tt> must additionally implement its
 * <tt>remove</tt> method.
 *
 * <p>The programmer should generally provide a void (no argument) and map
 * constructor, as per the recommendation in the <tt>Map</tt> interface
 * specification.
 *
 * <p>The documentation for each non-abstract method in this class describes its
 * implementation in detail.  Each of these methods may be overridden if the
 * map being implemented admits a more efficient implementation.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @version 1.50, 06/16/06
 * @see Map
 * @see Collection
 * @since 1.2
 */

public abstract class AbstractMap<K,V> implements Map<K,V> {		//实现Map接口
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected AbstractMap() {		//构造方法
    }

    // Query Operations

    /**
     * {@inheritDoc}
     *
     * <p>This implementation returns <tt>entrySet().size()</tt>.
     */
    public int size() {		//返回Mao大小
	return entrySet().size();
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation returns <tt>size() == 0</tt>.
     */
    public boolean isEmpty() {		//判断Map是否为空
	return size() == 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation iterates over <tt>entrySet()</tt> searching
     * for an entry with the specified value.  If such an entry is found,
     * <tt>true</tt> is returned.  If the iteration terminates without
     * finding such an entry, <tt>false</tt> is returned.  Note that this
     * implementation requires linear time in the size of the map.
     *
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsValue(Object value) {	//判断mao中是否包含值为value的元素
	Iterator<Entry<K,V>> i = entrySet().iterator();		//生成迭代器
	if (value==null) {
	    while (i.hasNext()) {
		Entry<K,V> e = i.next();
		if (e.getValue()==null)
		    return true;
	    }
	} else {
	    while (i.hasNext()) {
		Entry<K,V> e = i.next();
		if (value.equals(e.getValue()))
		    return true;
	    }
	}
	return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation iterates over <tt>entrySet()</tt> searching
     * for an entry with the specified key.  If such an entry is found,
     * <tt>true</tt> is returned.  If the iteration terminates without
     * finding such an entry, <tt>false</tt> is returned.  Note that this
     * implementation requires linear time in the size of the map; many
     * implementations will override this method.
     *
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsKey(Object key) {	//返回该Map中是否包含Key
	Iterator<Map.Entry<K,V>> i = entrySet().iterator();
	if (key==null) {
	    while (i.hasNext()) {
		Entry<K,V> e = i.next();
		if (e.getKey()==null)
		    return true;
	    }
	} else {
	    while (i.hasNext()) {
		Entry<K,V> e = i.next();
		if (key.equals(e.getKey()))
		    return true;
	    }
	}
	return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation iterates over <tt>entrySet()</tt> searching
     * for an entry with the specified key.  If such an entry is found,
     * the entry's value is returned.  If the iteration terminates without
     * finding such an entry, <tt>null</tt> is returned.  Note that this
     * implementation requires linear time in the size of the map; many
     * implementations will override this method.
     *
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public V get(Object key) {		//获取该key对应的value
	Iterator<Entry<K,V>> i = entrySet().iterator();
	if (key==null) {
	    while (i.hasNext()) {
		Entry<K,V> e = i.next();
		if (e.getKey()==null)
		    return e.getValue();
	    }
	} else {
	    while (i.hasNext()) {
		Entry<K,V> e = i.next();
		if (key.equals(e.getKey()))
		    return e.getValue();
	    }
	}
	return null;
    }


    // Modification Operations

    /**
     * {@inheritDoc}
     *
     * <p>This implementation always throws an
     * <tt>UnsupportedOperationException</tt>.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public V put(K key, V value) {		//Map中添加相应的记录
	throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation iterates over <tt>entrySet()</tt> searching for an
     * entry with the specified key.  If such an entry is found, its value is
     * obtained with its <tt>getValue</tt> operation, the entry is removed
     * from the collection (and the backing map) with the iterator's
     * <tt>remove</tt> operation, and the saved value is returned.  If the
     * iteration terminates without finding such an entry, <tt>null</tt> is
     * returned.  Note that this implementation requires linear time in the
     * size of the map; many implementations will override this method.
     *
     * <p>Note that this implementation throws an
     * <tt>UnsupportedOperationException</tt> if the <tt>entrySet</tt>
     * iterator does not support the <tt>remove</tt> method and this map
     * contains a mapping for the specified key.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public V remove(Object key) {		//移除Key为key的Entry记录
	Iterator<Entry<K,V>> i = entrySet().iterator();
	Entry<K,V> correctEntry = null;		//记录和保存要删除的元素
	if (key==null) {
	    while (correctEntry==null && i.hasNext()) {
		Entry<K,V> e = i.next();
		if (e.getKey()==null)
		    correctEntry = e;
	    }
	} else {
	    while (correctEntry==null && i.hasNext()) {
		Entry<K,V> e = i.next();
		if (key.equals(e.getKey()))
		    correctEntry = e;
	    }
	}

	V oldValue = null;
	if (correctEntry !=null) {		//i迭代到要删除的元素索引处
	    oldValue = correctEntry.getValue();
	    i.remove();			//调用iterator中的移除方法
	}
	return oldValue;
    }


    // Bulk Operations

    /**
     * {@inheritDoc}
     *
     * <p>This implementation iterates over the specified map's
     * <tt>entrySet()</tt> collection, and calls this map's <tt>put</tt>
     * operation once for each entry returned by the iteration.
     *
     * <p>Note that this implementation throws an
     * <tt>UnsupportedOperationException</tt> if this map does not support
     * the <tt>put</tt> operation and the specified map is nonempty.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation calls <tt>entrySet().clear()</tt>.
     *
     * <p>Note that this implementation throws an
     * <tt>UnsupportedOperationException</tt> if the <tt>entrySet</tt>
     * does not support the <tt>clear</tt> operation.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     */
    public void clear() {
	entrySet().clear();
    }


    // Views

    /**
     * Each of these fields are initialized to contain an instance of the
     * appropriate view the first time this view is requested.  The views are
     * stateless, so there's no reason to create more than one of each.
     */
     //这个类的有些属性需要序列化，而其他属性不需要被序列化，打个比方，如果一个
     //用户有一些敏感信息（如密码，银行卡号等），为了安全起见，不希望在网络操作
     //（主要涉及到序列化操作，本地序列化缓存也适用）中被传输，这些信息对应的变量就
     //可以加上transient关键字。换句话说，这个字段的生命周期仅存于调用者的内存中而不会写到磁盘里持久化。
    transient volatile Set<K>        keySet = null;
    transient volatile Collection<V> values = null;

    /**
     * {@inheritDoc}
     *
     * <p>This implementation returns a set that subclasses {@link AbstractSet}.
     * The subclass's iterator method returns a "wrapper object" over this
     * map's <tt>entrySet()</tt> iterator.  The <tt>size</tt> method
     * delegates to this map's <tt>size</tt> method and the
     * <tt>contains</tt> method delegates to this map's
     * <tt>containsKey</tt> method.
     *
     * <p>The set is created the first time this method is called,
     * and returned in response to all subsequent calls.  No synchronization
     * is performed, so there is a slight chance that multiple calls to this
     * method will not all return the same set.
     */
    public Set<K> keySet() {
	if (keySet == null) {
	    keySet = new AbstractSet<K>() {
		public Iterator<K> iterator() {
		    return new Iterator<K>() {
			private Iterator<Entry<K,V>> i = entrySet().iterator();

			public boolean hasNext() {
			    return i.hasNext();
			}

			public K next() {
			    return i.next().getKey();
			}

			public void remove() {
			    i.remove();
			}
                    };
		}

		public int size() {
		    return AbstractMap.this.size();
		}

		public boolean contains(Object k) {
		    return AbstractMap.this.containsKey(k);
		}
	    };
	}
	return keySet;
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation returns a collection that subclasses {@link
     * AbstractCollection}.  The subclass's iterator method returns a
     * "wrapper object" over this map's <tt>entrySet()</tt> iterator.
     * The <tt>size</tt> method delegates to this map's <tt>size</tt>
     * method and the <tt>contains</tt> method delegates to this map's
     * <tt>containsValue</tt> method.
     *
     * <p>The collection is created the first time this method is called, and
     * returned in response to all subsequent calls.  No synchronization is
     * performed, so there is a slight chance that multiple calls to this
     * method will not all return the same collection.
     */
    public Collection<V> values() {
	if (values == null) {
	    values = new AbstractCollection<V>() {
		public Iterator<V> iterator() {
		    return new Iterator<V>() {
			private Iterator<Entry<K,V>> i = entrySet().iterator();

			public boolean hasNext() {
			    return i.hasNext();
			}

			public V next() {
			    return i.next().getValue();
			}

			public void remove() {
			    i.remove();
			}
                    };
                }

		public int size() {
		    return AbstractMap.this.size();		//调用AbstractMap的size()方法
		}

		public boolean contains(Object v) {
		    return AbstractMap.this.containsValue(v);
		}
	    };
	}
	return values;
    }

    public abstract Set<Entry<K,V>> entrySet();


    // Comparison and hashing

    /**
     * Compares the specified object with this map for equality.  Returns
     * <tt>true</tt> if the given object is also a map and the two maps
     * represent the same mappings.  More formally, two maps <tt>m1</tt> and
     * <tt>m2</tt> represent the same mappings if
     * <tt>m1.entrySet().equals(m2.entrySet())</tt>.  This ensures that the
     * <tt>equals</tt> method works properly across different implementations
     * of the <tt>Map</tt> interface.
     *
     * <p>This implementation first checks if the specified object is this map;
     * if so it returns <tt>true</tt>.  Then, it checks if the specified
     * object is a map whose size is identical to the size of this map; if
     * not, it returns <tt>false</tt>.  If so, it iterates over this map's
     * <tt>entrySet</tt> collection, and checks that the specified map
     * contains each mapping that this map contains.  If the specified map
     * fails to contain such a mapping, <tt>false</tt> is returned.  If the
     * iteration completes, <tt>true</tt> is returned.
     *
     * @param o object to be compared for equality with this map
     * @return <tt>true</tt> if the specified object is equal to this map
     */
    public boolean equals(Object o) {
	if (o == this)
	    return true;

	if (!(o instanceof Map))
	    return false;
	Map<K,V> m = (Map<K,V>) o;
	if (m.size() != size())
	    return false;

        try {
            Iterator<Entry<K,V>> i = entrySet().iterator();
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
		K key = e.getKey();
                V value = e.getValue();
                if (value == null) {		//判断是否包含key，如包含，则判断key是否为空
                    if (!(m.get(key)==null && m.containsKey(key)))
                        return false;
                } else {		//比较两个对象中的value值
                    if (!value.equals(m.get(key)))
                        return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }

	return true;
    }

    /**
     * Returns the hash code value for this map.  The hash code of a map is
     * defined to be the sum of the hash codes of each entry in the map's
     * <tt>entrySet()</tt> view.  This ensures that <tt>m1.equals(m2)</tt>
     * implies that <tt>m1.hashCode()==m2.hashCode()</tt> for any two maps
     * <tt>m1</tt> and <tt>m2</tt>, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * <p>This implementation iterates over <tt>entrySet()</tt>, calling
     * {@link Map.Entry#hashCode hashCode()} on each element (entry) in the
     * set, and adding up the results.
     *
     * @return the hash code value for this map
     * @see Map.Entry#hashCode()
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     */
    public int hashCode() {
	int h = 0;
	Iterator<Entry<K,V>> i = entrySet().iterator();
	while (i.hasNext())
	    h += i.next().hashCode();		//使用每个Entry的hashcode值构成整个Map的hashcode值
	return h;
    }

    /**
     * Returns a string representation of this map.  The string representation
     * consists of a list of key-value mappings in the order returned by the
     * map's <tt>entrySet</tt> view's iterator, enclosed in braces
     * (<tt>"{}"</tt>).  Adjacent mappings are separated by the characters
     * <tt>", "</tt> (comma and space).  Each key-value mapping is rendered as
     * the key followed by an equals sign (<tt>"="</tt>) followed by the
     * associated value.  Keys and values are converted to strings as by
     * {@link String#valueOf(Object)}.
     *
     * @return a string representation of this map
     */
    public String toString() {
	Iterator<Entry<K,V>> i = entrySet().iterator();
	if (! i.hasNext())
	    return "{}";

	StringBuilder sb = new StringBuilder();
	sb.append('{');
	for (;;) {
	    Entry<K,V> e = i.next();
	    K key = e.getKey();
	    V value = e.getValue();
	    sb.append(key   == this ? "(this Map)" : key);		//key==this不是很理解
	    //在Java中，this通常指当前对象，super则指父类的。当您想要引用当前对象的某种东西，比如当前对象的某个方法，
	    //或当前对象的某个成员，您便可以利用this来实现这个目的，当然，this的另一个用途是调用当前对象的另一个构造函数，
	    //这些马上就要讨论。如果您想引用父类的某种东西，则非super莫属。
	    sb.append('=');
	    sb.append(value == this ? "(this Map)" : value);
	    if (! i.hasNext())
		return sb.append('}').toString();
	    sb.append(", ");
	}
    }

    /**
     * Returns a shallow copy of this <tt>AbstractMap</tt> instance: the keys
     * and values themselves are not cloned.
     *
     * @return a shallow copy of this map
     */
    protected Object clone() throws CloneNotSupportedException {	//新建一个map的副本实例，但是key和value则不被复制
        AbstractMap<K,V> result = (AbstractMap<K,V>)super.clone();
        result.keySet = null;
        result.values = null;
        return result;
    }

    /**
     * Utility method for SimpleEntry and SimpleImmutableEntry.
     * Test for equality, checking for nulls.
     */
    private static boolean eq(Object o1, Object o2) {		//判断map中的两个对象是否相同
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    // Implementation Note: SimpleEntry and SimpleImmutableEntry
    // are distinct unrelated classes, even though they share
    // some code. Since you can't add or subtract final-ness
    // of a field in a subclass, they can't share representations,
    // and the amount of duplicated code is too small to warrant
    // exposing a common abstract class.


    /**
     * An Entry maintaining a key and a value.  The value may be
     * changed using the <tt>setValue</tt> method.  This class
     * facilitates the process of building custom map
     * implementations. For example, it may be convenient to return
     * arrays of <tt>SimpleEntry</tt> instances in method
     * <tt>Map.entrySet().toArray</tt>.
     *
     * @since 1.6
     */
    public static class SimpleEntry<K,V>
	implements Entry<K,V>, java.io.Serializable		//简化Enrty的类
    {
	private static final long serialVersionUID = -8499721149061103585L;

	private final K key;
	private V value;

        /**
         * Creates an entry representing a mapping from the specified
         * key to the specified value.
         *
         * @param key the key represented by this entry
         * @param value the value represented by this entry
         */
	public SimpleEntry(K key, V value) {
	    this.key   = key;
            this.value = value;
	}

        /**
         * Creates an entry representing the same mapping as the
         * specified entry.
         *
         * @param entry the entry to copy
         */
	public SimpleEntry(Entry<? extends K, ? extends V> entry) {
	    this.key   = entry.getKey();
            this.value = entry.getValue();
	}

    	/**
	 * Returns the key corresponding to this entry.
	 *
	 * @return the key corresponding to this entry
	 */
	public K getKey() {
	    return key;
	}

    	/**
	 * Returns the value corresponding to this entry.
	 *
	 * @return the value corresponding to this entry
	 */
	public V getValue() {
	    return value;
	}

    	/**
	 * Replaces the value corresponding to this entry with the specified
	 * value.
	 *
	 * @param value new value to be stored in this entry
	 * @return the old value corresponding to the entry
         */
	public V setValue(V value) {
	    V oldValue = this.value;
	    this.value = value;
	    return oldValue;
	}

	/**
	 * Compares the specified object with this entry for equality.
	 * Returns {@code true} if the given object is also a map entry and
	 * the two entries represent the same mapping.	More formally, two
	 * entries {@code e1} and {@code e2} represent the same mapping
	 * if<pre>
	 *   (e1.getKey()==null ?
	 *    e2.getKey()==null :
	 *    e1.getKey().equals(e2.getKey()))
	 *   &amp;&amp;
	 *   (e1.getValue()==null ?
	 *    e2.getValue()==null :
	 *    e1.getValue().equals(e2.getValue()))</pre>
	 * This ensures that the {@code equals} method works properly across
	 * different implementations of the {@code Map.Entry} interface.
	 *
	 * @param o object to be compared for equality with this map entry
	 * @return {@code true} if the specified object is equal to this map
	 *	   entry
	 * @see    #hashCode
	 */
	public boolean equals(Object o) {
	    if (!(o instanceof Map.Entry))
		return false;
	    Map.Entry e = (Map.Entry)o;
	    return eq(key, e.getKey()) && eq(value, e.getValue());
	}

	/**
	 * Returns the hash code value for this map entry.  The hash code
	 * of a map entry {@code e} is defined to be: <pre>
	 *   (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
	 *   (e.getValue()==null ? 0 : e.getValue().hashCode())</pre>
	 * This ensures that {@code e1.equals(e2)} implies that
	 * {@code e1.hashCode()==e2.hashCode()} for any two Entries
	 * {@code e1} and {@code e2}, as required by the general
	 * contract of {@link Object#hashCode}.
	 *
	 * @return the hash code value for this map entry
	 * @see    #equals
	 */
	public int hashCode() {		//对于每个entry来说，求hashcode时使用key和value
	    return (key   == null ? 0 :   key.hashCode()) ^
		   (value == null ? 0 : value.hashCode());
	}

        /**
         * Returns a String representation of this map entry.  This
         * implementation returns the string representation of this
         * entry's key followed by the equals character ("<tt>=</tt>")
         * followed by the string representation of this entry's value.
         *
         * @return a String representation of this map entry
         */
	public String toString() {
	    return key + "=" + value;
	}

    }

    /**
     * An Entry maintaining an immutable key and value.  This class
     * does not support method <tt>setValue</tt>.  This class may be
     * convenient in methods that return thread-safe snapshots of
     * key-value mappings.
     *
     * @since 1.6
     */
    public static class SimpleImmutableEntry<K,V>
	implements Entry<K,V>, java.io.Serializable	//单一不变的Enrty,该类不支持value的改写，相对比较安全
    {
	private static final long serialVersionUID = 7138329143949025153L;

	private final K key;
	private final V value;

        /**
         * Creates an entry representing a mapping from the specified
         * key to the specified value.
         *
         * @param key the key represented by this entry
         * @param value the value represented by this entry
         */
	public SimpleImmutableEntry(K key, V value) {
	    this.key   = key;
            this.value = value;
	}

        /**
         * Creates an entry representing the same mapping as the
         * specified entry.
         *
         * @param entry the entry to copy
         */
	public SimpleImmutableEntry(Entry<? extends K, ? extends V> entry) {
	    this.key   = entry.getKey();
            this.value = entry.getValue();
	}

    	/**
	 * Returns the key corresponding to this entry.
	 *
	 * @return the key corresponding to this entry
	 */
	public K getKey() {
	    return key;
	}

    	/**
	 * Returns the value corresponding to this entry.
	 *
	 * @return the value corresponding to this entry
	 */
	public V getValue() {
	    return value;
	}

    	/**
	 * Replaces the value corresponding to this entry with the specified
	 * value (optional operation).  This implementation simply throws
         * <tt>UnsupportedOperationException</tt>, as this class implements
         * an <i>immutable</i> map entry.
	 *
	 * @param value new value to be stored in this entry
	 * @return (Does not return)
	 * @throws UnsupportedOperationException always
         */
	public V setValue(V value) {
            throw new UnsupportedOperationException();
        }

	/**
	 * Compares the specified object with this entry for equality.
	 * Returns {@code true} if the given object is also a map entry and
	 * the two entries represent the same mapping.	More formally, two
	 * entries {@code e1} and {@code e2} represent the same mapping
	 * if<pre>
	 *   (e1.getKey()==null ?
	 *    e2.getKey()==null :
	 *    e1.getKey().equals(e2.getKey()))
	 *   &amp;&amp;
	 *   (e1.getValue()==null ?
	 *    e2.getValue()==null :
	 *    e1.getValue().equals(e2.getValue()))</pre>
	 * This ensures that the {@code equals} method works properly across
	 * different implementations of the {@code Map.Entry} interface.
	 *
	 * @param o object to be compared for equality with this map entry
	 * @return {@code true} if the specified object is equal to this map
	 *	   entry
	 * @see    #hashCode
	 */
	public boolean equals(Object o) {
	    if (!(o instanceof Map.Entry))
		return false;
	    Map.Entry e = (Map.Entry)o;
	    return eq(key, e.getKey()) && eq(value, e.getValue());
	}

	/**
	 * Returns the hash code value for this map entry.  The hash code
	 * of a map entry {@code e} is defined to be: <pre>
	 *   (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
	 *   (e.getValue()==null ? 0 : e.getValue().hashCode())</pre>
	 * This ensures that {@code e1.equals(e2)} implies that
	 * {@code e1.hashCode()==e2.hashCode()} for any two Entries
	 * {@code e1} and {@code e2}, as required by the general
	 * contract of {@link Object#hashCode}.
	 *
	 * @return the hash code value for this map entry
	 * @see    #equals
	 */
	public int hashCode() {
	    return (key   == null ? 0 :   key.hashCode()) ^
		   (value == null ? 0 : value.hashCode());
	}

        /**
         * Returns a String representation of this map entry.  This
         * implementation returns the string representation of this
         * entry's key followed by the equals character ("<tt>=</tt>")
         * followed by the string representation of this entry's value.
         *
         * @return a String representation of this map entry
         */
	public String toString() {
	    return key + "=" + value;
	}

    }

}
