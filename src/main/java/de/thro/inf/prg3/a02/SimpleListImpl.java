package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

    @Override
    public void add(Object o) {
        if (head == null) {
            head = new Element();
            head.next = null;
            head.item = o;
        } else {
            Element temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Element();
            temp.next.item = o;
            temp.next.next = null;
        }
        ++size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleListImpl result = new SimpleListImpl();

        if (head == null) {
            return result;
        }

        Element temp = head;
        while (temp.next != null) {
            if (filter.include(temp.item)) {
                result.add(temp.item);
            }
            temp = temp.next;
        }

        return result;
    }

    @Override
    public java.util.Iterator iterator() {
        return new SimpleIteratorImpl();
    }

    /**
     * 2. implemented Element as static inner class
     */
    private static class Element {
        private Object item;
        private Element next;
    }

    private Element head = null;

    private int size = 0;

    /**
     * Interface Iterator
     */
    public class SimpleIteratorImpl implements Iterator {
        private int counter = 0;

        private Element element = head;

        @Override
        public boolean hasNext() {
            return counter < size && element != null;
        }

        @Override
        public Object next() {
            Element temp = element;
            element = element.next;
            ++counter;
            return temp.item;
        }
    }
}
