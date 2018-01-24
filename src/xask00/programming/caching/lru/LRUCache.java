package xask00.programming.caching.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author visna03
 * 
 * Implementation of LRU Cache
 *
 */
public class LRUCache<E> {
	
	// head of the list representing the most recently used item in the cache
	private Node head = null;
	// tail of the list representing the least recently used item in the cache
	private Node last = null;
	private Map<Integer, Node> map = new HashMap<>();
	private int capacity; // cache size
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
	}
	
	private boolean isFull() {
		return this.map.size() == this.capacity;
	}
	
	/**
	 * Retrieve an item from cache based on Key
	 * @param key
	 * @return E
	 */
	public E get(int key) {
		// If node is present in the map
		if (this.map.containsKey(key)) {
			// get the node
			Node node = this.map.get(key);
			// If node is the head of list, no need to do anything
			// If not, move the node to the top and make it head of the list
			if (node != this.head) {
				removeNode(node); // Remove the node from its current position
				addNode(node); // Add the node on top of the list
			}
			return node.value;
		}
		return null;
	}
	
	/**
	 * Add new item in the cache
	 * @param item
	 */
	public void put(int key, E item) {
		Node node;
		// If the key is already present in the cache, update the value
		// and make it most recently used
		if (this.map.containsKey(key)) {
			node = this.map.get(key);
			removeNode(node); // Remove the node from the list
			node.value = item; // Update the value
			addNode(node); // Add the node on top of the list
		}
		// If the key is not present in the cache, add the item in the cache
		// and make it most recently used
		else {
			node = new Node(key, item);
			// Before adding the item in the cache, check if the cache has space to accommodate the new item
			// If not, remove the least recently used item first
			// and then add the new item in the cache
			if (this.isFull()) {
				removeNode(this.last);
			}
			addNode(node);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node curr = this.head;
		while(curr != null) {
			sb.append(curr);
			sb.append((curr.next != null) ? " -> " : "");
			curr = curr.next;
		}
		return sb.toString();
	}
	
	/**
	 * Remove a given node from the List and Map
	 * @param node
	 */
	private void removeNode(Node node) {
		if (node.prev == null) {
			this.head = node.next;
			this.head.prev = null;
			node.next = null;
		} else if (node.next == null) {
			this.last = node.prev;
			this.last.next = null;
			node.prev = null;
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.next = null;
			node.prev = null;
		}
		this.map.remove(node.key);
	}
	
	/**
	 * Add a node on the top of the list and in the map
	 * @param node
	 */
	private void addNode(Node node) {
		if (this.head == null) {
			this.head = node;
			this.last = node;
		} else {
			node.next = this.head;
			this.head.prev = node;
			this.head = node;
		}
		this.map.put(node.key, node);
	}
	
	/**
	 * Node class
	 */
	private class Node {
		int key;
		E value;
		Node prev;
		Node next;
		
		public Node (int key, E val) {
			this.key = key;
			this.value = val;
			this.prev = null;
			this.next = null;
		}
		
		public String toString() {
			return "[" + this.key + ", " + this.value + "]";
		}
	}
}
