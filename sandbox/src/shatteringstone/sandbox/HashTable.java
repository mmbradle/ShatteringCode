package shatteringstone.sandbox;


public class HashTable {
    private final static int TABLE_SIZE = 128;
    LinkedHashEntry[] table;

    public HashTable(){
        table=new LinkedHashEntry[TABLE_SIZE];
        for(int i=0; i<table.length; ++i)
            table[i]=null;
    }

    private int hashFunction(int key){
        return key%TABLE_SIZE;
    }

    public int get(int key) {
        int hash = hashFunction(key);
        if (table[hash]==null) return -1;
        LinkedHashEntry curr = table[hash];
        while (curr.getKey()!=key){
            curr=curr.getNext();
            if(curr==null) return -1;
        }
        return curr.getValue();
    }

    public void put(int key, int value) {
        int hash = hashFunction(key);
        if (table[hash]==null) table[hash] = new LinkedHashEntry(key, value);
        else {
            LinkedHashEntry curr = table[hash];
            while(curr.getNext()!=null){
                if (curr.getKey() == key){
                    curr.setValue(value);
                    return;
                }
            }
            curr.setNext(new LinkedHashEntry(key, value));
        }
    }

    public boolean remove(int key){
        int hash = hashFunction(key);
        if(table[hash]==null) return false;
        LinkedHashEntry curr = table[hash];
        LinkedHashEntry last = null;
        while (curr.getKey()!=key){
            last=curr;
            curr=curr.getNext();
            if (curr==null) return false;
        }
        if(last==null){
            table[hash]=curr.getNext();
        }
        else{
            last.setNext(curr.getNext());
        }
        return true;
    }
}


class LinkedHashEntry {
    private int key;
    private int value;
    private LinkedHashEntry next;

    LinkedHashEntry(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public LinkedHashEntry getNext() {
        return next;
    }

    public void setNext(LinkedHashEntry next) {
        this.next = next;
    }
}