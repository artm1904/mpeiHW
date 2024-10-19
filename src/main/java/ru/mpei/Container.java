package ru.mpei;

/**Данный класс реализует узел, который содержит payload (массив данных по умолчанию 5)
 * и ссылки на правый, левый узел.
 * @param <T>
 */

public class Container<T> {

	private T[] data;
	private T prev;
	private T next;
	private int containerSizeMax = 5;



	/**Т.к в методах идет работа со свободными ячейками слева (справа), то нужна переменная
	 * Напрмиер, добавляем элемен и при каждом движении увеличивая index.
	 * Если containerSizeMax - index ==0, то значит нужно создать новый узел.
	 */
	private int index;


	public Container(T next, T prev) {
		this.next = next;
		this.prev = prev;
		this.data = (T[]) new Object[containerSizeMax];
		this.index = 0;

	}

	public T getNext() {
		return next;
	}

	public void setNext(T next) {
		this.next = next;
	}

	public T getPrev() {
		return prev;
	}

	public void setPrev(T prev) {
		this.prev = prev;
	}

	public T[] getData() {
		return data;
	}

	public void setData(int i, T set) {
		data[i] = set;

	}

	public int getContainerSizeMax() {
		return containerSizeMax;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


}
