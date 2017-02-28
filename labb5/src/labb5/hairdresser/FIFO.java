package labb5.hairdresser;
import java.util.*;

/**
 * 
 * @author Marcus, Oscar, Henrik. hej
 *
 */
public class FIFO{
	
	//creates a queue
	private List<Object> queue = new ArrayList<Object>();
	
	//creates a variable that can keep track
	// of the max size of the queue
	private int maxsize = 0;
	
	/**
	 * adds an item of type by choice to the list
	 * @param item the item that is added
	 */
	public void add(Object item){
		queue.add(item);
		if (queue.size() > maxsize){
			maxsize = queue.size();
		}
	}
	
	/**
	 * Removes the first item of the list
	 * (or rather creates a new list that is 
	 * exactly the same apart from the first object)
	 * 
	 * if the list is empty it throws an error.
	 */
	public void removeFirst(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}else{
			queue = queue.subList(1, queue.size());
			
		}
		
	}
	public void removeBack() {
		queue.remove(queue.size()-1);
	}
	
	/**
	 * @return size of queue
	 */
	public int size(){
		return(queue.size());
	}
	
	/**
	 * @return max size of the queue
	 */
	public int maxSize(){
		return(maxsize);
	}
	public void addFirst(Object item) {
		queue.add(0, item);
	}
	
	/**
	 * Checks if the queue is empty.
	 * if it is return true, 
	 * if not return false
	 */
	public boolean isEmpty(){
		if(queue.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @return first object in the queue
	 * 
	 * if the queue is empty it throws an error.
	 */
	public Object first(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return(this.queue.get(0));
	}
	
	/**
	 * Checks if this FIFO is equal to another FIFO object.
	 * returns true if yes and false if no.
	 */
	public boolean equals(Object f){
		
		//Checks if the f is of type FIFO
		//if not it throws an error.
		if (f.getClass() == this.getClass()){
			FIFO f2 = ((FIFO) f);
			
			//Makes sure the size of both objects
			// are the same.
			if(this.size() == f2.size()){
				
				//loops though both FIFO objects lists.
				for(int i = 0; i < this.size(); i++){
					
					//Making two variables of the object i
					//in each of the lists
					Object objThis = this.queue.get(i);
					Object objF = f2.queue.get(i);
					
					//Checks if one if null
					//if not checks if both have the same class
					//if not returns false.
					if(objThis == null || objF == null){
						//checks if both objects are null
						//if not return false.
						if(objThis == null && objF == null){
							continue;
						}else{
							return false;
						}
					}else if(objThis.equals(objF)){
						continue;
					}else{
						return false;
					}
				}
				
			}else{
				return false;
			}
			
			return true;
				
		}else{
			throw new ClassCastException();
		}
			
	}
	
	
	/**
	 * @return a string with all the objects in the list on a row.
	 */
	public String toString(){
		String s = "Queue: ";
		for(int elem = 0; elem < queue.size(); elem++){
			s = s+"(" + String.valueOf(queue.get(elem)) + ") ";
		}
		return(s);
	}
	
	public static void main(String[] args) {
		FIFO f1 = new FIFO();
		FIFO f2 = new FIFO();
		f1.add(new Integer(1));
		f2.add(new Integer(1));
		System.out.println(f1.equals(f2));
	}

}
