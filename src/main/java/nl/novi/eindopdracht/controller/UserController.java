package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.User;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


@RestController
public class UserController implements Serializable {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<Object> getAllEmployees(){
        Collection<User> employees = userService.getAllUsers();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping(value = "/users/{username}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "username") String username){
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }
    @PostMapping(value = "/users")
    public ResponseEntity<Object> createEmployee(@RequestBody User user){
        String newUser = userService.createUser(user);
        return new ResponseEntity<>("A new user is created: "+newUser+".", HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/users/{username}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "username") String username){
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/users/{username}")
    public ResponseEntity<Object> updateEmployee(@PathVariable(value = "username") String username, @RequestBody User user){
        userService.updateUser(username,user);
        return new ResponseEntity<>(username+ " is updated.", HttpStatus.OK);
    }
    @PatchMapping(value = "/users/{username}")
    public ResponseEntity<Object> updateEmployeePartial(@PathVariable(value = "username")@RequestBody User user, String email, String password, Machine machine, Role roles, String username) {
        userService.partialUpdateUser(username, email, password, machine, roles, user);
        return new ResponseEntity<>(username + " is updated.", HttpStatus.OK);
    }
    @GetMapping(value = "/users/{username}/roles")
    public ResponseEntity<Object> getRoles(@PathVariable(value = "username") String username){
        Collection<Role> roles = userService.getRoles(username);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @PostMapping(value = "/users/{username}/roles")
    public ResponseEntity<Object> addRoles(@PathVariable(value = "username") String username, Set<Role> roles){
    userService.addRole(username, roles);
    return new ResponseEntity<>("Successfully added "+roles+ " to "+username, HttpStatus.OK);
    }

}
