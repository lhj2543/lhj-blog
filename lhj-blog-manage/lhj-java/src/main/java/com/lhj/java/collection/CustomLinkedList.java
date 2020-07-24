package com.lhj.java.collection;

/**
 * 自定义简单 LinkedList
 * @author 17822
 */
public class CustomLinkedList<E> {


    int size;

    private Node<E> node;

    private Node<E> firstNode;

    public void add(E e){
        Node newNode = new Node(e, null);
        if(this.node !=null){
            this.node.next = newNode;
        }else {
            this.firstNode = newNode;
        }
        this.node = newNode;
        this.size ++ ;
    }

    public void addFirst(E e){
        if(this.firstNode==null){
            this.add(e);
            return;
        }
        Node newNode = new Node(e, this.firstNode);
        this.firstNode = newNode;
        this.size ++ ;
    }

    public  E  remove(){
        if(this.firstNode==null){
            return null;
        }
        E a = this.firstNode.value;
        this.firstNode = this.firstNode.next;
        this.size -- ;
        return a;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        if(size<=0){
            result.append("[]");
            return result.toString();
        }

         Node fn = this.firstNode;
        while (fn != null){
            //System.out.println(fn.value);
            result.append(fn.value + ",");
            fn = fn.next;
        }

        return  result.toString();
    }


    /**
     * 节点
     * @param <E>
     */
    private class Node<E>{

        Node(E value, Node<E> next){
            this.value = value;
            this.next = next;
        }

        public E value;

        public  Node<E> next;

    }

}

class test{

    public static void main(String[] args) {

        CustomLinkedList<String> customLinkedList = new CustomLinkedList<>();
        customLinkedList.add("k");
        customLinkedList.add("t");
        customLinkedList.add("a");
        customLinkedList.add("Y");
        customLinkedList.add("aslkfa");
        customLinkedList.addFirst("第一个");

        String remove = customLinkedList.remove();
        System.out.println(remove);

        //customLinkedList.addFirst("fff");

        System.out.println(customLinkedList.size);

        System.out.println(customLinkedList.toString());

    }

}
