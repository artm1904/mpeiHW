package ru.mpei;

import java.util.Deque;

public class Main {
	public static void main(String[] args) {
		Deque<String> tQueue = new TripletDeque<>();
		for (int i = 0; i < 12; i++) {
			//for (int i = 0; i < 15; i++) {
			tQueue.addFirst("n_" + i);
		}

		for (String s : tQueue) {
			System.out.println(s);
		}
	}
}
