/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Arrays;

/**
 *W
 * @author hp
 */
public class ResizabbleArrayBag< T> implements BagInterface<T> {

    private  T bag[];
    private int numberOfItems;
    private static final int DEFAULT_CAPACITY = 50;
    private boolean integrityOK;
    private static final int MAX_Capacity = 100;
    private int numberOfEntries;

    public ResizabbleArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    public ResizabbleArrayBag(int requiredCapacity) {
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
        if (isFull()) {
            duplicateCapacity();
        } else {
            bag[numberOfItems] = item;
            numberOfItems++;
        }
        return true;
    }

    private void duplicateCapacity() {
        int Capacity = 2 * bag.length;
        if (Capacity > MAX_Capacity) {
            throw new IllegalStateException("ERROR");
        }
        bag = Arrays.copyOf(bag, Capacity);

    }
    
    
    
    //q2
 public T replec(T item, T newItem) {
        checkIntegrity();
        T result = null;
        if (!isEmpty()) {
            int indItem = getIndexOf(item); //help method
            result = bag[indItem];
            bag[indItem] = newItem;
        }
        return result;}
 
 
 
 
 //q 3
  private boolean tooBig() {
        return (bag.length > 20 && (numberOfItems < (bag.length / 2)));
    }

    private void reduceArray() {
        bag = Arrays.copyOf(bag, (3 * bag.length) / 4);
    }
    
    
    public T removee22() {
        T result = removeItem(numberOfItems - 1);
        if (tooBig()) {
            reduceArray();
        }
        return result;
    }

    public boolean removee22(T item) {
        int index = getIndexOf(item);
        T result = removeItem(index);
        if (tooBig()) {
            reduceArray();
        }
        return result.equals(result);
    }
 
    public BagInterface<T> union(BagInterface<T> anotherBag)
{
 BagInterface<T> unionBag = new ResizabbleArrayBag<T>();
 ResizabbleArrayBag<T> otherBag = (ResizabbleArrayBag<T>)anotherBag;
 int index;
 // add entries from this bag to the new bag
 for (index = 0; index < numberOfItems; index++)
 unionBag.add(bag[index]);
 // add entries from the second bag to the new bag
 for (index = 0; index < otherBag.getCurrentSize(); index++)
 unionBag.add(otherBag.bag[index]);
 return unionBag;
} // end union
    public BagInterface<T> intersection(BagInterface<T> anotherBag)
{
 // The count of an item in the intersection is
 // the smaller of the count in each bag
 BagInterface<T> intersectionBag = new ResizabbleArrayBag<T>();
 ResizabbleArrayBag<T> otherBag = (ResizabbleArrayBag<T>)anotherBag;
 BagInterface<T> copyOfAnotherBag = new ResizabbleArrayBag<T>();
 int index;
 // copy the second bag
  for (index = 0; index < otherBag.numberOfEntries; index++)
 {
 copyOfAnotherBag.add(otherBag.bag[index]);
 } // end for
 // add to intersectionBag each item in this bag that matches an item in anotherBag;
 // once matched, remove it from the second bag

 for (index = 0; index < getCurrentSize(); index++)
 {
 if (copyOfAnotherBag.contains(bag[index]))
 {
 intersectionBag.add(bag[index]);
 copyOfAnotherBag.remove(bag[index]);
 } // end if
 } // end for
 return intersectionBag;
} // end intersection
    
    public BagInterface<T> difference(BagInterface<T> anotherBag)
{
 // The count of an item in the difference is the difference of the counts
 // in the two bags.
 BagInterface<T> differenceBag = new ResizabbleArrayBag<T>();
 ResizabbleArrayBag<T> otherBag = (ResizabbleArrayBag<T>)anotherBag;
 int index;
        int numberOfEntries = 0;
 // copy this bag
 for (index = 0; index < numberOfEntries; index++)
 {
 differenceBag.add(bag[index]);
 } // end for
 // remove the ones that are in anotherBag
 for (index = 0; index < otherBag.getCurrentSize(); index++)
 {
 if (differenceBag.contains(otherBag.bag[index]))
 {
 differenceBag.remove(otherBag.bag[index]);
 } // end if
 } // end for
 return differenceBag;
} // end difference
    
    
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
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
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
    public Object[] toArtay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEntry(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
