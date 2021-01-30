package userInterfaceTests.models;

import elementsUtils.ElementsList;
import stringsUtils.RandomString;

import java.util.Random;

public class RandomUser {

    private String email;
    private String password;
    private String domain;
    private int randomDomainIndex;

    public RandomUser() {
        setRandomEmail();
        setRandomPassword();
        setRandomDomain();
    }

    private void setRandomEmail() {
        email = RandomString.getRandomLatinString(10);
    }

    private void setRandomDomain() {
        domain = RandomString.getRandomLatinString(5);
    }

    /**
     * Your password requires at least 10 characters.
     * Your password should have at least 1 Capital letter.
     * Your password must have at least 1 Numeral.
     * Your password needs at least 1 letter of your email.
     * Your password can have at least 1 cyrillic character.
     */
    private void setRandomPassword() {
        Random random = new Random();
        String letterFromEmail = RandomString.getRandomStringFromText(1, email);
        password = RandomString.getRandomLatinString(10);
        password +=letterFromEmail;
        password += random.nextInt();
        password +=RandomString.getRandomCyrillicString(1);
    }

    private void setRandomDomainIndex(ElementsList list) {
        Random random = new Random();
        randomDomainIndex = random.nextInt(list.getSize());
    }

    public String getEmail() {
        return email;
    }

    public String getDomain() {
        return domain;
    }

    public String getRandomPassword() {
        return password;
    }

    public int getRandomDomainIndex(ElementsList list) {
        setRandomDomainIndex(list);
        return randomDomainIndex;
    }
}
