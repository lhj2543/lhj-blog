package com.lhj.java.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;

/**
 * @author 17822
 */
public class CollectionTest {

    public static void main(String[] args) {

        Collection list = new ArrayList<String>();
        list.add("a");
        list.add("a");

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("k");
        linkedList.add("a");
        linkedList.addLast("u");
        linkedList.addFirst("sadfa");

        linkedList.get(2);

        String remove = linkedList.remove();
        System.out.println(remove);

        System.out.println(linkedList);

    }
}
