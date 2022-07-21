package otus;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class DIYarrayList<T> implements List {

    private final int INIT_SIZE = 8;
    private final int CUT_RATE = 4;
    private Object[] list = new Object[INIT_SIZE];
    private int size = 0;

    // Utility function for resize current list to add new objects or when we remove an object
    // to save extra space
    private final void resize(int newLen) {
        Object[] newList = new Object[newLen];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    // Return an amount of object in collection rn
    @Override
    public int size() {
        return size;
    }

    // Return True if collection is empty and False if not.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Return True if element contains in collection and False if not
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; ++i)
        {
            if (list[i] == o) {
                return true;
            }
        }
        return false;
    }

    // Implements iterator from interface 'Iterable'
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int curIndex = 0;

            @Override
            public boolean hasNext() {
                return curIndex < size && list[curIndex] != null;
            }

            @Override
            public T next() {
                return (T)list[curIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    // Return this collection as array with type Object
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(list, size);
    }

    // Add a new object to end of collection, return true if OK, and false if something went wrong
    @Override
    public boolean add(Object o) {
        try {
            if (size == list.length - 1) {
                this.resize(list.length * 2);
            }
            list[size] = o;
            size = size + 1;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void print() {
        for (int i = 0; i < list.length; ++i) {
            if (list[i] == null) {
                break;
            }
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    // Remove an object from collection, return true if OK, and false if something went wrong
    @Override
    public boolean remove(Object o) {
        try {
            int ind = -1;
            for (int i = 0; i < size; ++i) {
                if (list[i] == o) {
                    ind = i;
                }
            }
            if (ind == -1) {
                return false;
            }
            for (int i = ind; i < size; ++i) {
                list[i] = list[i + 1];
                list[size] = null;
                --size;
                if (list.length > INIT_SIZE && size < list.length / CUT_RATE) {
                    this.resize(list.length / 2);
                }
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    // Add a collection of elements with same type to current ArrayList
    // To end of List
    // using add(Object e)
    @Override
    public boolean addAll(Collection c) {
        if (c.getClass() == this.getClass()) {
            Iterator<T> it = c.iterator();
            while (it.hasNext()) {
                Object elementToAdd = it.next();
                this.add(elementToAdd);
            }
            return true;
        }
        else {
            return false;
        }
    }

    // Add a collection of elements with same type to current ArrayList
    // From index what we have in function vars
    // using add(Integer ind, Object e)
    // RU: Халтурно, можно доработать и сделать эту операцию
    // в разы быстрее.
    @Override
    public boolean addAll(int index, Collection c) {
        if (c.getClass() == this.getClass()) {
            Iterator<T> it = c.iterator();
            while (size + c.size() == list.length - 1) {
                this.resize(list.length * 2);
            }
            int i = index;
            while (it.hasNext()) {
                Object elementToAdd = it.next();
                this.add(i, elementToAdd);
                ++i;
            }
            return true;
        }
        else {
            return false;
        }
    }

    // Clear collection
    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            list[i] = null;
        }
        size = 0;
        list = new Object[INIT_SIZE];
    }

    // Return an element from collection by index
    @Override
    public Object get(int index) {
        if (index > 0 && index < list.length) {
            return null;
        }
        else {
            return list[index];
        }
    }

    // Set a new value for element in collection by index
    @Override
    public Object set(int index, Object element) {
        if (index > 0 && index < list.length) {
            return null;
        }
        else {
            Object toReturn = list[index];
            list[index] = element;
            return toReturn;
        }
    }

    // Add a new object to index what requested in collection, True - OK, False - Something went wrong
    @Override
    public void add(int index, Object element) {
        try {
            if (size == list.length - 1) {
                this.resize(list.length * 2);
            }
            for (int i = size - 1; i > index; ++i) {
                list[i] = list[i - 1];
            }
            list[index] = element;
        }
        catch (Exception e) {
            throw e;
        }
    }

    // Remove the object from collection by index
    @Override
    public Object remove(int index) {
        try {
            Object objToReturn = list[index];
            this.remove(objToReturn);
            return objToReturn;
        }
        catch (Exception e) {
            return e;
        }
    }

    // Return the first left index of requested object
    // Return index if all OK
    // Return -1 if something went wrong
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i)
        {
            if (list[i] == o) {
                return i;
            }
        }
        return -1;
    }

    // Return the last right index of requested object
    // Return index if all OK
    // Return -1 if something went wrong
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i)
        {
            if (list[i] == o) {
                return i;
            }
        }
        return -1;
    }

    // ListIterator realisation from the beginning
    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    // ListIterator realisation by index
    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    // Return a sublist of current list from index to other index
    // Return null if something went wrong
    @Override
    public List subList(int fromIndex, int toIndex) {
        DIYarrayList<T> listToReturn = new DIYarrayList<T>();
        try {
            for (int i = fromIndex; i < toIndex; ++i) {
                listToReturn.add(list[i]);
            }
            return listToReturn;
        }
        catch (Exception e) {
            return null;
        }
    }

    // Removing all objects from current collection what not in requested collection
    // True - all OK
    // False - something went wrong
    @Override
    public boolean retainAll(Collection c) {
        try {
            for (int i = 0; i < size; ++i) {
                if (!c.contains(list[i])) {
                    this.remove(list[i]);
                }
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    // Removing all objects from requested collection in current collection
    // True - all OK
    // False - something went wrong
    @Override
    public boolean removeAll(Collection c) {
        try {
            Iterator<T> it = c.iterator();
            while (it.hasNext()) {
                this.remove(it.next());
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    // Check if all group of objects contains in this collection
    // True - all contains
    // False - some objects does not contain in this collection
    @Override
    public boolean containsAll(Collection c) {
        Iterator<T> it = c.iterator();
        while (it.hasNext()) {
            if (!this.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    // Return this collection as array with type what requested
    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(list, size, a.getClass());
        }
        System.arraycopy(list, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
}
