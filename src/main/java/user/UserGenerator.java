package user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public static User generateValidUser() {
        String name = "Test" + RandomStringUtils.randomAlphabetic(5);
        String email = "test_" + RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@mail.ru";
        String password = RandomStringUtils.randomAlphanumeric(8);

        return new User(name, email, password);
    }

    public static User generateUserWithInvalidPassword() {
        String name = "Test" + RandomStringUtils.randomAlphabetic(5);
        String email = "test_" + RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@mail.ru";
        String password = RandomStringUtils.randomAlphanumeric(3); // меньше 6 символов

        return new User(name, email, password);
    }
}
