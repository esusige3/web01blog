package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.User;

import java.util.List;

public interface UserService {
    List<User> LookupUsers();
    boolean RegistUser(User user);
    boolean DeleteUser(String account);
    boolean ModUser(Long id,User user);
    boolean LoginUser(User user);
}
