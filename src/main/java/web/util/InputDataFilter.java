package web.util;

import java.util.Arrays;
import java.util.Optional;

public class InputDataFilter {

    private Optional<String> phone = Optional.empty();

    private Optional<Gender> gender = Optional.empty();

    public InputDataFilter setGender(String gender) {
        this.gender = Arrays.stream(Gender.values()).filter(v -> v.name().contains(gender.toUpperCase())).findFirst();
        return this;
    }

    public InputDataFilter setPhone(String phone) {
        this.phone = phone.matches("^[+]7[0-9]{10}") ? Optional.of(phone) : Optional.empty();
        return this;
    }

    public Gender getGender() {
        return gender.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public boolean validate() {
        return gender.isPresent() && phone.isPresent();
    }

    public InputDataFilter clear() {
        phone = Optional.empty();
        gender = Optional.empty();
        return this;
    }
}
