package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.User;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    Collection<User> getAllUsers();
    Optional<User> getUser(String username);
    String createUser(User user);
    void deleteUser(String username);
    void updateUser(String username, User user);
    void partialUpdateUser(String username, String email, String password, Machine machine, Role roles, User user);
    Collection<Role> getRoles(String username);
    void addRole(String username, Set<Role> roles);
}
