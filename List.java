// lots of missing methods and missing implementations

public interface List<E> {

	abstract boolean equals(SinglyLinkedList<E> otherList);

	abstract void addFirst(E elem);

	abstract void add(E elem);

	abstract E remove(int index);

	abstract boolean remove(E o);

	abstract E get(int index);

	abstract int size();

	abstract boolean isEmpty();
}
