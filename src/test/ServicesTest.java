import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.service.UserService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class ServicesTest {

    User fineUser = new User()
            .setFirstname("Michael")
            .setLastname("Donovan")
            .setUsername("User0")
            .setPassword("12345678")
            .setEmail("mymail@gmail.com")
            .setRole(User.Role.CLIENT)
            .setBalance(BigDecimal.valueOf(10000))
            .setDob("1985-08-12");

    User userWithOneMistake = new User()
            .setFirstname("AAA")
            .setLastname("Donovan")
            .setUsername("User0")
            .setPassword("12345678")
            .setEmail("mymail@gmail.com")
            .setRole(User.Role.CLIENT)
            .setBalance(BigDecimal.valueOf(10000))
            .setDob("1985-08-12");

    User userWithTwoMistake = new User()
            .setFirstname("AAA")
            .setLastname("BBB")
            .setUsername("User0")
            .setPassword("12345678")
            .setEmail("mymail@gmail.com")
            .setRole(User.Role.CLIENT)
            .setBalance(BigDecimal.valueOf(10000))
            .setDob("1985-08-12");

    UserService userService = new UserService();
    Locale locale = Locale.ENGLISH;

    @Test
    public void validateFineUser() {
        assertEquals("", userService.validateWithConstraints(fineUser, locale));
    }

    @Test
    public void validateUserOneMistake() {
        assertEquals("Must consist of 1 uppercase and up to 19 lowercase letters: AAA", userService.validateWithConstraints(userWithOneMistake, locale));
    }

    @Test
    public void validateUserTwoMistake() {
        assertEquals("Must consist of 1 uppercase and up to 19 lowercase letters: AAA; Must consist of 1 uppercase and up to 19 lowercase letters: BBB",
                userService.validateWithConstraints(userWithTwoMistake, locale));
    }
}
