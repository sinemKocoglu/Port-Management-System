package containers;

public class HeavyContainer extends Container{

	/**
	 * Constructor of heavy container
	 * @param ID  ID of heavy container
	 * @param weight  weight of heavy container
	 */	
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Calculates fuel consumption required by heavy container
	 * @return  fuel consumption
	 */
	@Override
	public double consumption() { 
		// TODO Auto-generated method stub
		return super.getWeight()*3.00;
	}
	
	/**
	 * checks whether type, ID and weight of two containers are the same.
	 * @param other  
	 * @return   true if they are the same
	 */
	@Override
	public boolean equals(Container other) { 
		if(this.getID()==other.getID() && this.getWeight()==other.getWeight() && other.getClass()==HeavyContainer.class){
			return true;
		}                    
		else {
			return false;
		}
	}
	
}
