package simulator;

import java.util.*;

/**
 * this class contains different methods that manage the lifts and the floors
 */

public class Controller {

    /**
     * this is in inner class that represents a request to godown or up in a particular floor
     */
   private class request {
        /**
         * this variable stores the time when the up or down button has been pressed in a particular floor
         */
        int step;
        /**
         * the floor of the request
         */
        int floor;
        Direction requestDirection;
        /**
         * this variables stores the numLift of the Lift which takes in charge of this request
         */
        int liftInCharge ;

        /**
         * constructor of the class request
         * @param s step
         * @param f floornumber
         * @param rd direction (up/down)
         */
        public request(int s, int f,Direction rd)
        {
            this.step=s;
            this.floor=f;
            this.requestDirection=rd;
            this.liftInCharge=-1; // -1 standard value if no lift is in charge of this request
        }
    }

    /**
     * variable used to store the index to the last line of data which is read
     */
    int indexTolist = 0;
    /**
     * this varaible represents the current time in the sumulation
     */
    private int currentTime=0;
    /**
     * stores the list of the lifts
     */
    public List<Lift> listLift = new ArrayList<Lift>();
    /**
     * stores ther list of the floors
     */
    public List<Floor> listFloor = new ArrayList<Floor>();
    /**
     * a list of request
     */
    LinkedList<request> RequestsList = new LinkedList<request>();

    /**
     * Controller class constructor
     * @param nbfloor
     * @param nblift
     * @param capacity
     * @param velocity
     */
    public Controller(int nbfloor,int nblift,int capacity,int velocity)
    {
        for(int i = 0 ;i<nbfloor;i++) listFloor.add(new Floor(i));

        for(int i = 0 ;i<nblift;i++) listLift.add(new Lift(i,capacity,velocity,nbfloor));
    }

    /**
     * method that increments time and run other methods at each step of time
     */
    public void stepOfTime()
    {
        addPeopleToFloor(Main.datarequest);
        floorButtonStates();
        updateRequestsList();
        currentTime++;
    }

    /**
     * method that adds people to the floors at the time of of the request from the data read from the file
     * @param data the data of the simulation read from a file
     */
    public void addPeopleToFloor(ArrayList<int[]>data)
    {
        for(int i = indexTolist ;i<data.size();i++)
        {
            if(data.get(i)[1]>currentTime) break;

            else if (data.get(i)[1]==currentTime)
            {
                Person x  = new Person(data.get(i)[0],data.get(i)[1],data.get(i)[2],data.get(i)[3]); // push button of elevator
                if(x.getdirection()==Direction.up) listFloor.get(data.get(i)[2]).goingUp.add(x);
                else listFloor.get(data.get(i)[2]).goingDown.add(x);
            }
        }
    }

    /**
     * a getter for the private variable currentTime
     * @return current Time in the simulation
     */
    public int getcurrentTime()
    {
        return currentTime;
    }

    /**
     * method that simulate the press of the buttons UP/Down for every floor if there is someone waiting there
     */
    public void floorButtonStates()
    {
        for(Floor y : listFloor)
        {
            y.ButtonDownPressed= !y.goingDown.isEmpty();
            y.ButtonUpPressed= !y.goingUp.isEmpty();
        }
    }

    /**
     * add a request to RequestList if a button is pressed in a particular floor
     * @return true if RequestList is not empty else return false
     */
    public boolean updateRequestsList()
    {
        for(Floor y : listFloor)
        {
           if(y.ButtonUpPressed) RequestsList.add( new request(y.goingUp.peek().step,y.floornum,Direction.up));
            if(y.ButtonDownPressed) RequestsList.add( new request(y.goingDown.peek().step,y.floornum,Direction.down));
        }
        return ! RequestsList.isEmpty();
    }

    /**
     * method that decides which lift is going to handle a particular request
     */
    public void manageLifts()
    {


    }

    /**
     *
     * @param r request that should be handled
     * @return true if the request is handled else return false
     */
    public boolean handleRequest(request r)
    {
        if(LiftInRest()!=-1)
        {

        }
        else if(r.requestDirection==Direction.up && ! LiftGoingUp(r).isEmpty())
        {
            List<Lift>  x = LiftGoingUp(r);
            Collections.sort(x, new Comparator<Lift>() {
                @Override
                public int compare(Lift l1, Lift l2) {
                    return (l1.currentFloor-l2.currentFloor);
                }
            });
        }
        else if (r.requestDirection==Direction.down && ! LiftGoingDown(r).isEmpty())
        {
          List<Lift>  x = LiftGoingDown(r);
            Collections.sort(x, new Comparator<Lift>() {
                @Override
                public int compare(Lift l1, Lift l2) {
                    return (l2.currentFloor-l1.currentFloor);
                }
            });

        }

            return true; // temporary statement
    }

    /**
     * returns the numLift of the first lift in ListLifts which is in state of rest else returns -1
     * @return
     */
    public int LiftInRest()
    {
        int k=-1; // there is no lift in state "rest"
        for(Lift l : listLift)
        {
          if(l.state==Direction.rest)
          {
              k= l.numLift;
              break;
          }
        }
        return k;
    }

    /**
     * returns a list a lifts which are goingUp
     */
    public List<Lift> LiftGoingDown(request rq)
    {
        List<Lift> x = new ArrayList<Lift>();
        for(Lift l : listLift)
        {
            if(l.state==Direction.down && l.currentFloor>rq.floor)
            {
               x.add(l);
            }
        }
        return x;
    }
    /**
     * returns a list a lifts which are Down
     */
    public List<Lift> LiftGoingUp(request rq)
    {
        List<Lift> x = new ArrayList<Lift>();
        for(Lift l : listLift)
        {
            if(l.state==Direction.up && l.currentFloor<rq.floor)
            {
                x.add(l);
            }
        }
        return x;
    }

}
