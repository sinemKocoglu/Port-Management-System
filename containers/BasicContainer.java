
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class BasicContainer extends Container {

	/**
	 * Constructor of basic container
	 * @param ID  ID of basic container
	 * @param weight  weight of basic container
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Calculates fuel consumption required by basic container
	 * @return  fuel consumption
	 */
	@Override
	public double consumption() {
		// TODO Auto-generated method stub
		return super.getWeight()* 2.50;
	}	

	/**
	 * checks whether type, ID and weight of two containers are the same.
	 * @param other  
	 * @return   true if they are the same
	 */
	@Override
	public boolean equals(Container other) { 
		if(this.getID()==other.getID() && this.getWeight()==other.getWeight() && other.getClass()==BasicContainer.class){
			return true;
		}                    
		else {
			return false;
		}
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

