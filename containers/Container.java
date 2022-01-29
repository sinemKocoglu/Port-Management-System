package containers;

public abstract class Container implements Comparable<Container>{
	private final int ID;
	private final int weight;
	
	/**
	 * Constructor of Container
	 * @param ID : ID of container
	 * @param weight : weight of container
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	

	public abstract double consumption() ;
	
	public abstract boolean equals(Container other) ;

	/**
	 * Gives container's weight
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Gives container's ID
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Method from Comparable<Container> interface to use Colletion.sort()
	 * @param o  another container to compare IDs 
	 * @return   difference between IDs
	 */
	@Override
	public int compareTo(Container o) {
		// TODO Auto-generated method stub
		int compareID = o.getID();
		return this.ID-compareID;
	}
	
}
