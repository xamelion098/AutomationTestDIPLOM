package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String years;
        private String name;
        private String cvv;


        public static String getAPPROVEDCardNumber() {

            return ("1111 2222 3333 4444");
        }

        public static String getDECLINEDCardNumber() {
            return ("5555 6666 7777 8888");
        }

        public static String getRandomInValidMaskCard() {
            Faker faker = new Faker();

            return faker.numerify("#### #### #### ####");
        }
        public static String getInValidShortMaskCard() {
            Faker faker = new Faker();

            return faker.numerify("#### #### ####");
        }


        public static String getValidMonth() {

           return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        }

        public static String getInValidMonth(){
            List<String> list = Arrays.asList("13", "14", "15", "16");
            Random rand = new Random();
            return list.get(rand.nextInt(list.size()));
        }
        public static String getValidYear() {
            return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));

        }

        public static String getInValidYear() {
            return LocalDate.now().minusYears(2).format(DateTimeFormatter.ofPattern("yy"));
        }

        public static String validName() {
            Faker faker = new Faker(new Locale("en"));

            return faker.name().fullName();
        }
        public static String invalidNameRUEN(){
            return ("Иванов Ivan");
        }
        public static String invalidLongName(){
            return ("IIIIIIIIIIIIIIIIIIIIIIIIVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVAAAAAAAAAAAAAAAAAAAAAANNNNNNNNNNNNNN IIIIIIIIIIIIIIIIIIIIIIIIIVVVVVVVVVVVVVVVVVVVAAAAAAAAAAAAAAAAAAAANNNNNNNNNNNNNNNNOOOOOOOOOOOOOOOOVVVVVVVVVVV");
        }
        public static String invalidNameNumber(){
            return ("41241421");
        }
        public static String getValidCodeCVV() {
            Faker faker = new Faker();

            return faker.numerify("###");
        }

        public static String getInValidCodeCVV() {
            Faker faker = new Faker();

            return faker.numerify("##");

        }
    }
}


