package bettercollection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class BetterSet<T> implements Set<T> {

    public BetterSet(Collection<? extends T> c) {
    }

    public BetterSet() {
    }
    @Override
    public int size() {
        return 99;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return a;
    }

    @Override
    public boolean add(T t) {
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return true;
    }

    @Override
    public void clear() {
    }
}
