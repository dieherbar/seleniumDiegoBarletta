package ExamenNivelUno;

import org.testng.annotations.Factory;

public class MailchimpFactory {
    @Factory
    public Object[] factoryMethod(){
        return new Object[]{
                new Prueba_mailchimp(),
                new Prueba_mailchimp(),
        };
    }
}
