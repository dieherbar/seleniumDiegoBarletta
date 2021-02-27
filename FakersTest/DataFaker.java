package Practico14.FakersTest;

import com.github.javafaker.Faker;

public class DataFaker {
    public static Faker falseador = new Faker();

    public static String getFirstName(){
        String firstName = falseador.name().firstName();
        return firstName;
    }
    public static String getLastName(){
        String lastName = falseador.name().lastName();
        return lastName;
    }
    public static String getJob(){
        String titulo = falseador.company().profession();
        return titulo;
    }
    public static String getEmail(){
        String email = falseador.internet().emailAddress();
        return email;
    }
    public static String getPhone(){
        String phone = falseador.phoneNumber().cellPhone();
        return phone;
    }
}
