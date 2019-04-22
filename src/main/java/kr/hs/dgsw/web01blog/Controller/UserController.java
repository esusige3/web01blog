package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/view")
    public List<User> LookupUsers(){
        return this.userService.LookupUsers();
    }

    @PostMapping("/user/regit")
    public boolean RegistUser(@RequestBody User user){
        return this.userService.RegistUser(user);
    }

    @DeleteMapping("/user/delete")
    public boolean DeleteUser(@RequestBody String account){
        return this.userService.DeleteUser(account);
    }

    @PutMapping("/user/modi/{id}")
    public boolean ModUser(@PathVariable Long id, @RequestBody User user){
        return this.userService.ModUser(id, user);
    }

    @PostMapping("/user/login")
    public boolean LoginUser(@RequestBody User user){
        return this.userService.LoginUser(user);
    }

}
