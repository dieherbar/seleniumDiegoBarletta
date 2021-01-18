package practico13;

import org.testng.annotations.Factory;

public class DocusignFactory {
    @Factory
    public Object[] factoryMethod(){
        return new Object[]{
                new DocusignFactoryTest(),
                new DocusignFactoryTest(),
                new DocusignFactoryTest()
        };
    }
}
