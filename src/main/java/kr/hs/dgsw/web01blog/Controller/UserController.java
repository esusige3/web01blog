package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/view")
    public ResponseFormat LookupUsers(){
        try {
            return new ResponseFormat(ResponseType.USER_GET,this.userService.LookupUsers());
        }catch(Exception e){
            return new ResponseFormat(ResponseType.FAIL,"Fail");
        }

    }

    @PostMapping("/user/regit")
    public ResponseFormat RegistUser(@RequestBody User user){
        try{
            return new ResponseFormat(ResponseType.USER_ADD,this.userService.RegistUser(user));
        }catch (Exception e){
            return new ResponseFormat(ResponseType.FAIL,"Fail");
        }

    }

    @DeleteMapping("/user/delete")
    public ResponseFormat DeleteUser(@RequestBody String account){
        try{
            return new ResponseFormat(ResponseType.USER_DELETE,this.userService.DeleteUser(account));
        }catch (Exception e){
            return new ResponseFormat(ResponseType.FAIL,"Fail");
        }

    }

    @PutMapping("/user/modi/{id}")
    public ResponseFormat ModUser(@PathVariable Long id, @RequestBody User user){
        try{
            return new ResponseFormat(ResponseType.USER_UPDATE, this.userService.ModUser(id, user));
        }catch (Exception e){
            return new ResponseFormat(ResponseType.FAIL,"Fail");
        }

    }

    @PostMapping("/user/login")
    public ResponseFormat LoginUser(@RequestBody User user){
        try{
            return new ResponseFormat(ResponseType.USER_ACCESSS,this.userService.LoginUser(user));
        }catch (Exception e){
            return new ResponseFormat(ResponseType.FAIL,"Fail");
        }

    }


    @GetMapping
    public ResponseFormat list(){
        return new ResponseFormat(ResponseType.POST_ADD,"HELLO!");
    }

    @GetMapping("/test")
    public String testResponse(){
        return "Response is Recalled!";
    }

}
