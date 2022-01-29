
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class RefrigeratedContainer extends HeavyContainer{

	/**
	 * Constructor of refrigerated container
	 * @param ID  ID of refrigerated container
	 * @param weight  weight of refrigerated container
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calculates fuel consumption required by refrigerated container
	 * @return  fuel consumption
	 */
	@Override
	public double consumption() {
		// TODO Auto-generated method stub
		return super.getWeight()*5.00;
	}
	
	/**
	 * checks whether type, ID and weight of two containers are the same.
	 * @param other  
	 * @return   true if they are the same
	 */
	@Override
	public boolean equals(Container other) { 
		if(this.getID()==other.getID() && this.getWeight()==other.getWeight() && other.getClass()==RefrigeratedContainer.class){
			return true;
		}                    
		else {
			return false;
		}
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

