package bookStore;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserAPIController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "/createUsers", method = RequestMethod.POST)
    public String create(@RequestBody @Valid UserDTO user) {
        userService.create(user);
        return "redirect:createUsers?success";
    }

    @RequestMapping(value = "/deleteUsers", method = RequestMethod.POST)
    public String delete(@RequestBody String username) {
        userService.delete(username.split("=")[1]);
        return "redirect:deleteUsers?success";
    }

    @RequestMapping(value = "/updateUsers", method = RequestMethod.POST)
    public String update(@RequestBody @Valid UserDTO user) {
        userService.update(user);
        return "redirect:updateUsers?success";
    }
}
