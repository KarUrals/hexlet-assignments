package exercise.controller;
import com.querydsl.core.types.dsl.BooleanExpression;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> findUsers(
            @RequestParam(defaultValue = "") String firstName,
            @RequestParam(defaultValue = "") String lastName) {

        BooleanExpression firstNameExpression = QUser.user.firstName.containsIgnoreCase(firstName);
        BooleanExpression lastNameExpression = QUser.user.lastName.containsIgnoreCase(lastName);
        return this.userRepository.findAll(firstNameExpression.and(lastNameExpression));
    }
    // END
}

