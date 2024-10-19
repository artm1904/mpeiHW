package ru.mpei;

import java.util.Collection;
import java.util.Deque;
import java.util.NoSuchElementException;

public class TripletDeque<T> implements Deque<T>, Containerable {

	private static final int ORDER_VOLUME = 1000;

	private Container<T> first;
	private Container<T> last;


	private int length;    //Текущая заполненность очереди
	private int orderVolume;

	public TripletDeque() {
		this(ORDER_VOLUME);
	}

	//Создаем оччередь где первый и последний узел вместе null
	public TripletDeque(int orderVolume) {
		this.orderVolume = orderVolume;
		this.length = 0;
		Container<T> container = new Container<T>(null, null);
		this.first = container;
		this.last = container;
	}

	/**
	 * Добавляет элементь в первый узел, там берется payload и
	 * назнанчем t в массив влевую сторону. Если payload узла заполнен,
	 * то создаем новый узел - он становится первым
	 *
	 */
	@Override
	public void addFirst(T t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (this.length < orderVolume) {

			//Если узел переполнен, то создаем новый
			if (first.getContainerSizeMax() == first.getIndex()) {
				Container<T> cont = new Container<T>((T) first, null);
				this.first.setPrev((T) cont);
				this.first = cont;
			}
			//индексация в массиве с 0, поэтому надо вычитать 1
			first.setData(first.getContainerSizeMax() - first.getIndex() - 1, t);
			//first.setData(first.getIndex(), t);
			first.setIndex(first.getIndex() + 1);
			this.length++;
		} else {
			throw new IllegalStateException();
		}

	}


	/**
	 * Добавляем новые элементы, рост идет вправо с 0 индекса, если в узле payload заполнен, то создаем новый узул
	 * и добавялем ссылку next
	 * @param t the element to be added.
	 */
	@Override
	public void addLast(T t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (this.length < orderVolume) {

			//Если переполнен, то создаем новый узел
			if (last.getContainerSizeMax() == last.getIndex()) {
				Container<T> cont = new Container<T>(null, (T) last);
				this.last.setNext((T) cont);
				this.last = cont;
			}
			last.setData(last.getIndex(), t);
			last.setIndex(last.getIndex() + 1);
			this.length++;
		} else {
			throw new IllegalStateException();
		}
	}

	/**
	 * По сути метод такой же как и  addFirst только возвращает буль, а  не бросает исключение
	 *
	 * @param t the element to add
	 * @return
	 */

	@Override
	public boolean offerFirst(T t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (this.length < orderVolume) {

			//Если узел переполнен, то создаем новый
			if (first.getContainerSizeMax() == first.getIndex()) {
				Container<T> cont = new Container<T>((T) first, null);
				this.first.setPrev((T) cont);
				this.first = cont;
			}
			//индексация в массиве с 0, поэтому надо вычитать 1
			first.setData(first.getContainerSizeMax() - first.getIndex() - 1, t);
			//first.setData(first.getIndex(), t);
			first.setIndex(first.getIndex() + 1);
			this.length++;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean offerLast(T t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (this.length < orderVolume) {

			//Если переполнен, то создаем новый узел
			if (last.getContainerSizeMax() == last.getIndex()) {
				Container<T> cont = new Container<T>(null, (T) last);
				this.last.setNext((T) cont);
				this.last = cont;
			}
			last.setData(last.getIndex(), t);
			last.setIndex(last.getIndex() + 1);
			this.length++;
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Возвращает и удаляет первый элемент очереди (самый левый)
	 * Если индекс payload == 0 то нужно перевязать ссылки на контейнеры и убрать пустой
	 *
	 * Retrieves and removes the first element of this deque.  This method
	 * differs from {@link #pollFirst pollFirst} only in that it throws an
	 * exception if this deque is empty.
	 *
	 * @return the head of this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	@Override
	public T removeFirst() {


		// Вначале могут быть дырки, т.к. рост влево, ищем первый !=null
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (first.getData()[i] != null) {
				T retVal = first.getData()[i];
				//Т.к добавление элемента идет через (getContainerSizeMax - getIndex), то при удалении элемента нужно индекс ументшить
				first.setIndex(first.getIndex() - 1);
				first.setData(first.getContainerSizeMax() - first.getIndex() - 1, null);
				//Если возникла ситуация что мы удалили единственный элемент в payload узла, и у него есть сосед next, то first двигается вправо
				if (first.getIndex() == 0 && first.getNext() != null) {
					first = (Container<T>) first.getNext();
					first.setPrev(null);
				}
				this.length--;
				return retVal;
			}
		}
		//Если очередь пуста, то исключение
		throw new NoSuchElementException();
	}

	@Override
	public T removeLast() {


		for (int i = last.getContainerSizeMax() - 1; i >= 0; i--) {
			if (last.getData()[i] != null) {
				T retVal = last.getData()[i];

//				if (last.getIndex() == last.getContainerSizeMax()){
//					last.setIndex(last.getIndex() - 1);
//				}
//
//				last.setData(last.getIndex(), null);
//				last.setIndex(last.getIndex() - 1);
				if (last.getData()[0] == null) {

					last.setData(last.getIndex(), null);
					last.setIndex(last.getIndex() - 1);

				} else {
					last.setIndex(last.getIndex() - 1);
					last.setData(last.getIndex(), null);
				}


				if (last.getIndex() == 0 && last.getPrev() != null) {
					last = (Container<T>) last.getPrev();
					last.setNext(null);
				}
				this.length--;

				// Если текущая длина очереди ноль, то нужно все ссылки на first и last переривязать на начальные (как сдедалть более элегантано - не придумал)
				if (length == 0) {
					Container<T> container = new Container<T>(null, null);
					this.first = container;
					this.last = container;
				}

				return retVal;
			}

		}
		throw new NoSuchElementException();
	}

	@Override
	public T pollFirst() {

		// Вначале могут быть дырки, т.к. рост влево, ищем первый !=null
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (first.getData()[i] != null) {
				T retVal = first.getData()[i];
				//Т.к добавление элемента идет через (getContainerSizeMax - getIndex), то при удалении элемента нужно индекс ументшить
				first.setIndex(first.getIndex() - 1);
				first.setData(first.getContainerSizeMax() - first.getIndex() - 1, null);
				//Если возникла ситуация что мы удалили единственный элемент в payload узла, и у него есть сосед next, то first двигается вправо
				if (first.getIndex() == 0 && first.getNext() != null) {
					first = (Container<T>) first.getNext();
					first.setPrev(null);
				}
				this.length--;
				return retVal;
			}
		}
		//Если очередь пуста, то null
		return null;
	}

	@Override
	public T pollLast() {
		for (int i = last.getContainerSizeMax() - 1; i >= 0; i--) {
			if (last.getData()[i] != null) {
				T retVal = last.getData()[i];
				last.setIndex(last.getIndex() - 1);
				last.setData(last.getIndex(), null);
				if (last.getIndex() == 0 && last.getPrev() != null) {
					last = (Container<T>) last.getPrev();
					last.setNext(null);
				}
				this.length--;
				return retVal;
			}
		}
		return null;
	}

	/**
	 * Извлекает голову очереди, но не удаляет
	 * This method differs from {@link #peekLast peekFirst} only in that it
	 * throws an exception if this deque is empty.
	 *
	 * @return the head of this deque
	 * @throws NoSuchElementException if this deque is empty
	 *
	 * По сути берем первый узел и идем с 0 индекса пока не встретим данные
	 */
	@Override
	public T getFirst() {
		T[] data = first.getData();
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (data[i] != null) {
//				System.out.println(data[1]) ;
				return data[i];
			}
		}
		throw new NoSuchElementException();
	}

	/**
	 * Извлекает хвост очереди - самый правый элемент
	 * @return
	 */
	@Override
	public T getLast() {
		T[] data = last.getData();
		for (int i = first.getContainerSizeMax() - 1; i >= 0; i--) {
			if (data[i] != null) {
				return data[i];
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public T peekFirst() {
		T[] data = first.getData();
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (data[i] != null) {
//				System.out.println(data[1]) ;
				return data[i];
			}
		}

		return null;
	}

	@Override
	public T peekLast() {
		T[] data = last.getData();
		for (int i = first.getContainerSizeMax() - 1; i >= 0; i--) {
			if (data[i] != null) {
				return data[i];
			}
		}

		return null;
	}

	/**
	 * Removes the first occurrence of the specified element from this deque.
	 * 	 *
	 * @param o element to be removed from this deque, if present
	 * @return {@code true} if an element was removed as a result of this call
	 * @throws ClassCastException if the class of the specified element
	 *         is incompatible with this deque
	 *         ({@linkplain Collection##optional-restrictions optional})
	 * @throws NullPointerException if the specified element is null and this
	 *         deque does not permit null elements
	 *         ({@linkplain Collection##optional-restrictions optional})
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {

		Container<T> cont = first;
		//Нужно для случая когда только один узел
		int i = -1;
		boolean findFirstEl = false;

		if (o == null) {
			throw new NullPointerException();
		}

		while (cont.getNext() != null || i == -1) {
			//Если в первом узле не нашли, то идем в следующий
			if (i != -1) {
				cont = (Container<T>) cont.getNext();
			}

			T[] data = cont.getData();
			//Проходим весь payload узла
			for (i = 0; i < cont.getContainerSizeMax(); i++) {
				//нашли первый такой элемент и сразу уменьшили очередь
				if (!findFirstEl) {
					findFirstEl = data[i].equals(o);
					length--;
				}
				//нашли первое совпадение, то просто смещаем все на 1 влево. Так делаем до последней ячейки
				if (findFirstEl && i < cont.getContainerSizeMax() - 1) {
					data[i] = data[i + 1];
				}
				//Если у нашего контейнера есть следующий, то нужно из него перетащить 0 ячейку, иначе наша последняя будет null
				if (findFirstEl && i == cont.getContainerSizeMax() - 1) {
					if (cont.getNext() != null) {
						data[i] = ((Container<T>) cont.getNext()).getData()[0];
					} else {
						data[i] = null;
					}
				}
			}
		}

		//Берем реальный индекс куда был записан последний элемент
		last.setIndex(last.getIndex() - 1);
		if (last.getIndex() == 0 && last.getPrev() != null) {
			last = (Container<T>) last.getPrev();
			last.setNext(null);
		}

		return findFirstEl;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {

		return true;
	}


	/**
	 *
	 * Метод из коллекции ОЧЕРЕДЬ. Вставляет элемент в хвост очереди (самое правое место)
	 *
	 * @param t the element to add
	 * @return {@code true} (as specified by {@link Collection#add})
	 *
	 * @throws NullPointerException if the specified element is null and this
	 *         deque does not permit null elements
	 * @throws IllegalArgumentException if some property of the specified
	 *         element prevents it from being added to this deque
	 */
	@Override
	public boolean add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (this.length < orderVolume) {

			//Если переполнен, то создаем новый узел
			if (last.getContainerSizeMax() == last.getIndex()) {
				Container<T> cont = new Container<T>(null, (T) last);
				this.last.setNext((T) cont);
				this.last = cont;
			}
			last.setData(last.getIndex(), t);
			last.setIndex(last.getIndex() + 1);
			this.length++;
			return true;
		} else {
			throw new IllegalStateException();
		}
	}


	/**
	 * Метод аналогичен public boolean add(T t), только возвращает true если смогли добавить и false если не смогли
	 *
	 * @param t the element to add
	 * @return
	 */
	@Override
	public boolean offer(T t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (this.length < orderVolume) {

			//Если переполнен, то создаем новый узел
			if (last.getContainerSizeMax() == last.getIndex()) {
				Container<T> cont = new Container<T>(null, (T) last);
				this.last.setNext((T) cont);
				this.last = cont;
			}
			last.setData(last.getIndex(), t);
			last.setIndex(last.getIndex() + 1);
			this.length++;
			return true;
		} else {
			return false;
		}
	}


	/**
	 * удаляет the first element of this deque).
	 * This method differs from {@link #poll() poll()} only in that it
	 * throws an exception if this deque is empty.
	 *
	 * <p>This method is equivalent to {@link #removeFirst()}.
	 *
	 * @return the head of the queue represented by this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	@Override
	public T remove() {

		// Вначале могут быть дырки, т.к. рост влево, ищем первый !=null
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (first.getData()[i] != null) {
				T retVal = first.getData()[i];
				//Т.к добавление элемента идет через (getContainerSizeMax - getIndex), то при удалении элемента нужно индекс ументшить
				first.setIndex(first.getIndex() - 1);
				first.setData(first.getContainerSizeMax() - first.getIndex() - 1, null);
				//Если возникла ситуация что мы удалили единственный элемент в payload узла, и у него есть сосед next, то first двигается вправо
				if (first.getIndex() == 0 && first.getNext() != null) {
					first = (Container<T>) first.getNext();
					first.setPrev(null);
				}
				this.length--;
				return retVal;
			}
		}
		//Если очередь пуста, то исключение
		throw new NoSuchElementException();

	}

	/**
	 * Retrieves and removes the head of the queue represented by this deque
	 * (in other words, the first element of this deque), or returns
	 * {@code null} if this deque is empty.
	 *
	 * <p>This method is equivalent to {@link #pollFirst()}.
	 *
	 * @return the first element of this deque, or {@code null} if
	 *         this deque is empty
	 */
	@Override
	public T poll() {

		// Вначале могут быть дырки, т.к. рост влево, ищем первый !=null
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (first.getData()[i] != null) {
				T retVal = first.getData()[i];
				//Т.к добавление элемента идет через (getContainerSizeMax - getIndex), то при удалении элемента нужно индекс ументшить
				first.setIndex(first.getIndex() - 1);
				first.setData(first.getContainerSizeMax() - first.getIndex() - 1, null);
				//Если возникла ситуация что мы удалили единственный элемент в payload узла, и у него есть сосед next, то first двигается вправо
				if (first.getIndex() == 0 && first.getNext() != null) {
					first = (Container<T>) first.getNext();
					first.setPrev(null);
				}
				this.length--;
				return retVal;
			}
		}

		return null;
	}


	/**
	 *  Извлекает, но не удаляет голову очереди (самый правый элемент)
	 * This method differs from {@link #peek peek} only in that it throws an
	 * exception if this deque is empty.
	 *
	 * <p>This method is equivalent to {@link #getFirst()}.
	 *
	 * @return the head of the queue represented by this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	@Override
	public T element() {
		// Вначале могут быть дырки, т.к. рост влево, ищем первый !=null
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (first.getData()[i] != null) {
				T retVal = first.getData()[i];

				return retVal;
			}
		}
		//Если очередь пуста, то исключение
		throw new NoSuchElementException();
	}

	@Override
	public T peek() {
		// Вначале могут быть дырки, т.к. рост влево, ищем первый !=null
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (first.getData()[i] != null) {
				T retVal = first.getData()[i];

				return retVal;
			}
		}

		return null;
	}


	/**
	 * Adds all of the elements in the specified collection at the end
	 * of this deque, as if by calling {@link #addLast} on each one,
	 * in the order that they are returned by the collection's iterator.
	 *
	 * <p>When using a capacity-restricted deque, it is generally preferable
	 * to call {@link #offer(Object) offer} separately on each element.
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (c.isEmpty()) {
			throw new NullPointerException();
		}
		//будет знаком того, удалось ли поместить всею коллекцию в нашу очередь
		boolean flg = false;
		for (Object data : c) {
			if (data == null) {
				throw new NullPointerException();
			}
			if (data != null && offer((T) data)) {
				flg = true;
			} else {
				throw new IllegalStateException();
			}
		}
		return flg;
	}

	@Override
	public void clear() {
		Container<T> container = new Container<T>(null, null);
		this.first = container;
		this.last = container;
	}

	@Override
	public boolean retainAll(Collection c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		return false;
	}

	/**
	 * Метод аналогичен addFirst и как стек поверх головы ставит новые данные
	 */
	@Override
	public void push(T t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (this.length < orderVolume) {

			//Если узел переполнен, то создаем новый
			if (first.getContainerSizeMax() == first.getIndex()) {
				Container<T> cont = new Container<T>((T) first, null);
				this.first.setPrev((T) cont);
				this.first = cont;
			}
			//индексация в массиве с 0, поэтому надо вычитать 1
			first.setData(first.getContainerSizeMax() - first.getIndex() - 1, t);
			//first.setData(first.getIndex(), t);
			first.setIndex(first.getIndex() + 1);
			this.length++;
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public T pop() {
		// Вначале могут быть дырки, т.к. рост влево, ищем первый !=null
		for (int i = 0; i < first.getContainerSizeMax(); i++) {
			if (first.getData()[i] != null) {
				T retVal = first.getData()[i];
				//Т.к добавление элемента идет через (getContainerSizeMax - getIndex), то при удалении элемента нужно индекс ументшить
				first.setIndex(first.getIndex() - 1);
				first.setData(first.getContainerSizeMax() - first.getIndex() - 1, null);
				//Если возникла ситуация что мы удалили единственный элемент в payload узла, и у него есть сосед next, то first двигается вправо
				if (first.getIndex() == 0 && first.getNext() != null) {
					first = (Container<T>) first.getNext();
					first.setPrev(null);
				}
				this.length--;
				return retVal;
			}
		}
		//Если очередь пуста, то исключение
		throw new NoSuchElementException();
	}

	@Override
	public boolean remove(Object o) {
		return removeFirstOccurrence(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}


	@Override
	public boolean contains(Object o) {
		java.util.Iterator<T> iterator = iterator();
		boolean found = false;
		while ((iterator.hasNext()) && (!found)) {
			T el = iterator.next();
			if (el.equals(o))
				found = true;
		}
		return found;
	}

	@Override
	public int size() {
		int s = length;
		return s;
	}


	@Override
	public boolean isEmpty() {
		return this.length == 0;
	}


	/**
	 * Вернуть элементы от голову к хваосту
	 *
	 * Returns an iterator over the elements in this deque in proper sequence.
	 * The elements will be returned in order from first (head) to last (tail).
	 *
	 * @return an iterator over the elements in this deque in proper sequence
	 */
	@Override
	public Iterator<T> iterator() {
		Iterator iterator = new Iterator(first);
		return iterator;
	}

	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@Override
	public Object[] toArray(Object[] a) {
		return new Object[0];
	}

	@Override
	public Iterator descendingIterator() {
		Exception UnsupportedOperationException = new Exception();
		try {
			throw UnsupportedOperationException;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public Object[] getContainerByIndex(int cIndex) {
		int count = 0;
		Container<T> cont = first;

		for (int i = 0; i <= cIndex; i++) {
			if (count == cIndex) {
				if (cont == null) {
					return null;
				}

				return cont.getData();
			}
			count++;
			cont = (Container<T>) cont.getNext();
		}

		throw new RuntimeException();
	}


	class Iterator<T> implements java.util.Iterator<T> {
		private Container<T> container;
		private int firtsElenemt;
		private T retVal;
		private boolean flag = true;

		public Iterator(Container<T> container) {
			this.container = container;
		}

		/**
		 * Если первый узел не заполнен на все 5 ячеек, то в начале будут ДЫРКИ, нужно найти
		 * первый элемент, Который не null и уже с него проверять наличие next
		 * @return
		 */
		@Override
		public boolean hasNext() {

			if (this.container.getPrev() == null) {
				for (int i = 0; i < container.getContainerSizeMax() && this.flag == true; i++)
					if (this.container.getData()[i] != null) {
						this.firtsElenemt = i;
						this.flag = false;
					}
			}

			if (this.firtsElenemt != container.getContainerSizeMax() &&
					this.container.getData()[this.firtsElenemt] != null) {
				return true;
			}
			return false;
		}

		@Override
		public T next() {
			this.retVal = this.container.getData()[this.firtsElenemt];
			this.firtsElenemt++;

			//Если следующий элемент выходит за границы контейнера, то нужно перейти с следуюий узел
			if (this.firtsElenemt == container.getContainerSizeMax() &&
					this.container.getNext() != null) {
				this.firtsElenemt = 0;
				this.container = (Container<T>) this.container.getNext();
			}
			if (retVal == null) {
				throw new NoSuchElementException();
			} else {
				return this.retVal;
			}

		}
	}

}
