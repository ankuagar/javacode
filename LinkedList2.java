import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Math;

public class LinkedList2<T extends Comparable<T>> implements Iterable<T> {


	private Node<T> head;
	private Node<T> tail;
	private int numOfElem;
	
	public LinkedList2() {
	
		this.head = null;
		this.tail = null;
		this.numOfElem = 0;
	}
	
	
	private class Node<T> {
	
		Node<T> next;
		T data;
		
		Node(Node<T> next, T data) {
		
			this.next = next;
			this.data = data;
		
		}

	}
	
	private Node<T> returnHead() {
	
		return this.head;
	
	}
	
	
	
	public void insert(T data) {
	
		this.insertAtHead(data);	
	
	}
	
	public void insertAtHead(T data) {
	
		if(numOfElem == 0) {
				
			head = new Node<T>(null, data);
			tail = head;
			numOfElem+=1;
			
		}			
		
		else {
					
			Node<T> n = new Node<T>(null, data);
			n.next = head;
			head = n;
			numOfElem+=1;
			
		}			
	
	}
	
	public void insertAtTail(T data) {
	
		if(numOfElem == 0 ) {
		
			head = new Node<T>(null,data);
			tail = head;
			numOfElem+=1;
			
		
		}
		
		else {
		
			Node<T> n = new Node<T>( null, data);
			tail.next = n;
			tail = n;
			numOfElem+=1;
		}
	}
	
	
	public void delete( T data) {
	
		if(numOfElem == 0) {
		
			return;
		}
		
		else if(head.data == data) { //First check if head matches
		
			if(numOfElem == 1) {
				head = tail = null;
			}

			else {
				head = head.next;
			}

			numOfElem-=1;
		}

		else { 
			Node<T> previous = null;
			Node<T> current = head;
				
			while(current != null) {
		
				if(current.data == data ) { //now head will not match, we already checked that
				
					previous.next = current.next;			
					numOfElem-=1;	
				}

				else {
					previous = current;
					current = current.next;
				}
			}			
		}
	
	}
	
	
	private class LinkedList2Iterator implements Iterator<T> {
	
		private int count = LinkedList2.this.numOfElem;
		private Node<T> current = LinkedList2.this.head;
		
		public boolean hasNext() {
		
			if(count > 0)

				return true;

			else

				return false;					
		
		}
		
		public T next() {
		
			if(count > 0 ) {
			
				T temp = current.data;
				count-=1;
				current = current.next;
				return temp;
			
			}
		
			else 	
				throw new NoSuchElementException();	
		}
		
		
		public void remove() throws UnsupportedOperationException {
		
		
		}
		
	}
	
	public Iterator<T> iterator() {
	
		return new LinkedList2Iterator();
	
	}
		
	public static LinkedList2<Integer> numToLinkedList(int data) {
	
		LinkedList2<Integer> ll = new LinkedList2<Integer>();
		
		if(data == 0) {

			ll.insert(data);

		}
	
	
		else {
			while(data != 0) {
			
				int digit = data % 10;
				ll.insertAtTail(digit);
				data = data / 10;				
			}
		}	

		return ll;
	}
	
	public static LinkedList2<Integer> addTwoNum(int num1, int num2) {
	
	        return numToLinkedList(num1 + num2);
	
	}
	
	public static LinkedList2<Integer> addLinkedLists(LinkedList2<Integer> ll1, LinkedList2<Integer> ll2) {
	
	        
	        return numToLinkedList(linkedListToNum(ll1) + linkedListToNum(ll2));
	
	}
	
	
	
	//This method assumes that the number is stored in linkedlist in reverse order
	// 214 is stored as 4->1->2

	public static int linkedListToNum(LinkedList2<Integer> ll) {
	
		int sum = 0, count = 0;


		if(ll == null )
			return sum;

		else {
			Iterator<Integer> i = ll.iterator();
			
			while( i.hasNext() ) {
			
	  	       		sum = sum + i.next() * (int)Math.pow(10.0,count);
	  	       		count += 1;							
		       }
		}
		
		return sum;			
	}		
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


