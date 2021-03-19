package ExamenNivelUno;

import org.testng.annotations.DataProvider;

public class DataProviderMailchimp {
    @DataProvider(name = "mailchimpProvider")
    public Object[][] mailsMailchimp(){
        return new Object[][]{
                {"pruebaexamenmailchimp@gmail.com", "holamundo"},
                {"joseperezprobandoselenium@gmail.com", "holamundo"},
                {"mariarodriguezusandoasserts@gmail.com", "holamundo"},

        };
    }
}
