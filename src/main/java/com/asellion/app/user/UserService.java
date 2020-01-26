package com.asellion.app.user;

import com.asellion.app.role.RoleType;

public interface UserService {
    User saveUser(UserDto userDto, RoleType roleType);
}
