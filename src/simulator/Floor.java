
package simulator;
import java.util.*;

/**
 * class that reprensts a floor in the simulation
 */
public class Floor {
    /**
     * number of floor (start from 0)
     */
    int floornum;
    /**
     * boolean to tell if the goingDown Button is pressed (we supposed that in ecery floor thre is a goUP/Down Button
      */
    boolean ButtonUpPressed = false;
    /**
     * boolean to tell if the goingUp Button is pressed
     */
    boolean ButtonDownPressed= false;
    /**
     * list of people who are a queue to go UP
     */
    Queue <Person>goingUp = new PriorityQueue<Person>();
    /**
     * list of people who are a queue to go DOWN
     */
    Queue <Person>goingDown= new PriorityQueue<Person>();

    /**
     * floor class Constructor
     * @param k number of the floor
     */
    public Floor(int k)
    {
        this.floornum=k;
    }

}
