package labb5.hairdresser;

import labb5.simulator.State;

public class SaloonState extends State{
	final int dressers = 5;
	final int waitchairs = 15;
	private FIFO f;
	
	public void addLastLine(Customer c){
		if(!lineFull()){
			f.add(c);
		}
		
	}
	
	public void addUnsatisfied(Customer c){
		
	}
	
	public boolean isLineFullOfUnSatisfied(){
		Customer t;
		for(int i = 0; i < f.size(); i++){
			t = (Customer) f.getIndex(i);
			if(t.getSatisfaction()){
				return false;
			}
			
		}
		return true;
	}
	
	public boolean lineFull(){
		if(f.size() == waitchairs){
			return true;
		}
		return false;
	}

	
}
