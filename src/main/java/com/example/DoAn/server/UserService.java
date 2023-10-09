package com.example.DoAn.server;

import com.example.DoAn.dto.UserDto;
import com.example.DoAn.entity.Role;
import com.example.DoAn.entity.User;
import com.example.DoAn.repository.RoleRepository;
import com.example.DoAn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    public User save(UserDto userDto){
        Role role=roleRepository.findByName("USER");
        if(role==null){
            role=new Role("USER");
            roleRepository.save(role);
        }
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User findNameUser(String email){
        return userRepository.findByEmail(email);
    }
}
