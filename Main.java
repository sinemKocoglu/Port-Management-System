
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import ports.Port;
import ships.Ship;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		int N = Integer.parseInt(in.nextLine());
		int ContainerId = 0;
		int ShipId = 0;  
		int PortId = 0;
		int numofContainer = 0;
		int numOfShip = 0;
		int numOfPort = 0;
		Scanner temp = new Scanner(new File(args[0]));
		temp.nextLine();
		for(int j=0;j<N;j++) {
			String[] t = temp.nextLine().split(" ");
			if(t[0].contentEquals("1")) {
				numofContainer++;
			}
			else if(t[0].contentEquals("2")) {
				numOfShip++;
			}
			else if(t[0].contentEquals("3")) {
				numOfPort++;
			}
		}
		
		Container[] containers = new Container[numofContainer];
		Ship[] ships = new Ship[numOfShip];
		Port[] ports = new Port[numOfPort];
		
		for(int i = 0; i<N;i++) {       
			String[] ElementsOfAction = in.nextLine().split(" ");
			
			if(ElementsOfAction[0].contentEquals("1")) {            //creating container
				int portid = Integer.parseInt(ElementsOfAction[1]);
				int weight = Integer.parseInt(ElementsOfAction[2]);
				if (ElementsOfAction.length==3) {
					if(weight<=3000) {
						containers[ContainerId] = new BasicContainer(ContainerId,weight);
						ports[portid].getContainers().add(containers[ContainerId]);
					}
					else {
						containers[ContainerId] = new HeavyContainer(ContainerId,weight);
						ports[portid].getContainers().add(containers[ContainerId]);
					}		
				}
				else if (ElementsOfAction.length==4) {
					if(ElementsOfAction[3].contentEquals("R")){     
						containers[ContainerId] = new RefrigeratedContainer(ContainerId,weight);
						ports[portid].getContainers().add(containers[ContainerId]);
					}
					else if(ElementsOfAction[3].contentEquals("L")){
							containers[ContainerId] = new LiquidContainer(ContainerId,weight);
							ports[portid].getContainers().add(containers[ContainerId]);
					}
				}
				ContainerId++;
			}		
			else if(ElementsOfAction[0].contentEquals("2")) {  //creating ship
				int portid = Integer.parseInt(ElementsOfAction[1]);
				int maxWeightOfAllContainers = Integer.parseInt(ElementsOfAction[2]);
				int maxNumAllCont = Integer.parseInt(ElementsOfAction[3]);
				int maxNumHeavyCont = Integer.parseInt(ElementsOfAction[4]);
				int maxNumRefCont = Integer.parseInt(ElementsOfAction[5]);
				int maxNumLiqCont = Integer.parseInt(ElementsOfAction[6]);
				double fuelConsumptionPerKm = Double.parseDouble(ElementsOfAction[7]);
				ships[ShipId] = new Ship(ShipId,ports[portid],maxWeightOfAllContainers,maxNumAllCont,maxNumHeavyCont,maxNumRefCont,maxNumLiqCont,fuelConsumptionPerKm);
				ShipId++;
			}
			else if(ElementsOfAction[0].contentEquals("3")) {  //creating port
				double x = Double.parseDouble(ElementsOfAction[1]);
				double y = Double.parseDouble(ElementsOfAction[2]);
				ports[PortId] = new Port(PortId,x,y);
				PortId++;
			}
			else if(ElementsOfAction[0].contentEquals("4")) { //loading container to a ship
				int shipid = Integer.parseInt(ElementsOfAction[1]);
				int containerid = Integer.parseInt(ElementsOfAction[2]);
				ships[shipid].load(containers[containerid]);
			}
			else if(ElementsOfAction[0].contentEquals("5")) {  //Unloading a container from a ship
				int shipid = Integer.parseInt(ElementsOfAction[1]);
				int containerid = Integer.parseInt(ElementsOfAction[2]);
				ships[shipid].unLoad(containers[containerid]);
			}
			else if(ElementsOfAction[0].contentEquals("6")) {   //Ship sails to another port
				int shipid = Integer.parseInt(ElementsOfAction[1]);
				int destinationportid = Integer.parseInt(ElementsOfAction[2]);
				ships[shipid].sailTo(ports[destinationportid]);				
			}
			else if(ElementsOfAction[0].contentEquals("7")) {     //Ship is refueled
				int shipid = Integer.parseInt(ElementsOfAction[1]);
				double fuel = Double.parseDouble(ElementsOfAction[2]);
				ships[shipid].reFuel(fuel);
			}
		}

		for(int portid=0;portid<numOfPort;portid++) {
			if(portid==0) {
				out.println("Port "+ portid + String.format(": (%.2f, %.2f)", ports[portid].getX(), ports[portid].getY()));
			}
			else {
				out.println("Port "+ portid + String.format(": (%.2f, %.2f)", ports[portid].getX(), ports[portid].getY()));
			}
			ports[portid].TypeContIdLists(ports[portid].getContainers());
			if(ports[portid].getBasicContIds().size() != 0) {
				out.print("  BasicContainer:" );
				for(int e:ports[portid].getBasicContIds()) {
					out.print(" " + e);
				}
				out.print("\n");
			}
			if(ports[portid].getHeavyContIds().size()!=0) {
				out.print("  HeavyContainer:" );
				for(int e:ports[portid].getHeavyContIds()) {
					out.print(" " + e);
				}
				out.print("\n");
			}
			if(ports[portid].getRefContIds().size()!=0) {
				out.print("  RefrigeratedContainer:" );
				for(int e:ports[portid].getRefContIds()) {
					out.print(" " + e);
				}
				out.print("\n");
			}
			if(ports[portid].getLiqContIds().size()!=0) {
				out.print("  LiquidContainer:" );
				for(int e:ports[portid].getLiqContIds()) {
					out.print(" " + e);
				}
				out.print("\n");
			}
			for(int shipid=0;shipid<numOfShip;shipid++) {
				if(ports[portid].getCurrent().contains(ships[shipid])) {
					out.println("  Ship "+ shipid + String.format(": %.2f",ships[shipid].getFuel()));
					ships[shipid].TypeContIdLists(ships[shipid].getCurrentContainers());
					if(ships[shipid].getBasicContIds().size()!=0) {
						out.print("    BasicContainer:" );
						for(int e:ships[shipid].getBasicContIds()) {
							out.print(" " + e);
						}
						out.print("\n");
					}
					if(ships[shipid].getHeavyContIds().size()!=0) {
						out.print("    HeavyContainer:" );
						for(int e:ships[shipid].getHeavyContIds()) {
							out.print(" " + e);
						}
						out.print("\n");
					}
					if(ships[shipid].getRefContIds().size()!=0) {
						out.print("    RefrigeratedContainer:" );
						for(int e:ships[shipid].getRefContIds()) {
							out.print(" " + e);
						}
						out.print("\n");
					}
					if(ships[shipid].getLiqContIds().size()!=0) {
						out.print("    LiquidContainer:" );
						for(int e:ships[shipid].getLiqContIds()) {
							out.print(" " + e);
						}
						out.print("\n");
					}
				}
			}
		}
		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

