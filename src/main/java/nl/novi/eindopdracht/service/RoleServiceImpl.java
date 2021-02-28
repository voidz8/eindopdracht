package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {return roleRepository.findAll();}
}
