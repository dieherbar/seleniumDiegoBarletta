package practico12;

import org.testng.annotations.*;

public class notacionesTestNg {
    @BeforeTest
    public void preTest(){
        System.out.println("mensaje de notacion BEFORE TEST");
    }
    @BeforeMethod
    public void preMetodo(){
        System.out.println("mensaje de notacion BEFORE METHOD");
    }
    @BeforeClass
    public void preClass(){
        System.out.println("mensaje de notacion BEFORE CLASS");
    }
    @AfterMethod
    public void postMetodo(){
        System.out.println("mensaje de notacion AFTER METHOD");
    }
    @AfterClass
    public void postClass(){
        System.out.println("mensaje de notacion AFTER CLASS");
    }
    @AfterTest
    public void postTest(){
        System.out.println("mensaje de notacion AFTER TEST");
    }

    @Test
    public void metodo1(){
        System.out.println("Ejecucion metodo UNO");
    }
    @Test
    public void metodo2(){        System.out.println("Ejecucion metodo DOS");}
    @Test
    public void metodo3(){        System.out.println("Ejecucion metodo TRES");}
}
