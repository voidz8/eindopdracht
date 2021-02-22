package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.ERole;
import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.model.User;
import nl.novi.eindopdracht.payload.request.LoginRequest;
import nl.novi.eindopdracht.payload.request.SignupRequest;
import nl.novi.eindopdracht.payload.response.JwtResponse;
import nl.novi.eindopdracht.payload.response.MessageResponse;
import nl.novi.eindopdracht.repository.RoleRepository;
import nl.novi.eindopdracht.repository.UserRepository;
import nl.novi.eindopdracht.service.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class AuthorizationService {

    private static final  String ROLE_NOT_FOUND_ERROR = "Error: Role not found.";

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public void setUserRepository(UserRepository userRepository){this.userRepository = userRepository;}

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder){this.encoder = passwordEncoder;}

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository){this.roleRepository = roleRepository;}

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager){this.authenticationManager = authenticationManager;}

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils){this.jwtUtils = jwtUtils;}

    public ResponseEntity<MessageResponse> registerUser(@Valid SignupRequest signupRequest){
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signupRequest.getUsername()))){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username already exists."));
        }
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signupRequest.getEmail()))){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email already exists"));
        }

        //Create new user account
        User user = new User(signupRequest.getUsername(),signupRequest.getEmail(),encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()-> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(()-> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(adminRole);
                        break;
                    case "programmer":
                        Role programmerRole = roleRepository.findByName(ERole.ROLE_MACHINPROGRAMMER)
                                .orElseThrow(()-> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(programmerRole);
                        break;
                    case "planner":
                        Role plannerRole = roleRepository.findByName(ERole.ROLE_PLANNER)
                                .orElseThrow(()-> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(plannerRole);
                        break;
                    case "worker":
                        Role workerRole = roleRepository.findByName(ERole.ROLE_WORKSHOPWORKER)
                                .orElseThrow(()-> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(workerRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(()-> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User successfully registered."));
    }

    public ResponseEntity<JwtResponse> authenticateUser(@Valid LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), roles));
    }
}
