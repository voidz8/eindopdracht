package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/roles")
    public ResponseEntity<Object> getAllRoles(){return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);}
}
