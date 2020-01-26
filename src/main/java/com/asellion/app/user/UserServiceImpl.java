package com.asellion.app.user;

import com.asellion.app.role.Role;
import com.asellion.app.role.RoleRepository;
import com.asellion.app.role.RoleType;
import com.asellion.app.userRole.UserRole;
import com.asellion.exceptions.RoleDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private UserDtoMapper userDtoMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository,
                           UserRepository userRepository,
                           UserDtoMapper userDtoMapper,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(UserDto userDto, RoleType roleType) {
        User user = userDtoMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return setUserRole(user, roleType);
    }

    private User setUserRole(User user, RoleType roleType) {
        Optional<Role> optionalRole = roleRepository.findByName(roleType);
        Role role = optionalRole.orElseThrow(() -> new RoleDoesNotExistException("There is no role such as: " + roleType.name()));
        UserRole userRole = new UserRole(user, role);
        user.addUserRole(userRole);
        return userRepository.save(user);
    }
}
