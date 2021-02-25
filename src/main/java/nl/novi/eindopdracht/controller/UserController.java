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
import java.util.Map;

@RestController
public class UserController implements Serializable {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<Object> getAllEmployees(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping(value = "/users/{username}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "username") String username){
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }
    @PostMapping(value = "/users")
    public ResponseEntity<Object> createEmployee(@RequestBody User user){
        String newUser = userService.createUser(user);
        return new ResponseEntity<>("A new user is created: " + newUser + ".", HttpStatus.CREATED);
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
    public ResponseEntity<Object> updateEmployeePartial(@PathVariable(value = "username") String username,@RequestBody Map<String, Object> fields) {
        userService.partialUpdateUser(username,fields);
        return new ResponseEntity<>(username + " is updated.", HttpStatus.OK);
    }
    @GetMapping(value = "/users/{username}/roles")
    public ResponseEntity<Object> getRoles(@PathVariable(value = "username") String username){
        return new ResponseEntity<>(userService.getRoles(username), HttpStatus.OK);
    }
    @PostMapping(value = "/users/{username}/roles")
    public ResponseEntity<Object> addRole(@PathVariable(value = "username") String username, @RequestBody Role role){
    userService.addRole(username,role);
    return new ResponseEntity<>("Successfully added " + role + " to " + username + ".", HttpStatus.OK);
    }
    @DeleteMapping(value = "/users/{username}/roles")
    public ResponseEntity<Object> removeRole(@PathVariable(value = "username") String username, @RequestBody Role role){
        userService.removeRole(username, role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "users/{username}/machines")
    public ResponseEntity<Object> getMachines(@PathVariable(value = "username") String username){
        return new ResponseEntity<>(userService.getMachines(username), HttpStatus.OK);
    }
    @PostMapping(value = "/users/{username}/machines")
    public ResponseEntity<Object> addMachine(@PathVariable(value = "username") String username, @RequestBody Machine machine){
        userService.addMachine(username,machine);
        return new ResponseEntity<>("Successfully added "+ machine + " to " + username + ".", HttpStatus.OK);
    }
    @DeleteMapping(value = "/users/{username}/machines")
    public ResponseEntity<Object> removeMachine(@PathVariable(value = "username") String username, @RequestBody Machine machine){
    userService.removeMachine(username, machine);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
