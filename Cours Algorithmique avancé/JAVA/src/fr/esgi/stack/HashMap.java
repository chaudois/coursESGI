package fr.esgi.stack;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;


public class HashMap<K,V> {

    class HashMapItem<K, V>{
        public K key;
        public V value;

        HashMapItem(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof HashMapItem){
                HashMapItem<K, V> obj = (HashMapItem<K, V>) o;
                return this.key.equals(obj.key);

            }
            throw new RuntimeException("Not the same object");
        }
    }

    int size = 1024;

    LinkedList<HashMapItem<K, V>>[] buckets = new LinkedList[size];
    Set <K> keySet = new HashSet<K>();

    public V put(K key, V value) {
        int position = getPosition(key);

        HashMapItem<K, V> item = new HashMapItem<>(key, value);
        LinkedList<HashMapItem<K,V> >bucket = buckets[position];

        if (bucket == null) {
            bucket = new LinkedList<>();
            bucket.addLast(item);
            buckets[position] = bucket;
        } else {
            int index = bucket.indexOf(item);
            if (index != -1){
                HashMapItem< K, V> oldItem = bucket.get(index);
                oldItem.value = value;
            } else {
                bucket.addLast(item);
            }
        }
        keySet.add(key);
        return value;
    }

//    public V get (K key){
//        HashMapItem<K, V> item = getItem(key);
//        return item != null ? item.value : null;
//    }
    public boolean containsKey(K key){
        return keySet.contains(key);
    }
    private int getPosition(K key){
        int hash=key.hashCode();
        if(hash<0){
            hash*=-1;

        }
        int position=hash%size;
        return position;
    }
//    private HashMapItem<K,V> getItem(K key){
//        int position=getPosition(key);
//        LinkedList<HashMapItem<K,V>>bucket=buckets[position];
//        if(bucket!=null){
//            HashMapItem<K,V>item=new HashMapItem<>(key,null);
//            int index = bucket.indexOf(item);
//            if(index!=-1){
//
//            }
//        }
//
//    }
}