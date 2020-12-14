/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author hp
 */
public class ArrayBag < T> implements BagInterface<T> {

    private final T bag[];
    private int numberOfItems;
    private static final int DEFAULT_CAPACITY = 50;
    private boolean integrityOK;
    private static final int MAX_Capacity = 100;

    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBag(int requiredCapacity) {
        integrityOK = false;
        if (requiredCapacity <= MAX_Capacity) {
            @SuppressWarnings("Unchecked")
            T[] temBag = (T[]) new Object[requiredCapacity];
            bag = temBag;
            numberOfItems = 0;
            integrityOK = true;
        } else {
            throw new IllegalStateException("Can not creat the bag");
        }

    }

    @Override
    public boolean add(T item) {
        checkIntegrity();
        boolean result = true;
        if (!isFull()) {
            bag[numberOfItems] = item;
            numberOfItems++;
        } else {
            result = false;
        }
        return result;
    }
    
    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("Data is cprrupt");
        }
    }

    private boolean isFull() {
        return (numberOfItems >= bag.length);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T tempBag[];
        tempBag = (T[]) new Object[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            tempBag[i] = bag[i];
        }
        return tempBag;
    }

    @Override
    public int getCurrentSize() {
        return numberOfItems;
    }

 

    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }
     @Override
    public boolean isEmpty() {
        return (numberOfItems == 0);
    }

    @Override
    public int getFrequencyOf(T item) {
        checkIntegrity();
        int counter = 0;
        for (int i = 0; i < numberOfItems; i++) {
            if (item.equals(bag[i])) {
                counter++;
            }
        }
        return counter;
    }

    private T removeItem(int index) {
        T result = null;
        if (!isEmpty() && index >= 0) {
            result = bag[numberOfItems - 1];
            bag[index] = bag[numberOfItems - 1];
            bag[numberOfItems - 1] = null;
            numberOfItems--;
        }
        return result;
    }

    @Override
    public T remove() {
        checkIntegrity();
        T result = removeItem(numberOfItems - 1);
        return result;
    }

    private int getIndexOf(T item) {
        int index = 0;
        int where = -1;
        boolean found = false;
        while (!found && index < numberOfItems) {
            if (bag[index].equals(item)) {
                found = true;
                where = index;
            } else {
                index++;
            }
        }
        return where;
    }

    @Override
    public boolean remove(T item) {
        checkIntegrity();
        int index = getIndexOf(item);
        T result = removeItem(index);
        return item.equals(result);
    }
@Override
    public boolean contains(T item) {
        checkIntegrity();
        boolean found = false;
        int index = 0;
        while ((index < numberOfItems) && !found) {
            if (bag[index].equals(item)) {
                found = true;
            } else {
                index++;
            }
        }
        return found;
    }

    @Override
    public Object[] toArtay() {
     
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEntry(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}
