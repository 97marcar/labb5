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
	 * @return the removed object.
	 */
	public Object removeFirst(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}else{
			 List<Object> temp =  new ArrayList<Object>();
			 temp = queue;
			 queue = queue.subList(1, queue.size());
			 return(temp.get(0));
			
		}
		
	}
	
	/**
	 * Add object to a specific index of the queue
	 * @param i index
	 * @param o object
	 */
	public void addObjectMiddle(int i, Object o){
		 queue.add(i, o);
	}
	/**
	 * removes last object
	 */
	public void removeBack() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}else{
			queue.remove(queue.size()-1);
		}
	}	
	/**
	 * @return size of queue
	 */
	public int size(){
		return(queue.size());
	}
	
	/**
	 * Removes the first occurring specific object in the queue 
	 * @param o object
	 * @return true if something was remove else false
	 */
	public boolean removeSpecific(Object o){
		for(int i = 0; i < queue.size(); i++){
			if(o.equals(queue.get(i))){
				queue.remove(o);
				return true;
			}
		}
		return false;
		
		
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
	
	public Object getIndex(int i){
		return(queue.get(i));
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

}
