package xask00.programming.caching.lru;

public class LRUCacheDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCache<Integer> lru = new LRUCache<>(4);
		lru.put(1, 10);
		lru.put(2, 20);
		lru.put(3, 30);
		lru.put(4, 40);
		System.out.println(lru);
		lru.get(3);
		System.out.println(lru);
		lru.put(2, 200);
		System.out.println(lru);
		lru.put(5, 50);
		System.out.println(lru);
		lru.put(7, 70);
		System.out.println(lru);
	}

}
