package myArrayList;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {

    private Object[] elements;
    private int index;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    /**
     * Expands current ArrayList size
     */
    private void expand() {
        elements = Arrays.copyOf(elements, elements.length + 2);
    }

    /**
     * Get List value by index
     */
    private Object elements(int index) {
        return elements[index];
    }

    /**
     * Method that check is List is empty?
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * Constructor1
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_SIZE];
    }

    /**
     * Constructor2
     */
    public MyArrayList(int length) {
        if (length == 0) {
            this.elements = new Object[DEFAULT_SIZE];
        } else if (length > 0) {
            this.elements = new Object[length];
        } else {
            throw new IllegalArgumentException("Capacity error" + length);
        }
    }

    /**
     * Add new values
     */
    public void add(E val) {
        if (index == elements.length) {
            expand();
        }
        elements[index] = val;
        this.index++;
        this.size++;
    }

    /**
     * Add value by index. Initiates list shift
     */
    public boolean add(E val, int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index value error: " + index);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = val;
        return true;
    }

    /**
     * Replace value by index
     */
    public boolean set(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index : " + index);
        }
        if (index < size) {
            elements[index] = value;
            return true;
        }
        return false;
    }

    /**
     * Get value by index
     */
    public Object get(int indexValue) {
        if (indexValue > size || indexValue < 0) {
            throw new IndexOutOfBoundsException();
        }
        return elements[indexValue];
    }

    /**
     * Search element by index
     */
    public int indexOf(E val) {
        // null value check
        if (val == null) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        }
        for (int i = 0; i < elements.length; i++) {
            if (val.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Print List size
     */
    public int size() {
        return size;
    }

    /**
     * Remove element of List by index
     */
    public boolean remove(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        int val = size - index - 1;
        if (val > 0) {
            System.arraycopy(elements, index + 1, elements, index, val);
            elements[--size] = null;
            //size--;
            return true;
        }
        return false;
    }


    /**
     * His majesty the ITERATOR!!!
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int curr;

            @Override
            public boolean hasNext() {
                return elements.length >= size;
            }

            @Override
            public E next() {
                int i = curr;
                Object[] elementsCopy = elements;
                if (i >= size) {
                    throw new NoSuchElementException();
                }
                if (i > elementsCopy.length) {
                    throw new ConcurrentModificationException();
                }
                curr = i + 1;
                return (E) elementsCopy[i];
            }
        };
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        System.out.println(list.size());

        for (int i = 1; i < 1000; i++) {
            list.add(i);
        }

        System.out.println(list.size());
        System.out.println(list.get(777));
        list.remove(40);
        list.remove(555);
        list.remove(678);
        System.out.println(list.size());
         list.set(646, 1234567);
        System.out.println(list.get(646));
        System.out.println(list.indexOf(8));
        list.add(77777777, 555);
        System.out.println(list.get(555));
        System.out.println(list.size);
    }
}
