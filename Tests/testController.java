
import org.junit.jupiter.api.Test;
import simulator.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * class  test  for the Conrtroller Class
 */
public class testController {

    Controller c = new Controller(6,5,15,10);
    @Test
    void TestController()
    {
        assertEquals(6, c.listFloor.size());
        assertEquals(5, c.listLift.size());
    }

    }


