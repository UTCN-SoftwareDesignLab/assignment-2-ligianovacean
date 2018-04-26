package bookStore.service;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.entity.UserRole;
import bookStore.repository.UserRepository;
import bookStore.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserRolesRepository userRolesRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO user) {
        byte userEnabled = 1;
        User userToSave = new User(user.username, new ShaPasswordEncoder().encodePassword(user.password, null), userEnabled);
        UserRole userRole = new UserRole(user.username, "EMPLOYEE");
        User savedUser = userRepository.save(userToSave);
        userRolesRepository.save(userRole);
        return savedUser;
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }

    @Override
    public void update(UserDTO user) {
        User userToUpdate = userRepository.findByUsername(user.username);
        userToUpdate.setPassword(new ShaPasswordEncoder().encodePassword(user.password, null));
        userRepository.save(userToUpdate);
    }

}
