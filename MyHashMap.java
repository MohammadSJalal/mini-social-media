import javax.management.openmbean.KeyAlreadyExistsException;


public class MyHashMap <K,V> {
    private HashNode <K,V>[] map ;
    private LinkedList<K> keys ;
    private int size;
    public MyHashMap() {
        map = new HashNode[238328];
        keys = new LinkedList<>();
    }
    public void put(K key, V value) throws KeyAlreadyExistsException {
        String SKey = null;
        if (key instanceof String){
            SKey = (String)key;
        }
        else SKey = key.toString();
        HashNode<K,V> h = new HashNode<K,V>(key,value);
        if (map[myHashFunction(SKey)] == null){
            map[myHashFunction(SKey)] = h;
            keys.append(key);
            display();
            size++;
        }
        else throw new KeyAlreadyExistsException();
    }
    public V get(K key) {
        String SKey;
        if (key instanceof String){
            SKey = (String)key;
        }
        else SKey = key.toString();
        V value = null;
        if (map[myHashFunction(SKey)] != null){
            value = (V)map[myHashFunction(SKey)].value;
        }
        return value;
    }
    public int size() {
        return size;
    }
    public void remove(K key) {
        String SKey;
        if (key instanceof String){
            SKey = (String)key;
        }
        else SKey = key.toString();
        map[myHashFunction(SKey)] = null;
        keys.remove(key);
        display();
        size--;
    }

    /**
     * this function get three first letter of sent string
     * and then it give a number for any of them such
     * 0-9 -> 0-9
     * A-Z -> 10-35
     * a-z -> 36-62
     * so for three first letter we have
     * 26*26*26 = 238328 require space
     * we provide that and implement a system of number in base
     * 62 and use that for code data
     * @param key
     */
    private static Integer myHashFunction(String key) {
        key = key.substring(0,3);
        Integer code = 0;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            int num = 0;
            if (ch >= '0' && ch <= '9') {
                num = ch - '0';
            }
            else if (ch >= 'A' && ch <= 'Z') {
                num = ch - 'A' + 10;
            }
            else if (ch >= 'a' && ch <= 'z') {
                num = ch - 'a' + 36;
            }
            code += ((int)Math.pow(62,key.length() - i - 1))*num;
        }
        return code ;
    }
    public void display() {
        System.out.println("MyHashMap");
        keys.initiateIterate();
        while (keys.getStateOfIterate()) {
            String k = keys.getIterate().toString();
            HashNode<K,V> h = map[myHashFunction(k)];
            System.out.println(h.key+" : "+h.value);
            keys.next();
        }
        System.out.println("----------------------------");
    }
    private static class HashNode <K,V> {
        K key;
        V value;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}