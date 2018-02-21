package fr.esgi.stack;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapExo {

    private class Person {
        String name;
        String address;
        Integer age;

        Person(String name, String address, Integer age) {
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public String toString() {
            return this.name + " " + this.address + " " + this.age;
        }
    }

    void hashTest() {
        HashMap<String, Person> map = new HashMap<>();

        Person p1 = new Person("Toto", "Paris", 29);
        map.put("Toto", p1);

        Person p2 = new Person("Titi", "Paris - 2", 14);
        map.put("MyAwesomeKey", p2);

        Person p3 = new Person("Tata", "Paris - 11", 67);
        map.put(p3.name, p3);

        if (map.containsKey("Toto")) {
            System.out.println(map.get("Toto").toString());
        } else {
            System.out.println("Toto not found");
        }

        if (map.containsKey("Titi")) {
            System.out.println(map.get("Titi").toString());
        } else {
            System.out.println("Titi not found");
        }

        if (map.containsKey("MyAwesomeKey")) {
            System.out.println(map.get("MyAwesomeKey").toString());
        } else {
            System.out.println("MyAwesomeKey not found");
        }

        for (String key : map.keySet()) {
            System.out.println(map.get(key).toString());
        }
    }

    // toto titi tata toto titi
    // toto - 2
    // titi - 2
    // tata - 1
    public void wordCount(String text) {
        String[] words = text.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            Integer count = 1;
            if (map.containsKey(word)) {
                count += map.get(word);
            }
            map.put(word, count);
        }

        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }


    //obtient un tableau a-b,b-a et aUb
    public void noIdea(int[] a,int[] b){
        ArrayList<Integer> aIb= new ArrayList<>();
        ArrayList<Integer> aMb= new ArrayList<>();
        ArrayList<Integer> bMa= new ArrayList<>();

        HashMap<Integer,Integer> map=new HashMap<>();
        for(Integer elA : a){
            map.put(elA,1);
        }
        for(Integer elemntB : b){
            if(map.containsKey(elemntB)){
                aIb.add(elemntB);
                map.remove(elemntB);
            }else{
                bMa.add(elemntB);
            }
        }
        aMb.addAll(map.keySet());
    }
    public static void main(String [] args) {
        HashMapExo ex = new HashMapExo();
//        ex.hashTest();
        ex.wordCount("toto titi tata toto titi");
    }
}