package examples;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Calculator {
    @Test(dataProvider = "getNumbers")
    public void addition(int x,int y, int z){
        System.out.println(x+y+z);
    }
    @Test(dataProvider = "getNumbers")
    public void multiplication(int x,int y, int z){
        System.out.println(x*y*z);
    }

    @DataProvider
    public Object[][] getNumbers(){
        Object[][] data = {{5,4,6},{13,10,9},{120,100,4}};
        return data ;
    }
}
