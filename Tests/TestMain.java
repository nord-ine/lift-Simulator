
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import simulator.Main;
/**
 * class test  for the Conrtroller Class
 */
public class TestMain {


   @BeforeAll
    void TestConfigData()
   {

       Main.main(null);
       System.out.println("nbfloor : " + Main.nbfloors);
       System.out.println("nbLifts : " + Main.nblifts);
       System.out.println("capacity : " + Main.capacity);
       System.out.println("velocity : " + Main.velocity);
   }
    @BeforeAll
    void TestData()
    {

        Main.main(null);

        for(int i = 0 ;i<Main.datarequest.size();i++)
        {
            for(int j = 0 ;j<Main.datarequest.get(i).length;j++) System.out.print(Main.datarequest.get(i)[j]+" ");
            System.out.println("");
        }
    }

}
