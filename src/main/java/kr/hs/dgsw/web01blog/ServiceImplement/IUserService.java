package kr.hs.dgsw.web01blog.ServiceImplement;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class IUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> LookupUsers() {
        List<User> UserList = this.userRepository.findAll();
        return UserList;
    }

    @Override
    public boolean RegistUser(User user) {
        try{
            this.userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean DeleteUser(String account) {
        try{
            Optional<User> find =this.userRepository.findByAccount(account);
            this.userRepository.delete(find.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean ModUser(Long id, User user) {
        try{
            Optional<User> find = this.userRepository.findById(id);
            User found = find.get();

            found.setAccount(user.getAccount());
            found.setEmail(user.getEmail());
            found.setName(user.getName());
            found.setPassword(user.getPassword());
            found.setPhone(user.getPhone());

            this.userRepository.save(found);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean LoginUser(User user) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(user.getPassword().getBytes(),0,user.getPassword().getBytes().length);
            String tmp = new BigInteger(1,md.digest()).toString(16);

            if(this.userRepository.findByAccount(user.getAccount()).isPresent()&&this.userRepository.findByPassword(tmp).isPresent())
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }
}
