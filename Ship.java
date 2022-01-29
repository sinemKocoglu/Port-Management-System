
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.ArrayList;
import java.util.Collections;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import interfaces.IShip;
import ports.Port;
/**
 * IShip interface contains sailTo, refuel, load, unload methods.
 */
public class Ship implements IShip{
	/**
	 * ID : Ship ID
	 * fuel : Fuel ship has
	 * currentPort : Port that ship is located
	 */
	private final int ID;
	private double fuel;
	private Port currentPort;
	
	private int maxNumOfAllConts;
	private int maxNumOfHeavyConts;
	private int maxNumOfRefConts;
	private int maxNumOfLiqConts;
	private double fuelConsumptionPerKm;
	
	private int currentNumOfRefConts=0;
	private int currentNumOfLiqConts=0;
	
	private int currentNumOfHeavyConts = 0;
	private int currentNumOfAllConts = 0;
	private int currentLoadableWeight;
	private double totalContFuelConsumption;
	private ArrayList<Container> CurrentContainers = new ArrayList<Container>();
	
	private ArrayList<Integer> basicContIds = new ArrayList<Integer>();
	private ArrayList<Integer> heavyContIds = new ArrayList<Integer>();
	private ArrayList<Integer> refContIds = new ArrayList<Integer>();
	private ArrayList<Integer> liqContIds = new ArrayList<Integer>();
	
	/**
	 * Constructor of Ship
	 * @param ID  ID of the ship
	 * @param p  port the ship is located
	 * @param totalWeightCapacity  max value of the weight the ship can take
	 * @param maxNumberOfAllContainers  max number of containers ship can take
	 * @param maxNumberOfHeavyContainers  max number of heavy containers ship can take
	 * @param maxNumberOfRefrigeratedContainers  max number of refrigerated containers ship can take
	 * @param maxNumberOfLiquidContainers  max number of liquid containers ship can take
	 * @param fuelConsumptionPerKm  fuel consumption of the ship per km
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKm) {
		this.ID = ID;
		this.currentLoadableWeight = totalWeightCapacity;
		this.maxNumOfAllConts = maxNumberOfAllContainers;
		this.maxNumOfHeavyConts = maxNumberOfHeavyContainers;
		this.maxNumOfRefConts = maxNumberOfRefrigeratedContainers;
		this.maxNumOfLiqConts = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKm = fuelConsumptionPerKm;
		p.getCurrent().add(this);
		this.currentPort = p;
	}
	
	
	/**
	 * Checks whether a ship can sail from the current port to the destination port. If possible, the ship sails to the destination port.
	 * @param p  the destination port.
	 * @return   true if a ship successfully sailed to the destination port.
	 */
	@Override
	public boolean sailTo(Port p) {       
		// TODO Auto-generated method stub
		if(fuel >= (fuelConsumptionPerKm + totalContFuelConsumption(CurrentContainers))*currentPort.getDistance(p)){
			fuel-= (fuelConsumptionPerKm + totalContFuelConsumption(CurrentContainers))*currentPort.getDistance(p);
			currentPort.outgoingShip(this);				
			p.incomingShip(this);
			currentPort = p;
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Adds fuel to a ship.
	 * @param newFuel  the amount of fuel that will be added.
	 */
	@Override
	public void reFuel(double newFuel) {    
		// TODO Auto-generated method stub
		fuel+=newFuel;
	}
	/**
	 * Loads the container to the ship if it does not extend the max conditions of the ship.
	 * @param cont  a container that will be loaded to the ship from the port.
	 * @return   true if a container was successfully loaded to a ship.
	 */
	@Override
	public boolean load(Container cont) {   
		// TODO Auto-generated method stub
		if (currentPort.getContainers().contains(cont)) {
			if(maxNumOfAllConts>currentNumOfAllConts && currentLoadableWeight>=cont.getWeight()) {
				if(cont instanceof BasicContainer) {
					CurrentContainers.add(cont);
					currentPort.getContainers().remove(cont);
					currentNumOfAllConts++;
					currentLoadableWeight-=cont.getWeight();
					return true;
				}
				else if(maxNumOfHeavyConts>currentNumOfHeavyConts){
					if(cont instanceof RefrigeratedContainer) {
						if(maxNumOfRefConts>currentNumOfRefConts) {
							CurrentContainers.add(cont);
							currentPort.getContainers().remove(cont);
							currentNumOfRefConts++;
							currentNumOfHeavyConts++;
							currentNumOfAllConts++;
							currentLoadableWeight-=cont.getWeight();
							return true;
						}
						else {
							return false;
						}
					}
					else if(cont instanceof LiquidContainer) {
						if(maxNumOfLiqConts>currentNumOfLiqConts) {
							CurrentContainers.add(cont);
							currentPort.getContainers().remove(cont);
							currentNumOfLiqConts++;
							currentNumOfHeavyConts++;
							currentNumOfAllConts++;
							currentLoadableWeight-=cont.getWeight();
							return true;
						}
						else {
							return false;
						}
					}
					else if(cont instanceof HeavyContainer) {
						CurrentContainers.add(cont);
						currentPort.getContainers().remove(cont);
						currentNumOfHeavyConts++;
						currentNumOfAllConts++;
						currentLoadableWeight-=cont.getWeight();
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * Unloads the container from the ship to the port.
	 * @param cont  a container that will be unloaded from the ship to the port.
	 * @return   true if a container was successfully unloaded from a ship
	 */
	@Override
	public boolean unLoad(Container cont) {   
		// TODO Auto-generated method stub
		if(CurrentContainers.contains(cont)) {
			CurrentContainers.remove(cont);
			currentPort.getContainers().add(cont);
			currentLoadableWeight+=cont.getWeight();
			if(cont instanceof BasicContainer) {
				currentNumOfAllConts--;
			}
			else if(cont instanceof RefrigeratedContainer) {
				currentNumOfAllConts--;
				currentNumOfHeavyConts--;
				currentNumOfRefConts--;
			}
			else if(cont instanceof LiquidContainer) {
				currentNumOfAllConts--;
				currentNumOfHeavyConts--;
				currentNumOfLiqConts--;
			}
			else if(cont instanceof HeavyContainer) {
				currentNumOfAllConts--;
				currentNumOfHeavyConts--;
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Gives the list of all containers currently in the ship sorted by ID
	 * @return CurrentContainers
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(CurrentContainers);
		return CurrentContainers;
	}

	/**
	 * Sums the fuel consumption of the containers in the ship
	 * @param CurrentContainers
	 * @return totalContFuelConsumption
	 */
	private double totalContFuelConsumption(ArrayList<Container> CurrentContainers ) {
		totalContFuelConsumption=0;
		if(CurrentContainers.size()!=0) {
			for(Container item: CurrentContainers) {
				if(item instanceof RefrigeratedContainer) {
					totalContFuelConsumption += ((RefrigeratedContainer) item).consumption();
				}
				else if(item instanceof LiquidContainer) {
					totalContFuelConsumption += ((LiquidContainer) item).consumption();
				}
				else if(item instanceof HeavyContainer) {
					totalContFuelConsumption += ((HeavyContainer) item).consumption();
				}
				else if(item instanceof BasicContainer) {
					totalContFuelConsumption += ((BasicContainer) item).consumption();
				}
			}
		}
		return totalContFuelConsumption;
	}
	
	/**
	 * Separates ID of containers into array lists in terms of their classes.
	 * @param currentContainers
	 */
	public void TypeContIdLists(ArrayList<Container> currentContainers) {
		for(Container item:currentContainers) {
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
	 * Gives access to the value of fuel from the other classes.
	 * @return the fuel
	 */
	public double getFuel() {
		return fuel;
	}

	/**
	 * Gives access to the current port of ship from the other classes
	 * @return the currentPort
	 */
	public Port getCurrentPort() {
		return currentPort;
	}


	/**
	 * Sets the current port of ship in the other classes
	 * @param currentPort the currentPort to set
	 */
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}


	/**
	 * Gives access to the array list of basic containers' IDs from the other classes.
	 * @return the basicContIds
	 */
	public ArrayList<Integer> getBasicContIds() {
		Collections.sort(basicContIds);
		return basicContIds;
	}

	/**
	 * Gives access to the array list of IDs of the heavy containers in the ship from the other classes.
	 * @return the heavyContIds
	 */
	public ArrayList<Integer> getHeavyContIds() {
		Collections.sort(heavyContIds);
		return heavyContIds;
	}

	/**
	 * Gives access to the array list of the IDs of the refrigerated containers in the ship from the other classes.
	 * @return the refContIds
	 */
	public ArrayList<Integer> getRefContIds() {
		Collections.sort(refContIds);
		return refContIds;
	}

	/**
	 * Gives access to the array list of IDs of the liquid containers in the ship from the other classes.
	 * @return the liqContIds
	 */
	public ArrayList<Integer> getLiqContIds() {
		Collections.sort(liqContIds);
		return liqContIds;
	}
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

