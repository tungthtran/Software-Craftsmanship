package defensive;

import bettercollection.BetterSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BarricadeBetterSet<T> implements Set<T> {

    private BetterSet betterSet;
    private static Logger logger = Logger.getLogger(BarricadeBetterSet.class.getName());

    public BarricadeBetterSet(Set<T> betterSet) {
        this.betterSet = new BetterSet(betterSet);
    }

    public BarricadeBetterSet() {
        this.betterSet = new BetterSet<T>();
    }

    @Override
    public int size() {
        int checkedSize = betterSet.size();
        if (checkedSize < 0) {
            logger.log(Level.WARNING, "Method is returning a negative value");
            return 0;
        }
        else {
            return checkedSize;
        }
    }

    @Override
    public boolean isEmpty() {
        boolean checkedValue = betterSet.isEmpty();
        if (checkedValue && betterSet.size() > 0) {
            logger.log(Level.WARNING, "The set should not be empty");
            checkedValue = false;
        }
        return checkedValue;
    }

    @Override
    public boolean contains(Object o) {
        Objects.requireNonNull(o);
        boolean checkedValue = betterSet.contains(o);
        // if contains say false but there is that element in the set
        if (betterSet.stream().anyMatch(c -> c == o) && !checkedValue) {
            logger.log(Level.WARNING, "The method contains the element but returned false");
            checkedValue = true;
        }
        // if contains say true but there is no such element
        else if (betterSet.stream().allMatch(c -> c != o) && checkedValue) {
            logger.log(Level.WARNING, "The method does not contain the element but returned true");
            checkedValue = false;
        }
        return checkedValue;
    }

    @Override
    public Iterator<T> iterator() {
        return betterSet.iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] checkedArray = betterSet.toArray();
        // if the array is null, assign the value to an array
        if (checkedArray == null) {
            checkedArray = new Object[1];
        }
        return checkedArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        Objects.requireNonNull(a);
        Object[] checkedArray = betterSet.toArray(a);
        // utilize the size of the array, without extra or missing spaces
        if (a.length != betterSet.size()) {
            checkedArray = betterSet.toArray(new Object[betterSet.size()]);
        }
            return (T1[]) checkedArray;
    }

    @Override
    public boolean add(T t) {
        Objects.requireNonNull(t);
        boolean wasContained = betterSet.contains(t);
        boolean checkedValue = betterSet.add(t);
        // if the method already contains t but method still returns true
        // or
        // the method added the elements but the set still does not contain
        if ((checkedValue && wasContained) || (checkedValue && !betterSet.contains(t))) {
            logger.log(Level.WARNING, "The method is returning the opposite value");
            checkedValue = false;
        }
        return checkedValue;
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        boolean wasContained = betterSet.contains(o);
        boolean checkedValue = betterSet.remove(o);
        // if the set does not originally contain the element but remove still returns true
        if (!wasContained && checkedValue) {
            logger.log(Level.WARNING, "The method can't have removed the object");
            checkedValue = false;
        }
        // if the set originally contains the element but remove still returns false
        else if (wasContained && !checkedValue) {
            logger.log(Level.WARNING, "The method should have but hasn't removed the object");
            betterSet = (BetterSet)betterSet.stream().filter(c -> c != o).collect(Collectors.toSet());
            checkedValue = true;
        }
        return checkedValue;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        c.stream().forEach(e -> Objects.requireNonNull(e));
        boolean checkedValue = betterSet.containsAll(c);
        // if it says containing all but we find an element in c that the set does not contain
        if (checkedValue && c.stream().anyMatch(e -> !betterSet.contains(e))) {
            logger.log(Level.WARNING, "The set does not contain whole collection c");
            checkedValue = false;
        }
        // if it says not containing all but all the elements in c are in the set
        else if (!checkedValue && c.stream().allMatch(e -> betterSet.contains(e))) {
            logger.log(Level.WARNING, "The set should contain whole collection c");
            checkedValue = true;
        }
        return checkedValue;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Objects.requireNonNull(c);
        c.stream().forEach(e -> Objects.requireNonNull(e));
        boolean checkedValue = betterSet.addAll(c);
        // if the method has added all the elements but the set does not contain all the element
        if (checkedValue && !betterSet.containsAll(c)) {
            logger.log(Level.WARNING, "The set did not add all the elements");
            checkedValue = false;
        }
        return checkedValue;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        c.stream().forEach(e -> Objects.requireNonNull(e));
        int originalSize = betterSet.size();
        boolean checkedValue = betterSet.retainAll(c);
        // the set does not change so the size should be the same
        if (!checkedValue && originalSize != betterSet.size()) {
            logger.log(Level.WARNING, "The set changed as a result of the method invocation");
            checkedValue = true;
        }
        // the set changes so the size should be different
        else if (checkedValue && originalSize == betterSet.size()) {
            logger.log(Level.WARNING, "The set did not change as a result of the method invocation");
            checkedValue = false;
        }
        return checkedValue;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        c.stream().forEach(e -> Objects.requireNonNull(e));
        int originalSize = betterSet.size();
        boolean checkedValue = betterSet.removeAll(c);
        // if removeAll is true but there is still element in c that the set containing
        if (checkedValue && c.stream().anyMatch(e -> betterSet.contains(e))) {
            logger.log(Level.WARNING, "The method did not remove all collection from the set");
            checkedValue = false;
        }
        // if removeAll is false but the set is modified
        else if (!checkedValue && originalSize != betterSet.size()) {
            logger.log(Level.WARNING, "The method is modified but returned the opposite value");
            checkedValue = true;
        }
        return checkedValue;
    }

    @Override
    public void clear() {
        betterSet.clear();
        if (betterSet.size() > 0) {
            logger.log(Level.WARNING, "The method hasn't cleared all the element");
            betterSet.removeAll(betterSet);
        }

    }
}
