package simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a LIft in the simulation
 */
public class Lift {
    /**
     * number of the lift (start from 0)
     */
    int numLift;
    /**
     * the higher floor where the lift can go to
      */
    static int maxfloor;
    /**
     * current floor of the lift in the simulation
     */
    int currentFloor=0;
    /**
     * velocity of the lift
     */
    int velocity;
    /**
     * state is an enum that can take these values (up, Down , rest)
     */
    Direction state;
    /**
     * maximum number of people that get into the lift
     */
     static int capacity;
    /**
     * state of the door = (closed=false, open=true)
     */
     boolean doorState;
    /**
     * list of people that are inside the lift
     */
    List<Person> peopleInside = new ArrayList<Person>();
    /**
     * a list that contains integers that represent the numfoor where the lift should stop to drop people or handle a request
     */
     List<Integer> nextStops = new ArrayList<Integer>();

    /**
     * Lift Class constructor
     * @param num
     * @param capacity
     * @param velocity
     * @param maxfloor
     */
    public Lift(int num ,int capacity,int velocity,int maxfloor)
    {
        this.numLift=num;
        this.capacity=capacity;
        this.velocity=velocity;
        this.maxfloor=maxfloor;
    }

    /**
     * method that simulates lift's opening the door
     */
    public void open()
    {
        doorState = true;
    }
    /**
     * method that simulates lift's closing the door
     */
    public void close()
    {
        doorState=false;
    }

    /**
     * method to moveUp the Lift by one floor
     */
    public void moveUP()
    {
        if(currentFloor<maxfloor)
        currentFloor++;
    }
    /**
     * method to moveDown the Lift by one floor
     */
    public void moveDown()
    {
        if (currentFloor>0)
        currentFloor--;
    }

}
