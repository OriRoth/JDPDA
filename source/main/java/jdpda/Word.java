package jdpda;

import java.util.ArrayList;

/**
 * A finite sequence of symbols. Stack notations are supported.
 * 
 * @author Ori Roth
 */

public class Word<T extends Enum<T>> extends ArrayList<T> {
	private static final long serialVersionUID = 0x7713051FAB4F1FB2L;

	public Word() {
		//
	}

	public Word(T t) {
		add(0, t);
	}

	public Word(Word<T> w) {
		addAll(w);
	}

	public Word(@SuppressWarnings("unchecked") T... w) {
		for (T t : w)
			push(t);
	}

	public T top() {
		return get(0);
	}

	public void push(T t) {
		add(0, t);
	}

	public void push(Word<T> w) {
		addAll(0, w);
	}

	public Word<T> pop() {
		 remove(0);
		 return this;
	}

	@Override
	public Word<T> subList(int fromIndex, int toIndex) {
		return new Word<>(this).pop();
	}
}
