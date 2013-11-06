package test;

import event.*;

public class Events {
	 
    public static void main(String[] args) {
        StateReader reader = new StateReader();
        StateCheckListener listener = new StateReadListen();
        reader.addStateReadListener(listener);
        reader.start();
    }
}