
public class LinearHash<T> {

	public enum Status {
		empty, occupied, deleted
	};

	private int maxSize;
	private int size;
	private int c;
	private int current;
	private int[] keys;
	private int hkey;
	private Status[] statusT;
	private T[] data;
	private int lastIndex;
	

	public LinearHash(int maxSize, int c) {

		this.maxSize = maxSize;
		this.c = c;
		size = 0;
		current = -1;
		hkey = 0;
		lastIndex = maxSize - 1;
		keys = new int[maxSize];
		statusT = new Status[maxSize];
		data = (T[]) new Object[maxSize];

		// Initialize all cells to empty
		for (int i = 0; i < maxSize; i++) {
			statusT[i] = Status.empty;
		}
	}

	public int size() {
		return size;
	}

	public boolean full() {
		return size == maxSize;
	}

	public T retrieve() {
		return data[current];
	}

	public void update(T val) {
		data[current] = val;
	}

	public void delete() {
		statusT[current] = Status.deleted;
		size--;
	}
	
	public int hash(int key){
		return key%maxSize; 
	}

	public int insert(int key, T val) {
		int probes = 1;
		hkey = hash(key);
		
		while(statusT[hkey] == Status.occupied){
			hkey += c;
			probes += c;
			
			if(hkey == lastIndex){
				hkey = 0;
			}
		}
		
		data[hkey] = val;
		statusT[hkey] = Status.occupied;
		keys[hkey] = key;
		size++;
		
		return probes;
	}

	public boolean find(int key) {
		hkey = hash(key);
		if(keys[hkey] == key){
			current = hkey;
			return true;
		}else{
			return false;
		}

	}
}
