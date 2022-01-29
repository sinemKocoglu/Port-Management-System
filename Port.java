
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import java.util.ArrayList;
import java.util.Collections;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import interfaces.IPort;
import ships.Ship;
/**
 * IPort interface contains incomingShip and outgoingShip methods.
 *
 */
public class Port implements IPort{
	/**
	 * containers : array list of containers located in the port
	 * history : array list of ships visited the port
	 * current : array list of ships currently located in the port
	 */
	private final int ID;
	private final double X;
	private final double Y;
	private ArrayList<Container> containers = new ArrayList<Container>();
	private ArrayList<Ship> history = new ArrayList<Ship>();     
	private ArrayList<Ship> current = new ArrayList<Ship>();   
	
	private ArrayList<Integer> basicContIds = new ArrayList<Integer>();
	private ArrayList<Integer> heavyContIds = new ArrayList<Integer>();
	private ArrayList<Integer> refContIds = new ArrayList<Integer>();
	private ArrayList<Integer> liqContIds = new ArrayList<Integer>();
		
	/**
	 * Constructor of Port
	 * @param ID  ID of Port
	 * @param X  x coordinate of port
	 * @param Y  y coordinate of port
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	
	/**
	 * calculates distance between the current port and another port.
	 * @param other  other port
	 * @return distance
	 */
	public double getDistance(Port other) {  
		return Math.sqrt((other.getX()-X)*(other.getX()-X) + (other.getY()-Y)*(other.getY()-Y));
	}
	/**
	 * Records the incoming ships to current.
	 * @param s  ship coming to the port
	 */
	@Override
	public void incomingShip(Ship s) {
		// TODO Auto-generated method stub
		if(!current.contains(s)) {
			current.add(s);
		}		
	}
	/**
	 * Records the outgoing ships to history and current.
	 * @param s  ship going from the port
	 */
	@Override
	public void outgoingShip(Ship s) {
		// TODO Auto-generated method stub
		current.remove(s);
		if (!history.contains(s)) {	
			history.add(s);
		}
	}

	/**
	 * Gives access to the array list containers in the port from the other classes.
	 * @return the containers
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}

	/**
	 * Gives access to x component of the port from the other classes.
	 * @return the x
	 */
	public double getX() {
		return X;
	}

	/**
	 * Gives access to y component of the port from the other classes.
	 * @return the y
	 */
	public double getY() {
		return Y;
	}

	/**
	 * Separates ID of containers into array lists in terms of their classes.
	 * @param containers
	 */
	public void TypeContIdLists(ArrayList<Container> containers) {
		for(Container item:containers) {
			if(item instanceof BasicContainer) {
				basicContIds.add(item.getID());
			}
			else if(item instanceof RefrigeratedContainer) {
				refContIds.add(item.getID());
			}
			else if(item instanceof LiquidContainer) {
				liqContIds.add(item.getID());
			}
			else if(item instanceof HeavyContainer) {
				heavyContIds.add(item.getID());
			}
		}
	}

	/**
	 * Gives access to the array list of IDs of the basic containers in the port from the other classes.
	 * @return the basicContIds
	 */
	public ArrayList<Integer> getBasicContIds() {
		Collections.sort(basicContIds);
		return basicContIds;
	}

	/**
	 * Gives access to the array list of IDs of the heavy containers in the port from the other classes.
	 * @return the heavyContIds
	 */
	public ArrayList<Integer> getHeavyContIds() {
		Collections.sort(heavyContIds);
		return heavyContIds;
	}

	/**
	 * Gives access to the array list of IDs of the refrigerated containers in the port from the other classes.
	 * @return the refContIds
	 */
	public ArrayList<Integer> getRefContIds() {
		Collections.sort(refContIds);
		return refContIds;
	}

	/**
	 * Gives access to the array list of IDs of the liquid containers in the port from the other classes.
	 * @return the liqContIds
	 */
	public ArrayList<Integer> getLiqContIds() {
		Collections.sort(liqContIds);
		return liqContIds;
	}

	/**
	 * Gives access to the array list of current ships in the port from the other classes.
	 * @return the current
	 */
	public ArrayList<Ship> getCurrent() {
		return current;
	}
	
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

