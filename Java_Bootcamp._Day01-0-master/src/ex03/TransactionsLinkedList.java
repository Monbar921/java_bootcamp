package ex03;


import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(Transaction transaction) {
        Node last = tail;
        tail = new Node(last, null, transaction);
        if (last == null) {
            head = tail;
        } else {
            last.next = tail;
        }
        ++size;
    }

    @Override
    public void remove(UUID id) {
        Node first = head;
        boolean isFind = false;

        while (first != null) {
            Transaction transaction = first.elem;
            if (transaction.getId().equals(id)){
                isFind=  true;
                Node prev = first.prev;
                Node next = first.next;
                first.prev = null;
                first.next = null;
                if(prev == null){
                    head = next;
                }
                if (next == null){
                    tail = prev;
                    if(tail != null){
                        tail.next = null;
                    }
                }
                if(prev != null && next != null){
                    prev.next = next;
                }
                --size;
                break;
            }
            first = first.next;
        }
        if(!isFind){
            throw new TransactionNotFoundException();
        }
    }

    @Override
    public Transaction[] toArray() {
        System.out.println(size);
        Transaction[] transactions = new Transaction[size];
        Node first = head;
        int index = 0;
        while (first != null) {
            transactions[index++] = first.elem;
            first = first.next;
        }
        return transactions;
    }

    private static class Node {
        private  Node prev;
        private Node next;
        private final Transaction elem;

        Node(Node prev, Node next, Transaction elem) {
            this.prev = prev;
            this.next = next;
            this.elem = elem;
        }
    }
}
