package com.practice.design;

import java.util.Stack;
public class UndoRedo {

	static class TextEditor{
		Stack<Integer> undoStack = new Stack<>();
		Stack<Integer> redoStack = new Stack<>();
		Stack<Integer> editorStack = new Stack<>();
		int []a = new int[10];
		
		int maxSize  ;
		int undoSize = 0 ;
		int redoSize = 0 ;
		public TextEditor(int undoRedo) {
			maxSize = undoRedo;
		}
		public void undo(){
			if(redoSize > 0) redoSize = 0;
			if(undoStack.size() == 0) return;
			redoStack.add(undoStack.peek());
			undoStack.pop();
			editorStack.pop();
		}
		public void redo(){
			if(redoSize == maxSize +1) return;
			if(redoStack.size() == 0) return;
			editorStack.add(redoStack.peek());
			undoStack.add(redoStack.peek());
			redoStack.pop();
			redoSize++;
		}
		public void showEditor(){
			System.out.print("Current editor state : ");
			for(int i : editorStack){
				System.out.print(i + " ");
			}
			System.out.println();
			System.out.println();
		}
		public void write(int i){
			editorStack.add(i);
			if(redoSize > 0) {
				redoSize = 0;
				redoStack.clear();
			}
			undoStack.add(i);
			this.undoSize++;
			if(this.undoSize == (maxSize +1)) {
				undoStack.remove(0);	
				this.undoSize --;			
			}
		}
	}

	public static void main(String[] args){
		TextEditor t = new TextEditor(5);
		t.write(1);t.write(2);t.write(3);t.write(4);t.write(5);t.write(6);
		System.out.println("--> Wrote 1 2 3 4 5 6");
		t.showEditor();
		t.undo();
		t.undo();
		System.out.println("--> 2 times undo, should show now 1 2 3 4");
		t.showEditor();
		t.redo();
		System.out.println("--> 1 redo, should show now 1 2 3 4 5");
		t.showEditor();
		t.write(7);
		System.out.println("--> added 7, should show now 1 2 3 4 5 7");
		t.showEditor();
		t.redo();
		System.out.println("--> 1 redo, should show now 1 2 3 4 5 7");
		t.showEditor();
		t.undo();
		System.out.println("--> 1 undo, should show now 1 2 3 4 5");
		t.showEditor();
	}
}
