import java.util.HashMap;
import java.util.Map;

/**
 * This code is distributed under Apache License 2.0.
 * 
 * @author Sain Technology Solutions
 *
 */
public class LRUCache<K, V> {

	private final int capacity;

	private Map<K, Node<K, V>> cacheElements = new HashMap<>();
	private Node<K, V> head;
	private Node<K, V> tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Removes the element at tail from Doubly Linked List as well as
	 * cacheElements map.
	 */
	private void removeTail() {
		cacheElements.remove(tail.key);

		tail = tail.previous;
		tail.next = null;
	}

	/**
	 * Moves the input node to head of the doubly linked list.
	 * 
	 * @param node
	 *            Node to be moved
	 */
	private void moveToHead(Node<K, V> node) {
		// If node is already at head, do nothing and simply return
		if (node == head) {
			return;
		}

		// remove the node from LinkedList
		node.previous.next = node.next;
		if (node.next != null) {
			node.next.previous = node.previous;
		} else {
			tail = node.previous;
		}

		// put the node at head
		putAsHead(node);
	}

	/**
	 * Puts the input node at head of the doubly linked list.
	 * 
	 * @param node
	 *            Node to be put on head
	 */
	private void putAsHead(Node<K, V> node) {
		node.next = head;
		node.previous = null;

		if (head != null) {
			head.previous = node;
		}

		head = node;

		if (tail == null) {
			tail = head;
		}
	}

	/**
	 * Returns the value corresponding to input key from Cache Map. It also
	 * moves this element to head of doubly linked list since it was most
	 * recently accessed.
	 * 
	 * @param key
	 *            Key for the element whose value needs to be returned
	 * @return Value of the element with input key or null if no such element
	 *         exists
	 */
	public V get(K key) {

		if (cacheElements.containsKey(key)) {
			final Node<K, V> n = cacheElements.get(key);

			moveToHead(n);

			return n.value;
		}

		return null;
	}

	/**
	 * Put the element with input key and value in the cache. If the element
	 * already exits, it updates its value. This method also removes the least
	 * recently accessed element if the cache size has reached its capacity.
	 * 
	 * @param key
	 *            Key of the element
	 * @param value
	 *            Value of the element
	 */
	public void put(K key, V value) {
		if (cacheElements.containsKey(key)) {
			final Node<K, V> old = cacheElements.get(key);
			old.value = value;

			moveToHead(old);
		} else {
			final Node<K, V> created = new Node<>(key, value);

			if (cacheElements.size() >= capacity) {
				removeTail();
				putAsHead(created);
			} else {
				putAsHead(created);
			}

			cacheElements.put(key, created);
		}
	}

	/**
	 * Implementation of a Node of a Doubly Linked List.
	 * 
	 * @author Sain Technology Solutions
	 *
	 * @param <K>
	 * @param <V>
	 */
	private static class Node<K, V> {
		K key;
		V value;
		Node<K, V> next;
		Node<K, V> previous;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + "]";
		}

	}

	@Override
	public String toString() {
		return cacheElements.toString();
	}

	/**
	 * Entry point for testing LRU Cache.
	 */
	public static void main(String[] args) {
		// Create the cache with capacity 2
		LRUCache<Integer, Integer> cache = new LRUCache<>(2);

		cache.put(2, 1); // Will add an element with key as 2 and value as 1
		cache.put(3, 2); // Will add an element with key as 3 and value as 2

		// Will add an element with key as 4 and value as 3. Also will remove
		// the element with key 2 as it was added least recently and cache can
		// just have two elements at a time
		cache.put(4, 3);

		// Since element with key 2 was removed, it will return null
		System.out.println(cache.get(2));

		// It will return value 2 and move the element with key 3 to the head.
		// After this point, element with key 4 will be least recently accessed
		System.out.println(cache.get(3));

		// Will add an element with key as 5 and value as 4. Also will remove
		// the element with key 4 as it was accessed least recently and cache
		// can just have two elements at a time
		cache.put(5, 4);

		// Since element with key 2 was removed, it will return null
		System.out.println(cache.get(4));
	}
}
