package utility;

public class SLL implements List {

	private SLLNode head;
	private int size;
	
	@Override
	public boolean append(Object element) {
		SLLNode newNode = new SLLNode(element);
		
		if(head==null) {
			head = newNode;
		}
		else if(size==1){
			head.next=newNode;
		}
		else {
			SLLNode lastNode= head;
			while(lastNode.next !=null) {
				lastNode=lastNode.next;
			}
			lastNode.next=newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean add(Object element) {
		SLLNode newNode = new SLLNode(element);
		
		if(head == null) {
			head = newNode;
		}
		else {
			newNode.next = head;
			head = newNode;
		}
		
		size++;
		return true;
	}

	@Override
	public boolean add(Object element, int position) throws IndexOutOfBoundsException {
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		
		SLLNode newNode = new SLLNode(element);
		if(position == 0) {
			newNode.next = head;
			head = newNode;
			size++;
			return true;
		}
		else {
			SLLNode previous = head;
			int count = 1;
			while(count<position) {
				previous = previous.next;
				count++;
			}
			SLLNode current = previous.next;
			newNode.next = current;
			previous.next = newNode;
			size++;
			return true;
		}
		
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public Object remove() {		
		
		if(head == null) {
			return null;
		}
		else{
			Object element = head.element;
			head=head.next;
			size--;
			return element;
		}
	}

	@Override
	public Object removeLast() {
		SLLNode secondLastNode = head;
		
		if(head == null) {
			return null;
		}
		
		for(int i=0;i<size-1; i++ ) {
			secondLastNode=secondLastNode.next;
		}
		Object lastNode = getLast();
		secondLastNode.next = null;
		size--;
		return lastNode;
	}

	@Override
	public Object remove(int position) throws IndexOutOfBoundsException {
		if(position < 0 || position >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(position == 0) {
			return remove();
		}
		else if(position== size-1) {
			return removeLast();
		}
		else {
			SLLNode previous = head;
			int count = 1;
			while(count<position) {
				previous = previous.next;
				count++;
			}
			SLLNode current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}

	@Override
	public Object get() {
		if(head == null) {
			return null;
		}
		return head.element;
	}

	@Override
	public Object getLast() {
		if(head==null) {
			return null;
		}
		
		SLLNode lastNode= head;
		
		while(lastNode.next !=null) {
			lastNode=lastNode.next;
		
		}
		return lastNode.element;
	}

	@Override
	public Object get(int position) throws IndexOutOfBoundsException {
		if(position < 0 || position >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		SLLNode node = head;
		
		if(head == null) {
			return null;
		}
		else {
			for(int i=0; i<size; i++) {
				if(i==position) {
					return node.element;
				}
				node = node.next;
			}
		}
		return null;
		
	}


	
	@Override
	public Object set(Object element, int position) throws IndexOutOfBoundsException {
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		
		SLLNode newNode = new SLLNode(element);
		SLLNode previous = head;
		if(position == 0) {
			head = newNode;
			newNode.next=previous.next;
			return previous.element;
		}
		else {
			int count = 1;
			while(count<position) {
				previous = previous.next;
				count++;
			}
			SLLNode current = previous.next;
			newNode.next = current.next;
			previous.next = newNode;
			
			return current.element;
		}
		
	}

	@Override
	public boolean contains(Object element) {
		if(head==null) {
			return false;
		}
		
		SLLNode node = head;
		
		for(int i=0; i<size; i++) {
			if(node.element == element) {
				return true;
			}
			node=node.next;
		}
		return false;
	}

	@Override
	public int indexOf(Object element) {
		if(head==null) {
			return -1;
		}
		
		SLLNode node = head;

		for(int i=0;i<size;i++) {
			if(node.element == element) {
				return i;
			}
			node=node.next;
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head==null;
	}

}
