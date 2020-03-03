package simulator;

/**
 * class that represents a personn in the simulation
 */
public class Person {
    /**
     * id of the person
     */
    int id;
    /**
     * the step of time when the person hits the request Button
     */
    int step;
    /**
     * initial location of the person
     */
    int startfloor;
    /**
     * floor destination of the person
     */
    private int endfloor;
    /**
     * time when the person get in the lift
     */
    int enteringTime = 0;

    /**
     * person class constructor
     * @param id
     * @param step
     * @param startfloor
     * @param endfloor
     */
    public Person(int id ,int step,int startfloor, int endfloor)
    {
        this.id=id;
        this.step=step;
        this.startfloor=startfloor;
        this.endfloor=endfloor;
    }


    /**
     * method that returns the direction of the person based on startfloor - endFloor
     * this method help us know in which floor list we should add the person (goingUp or goingDown)
     * @return
     */
    public Direction getdirection()
    {
        if(endfloor-startfloor>0) return Direction.up;
        else return Direction.down;
    }

    /**
     * getter for the endfloor variable which is encapsulated with the private keyword
     * @return endfloor of the person
     */
    public int getendfloor()
    {
        return endfloor;
    }

}
