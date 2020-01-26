package com.asellion.app.userRole;

import com.asellion.app.role.Role;
import com.asellion.app.role.RoleStubs;
import com.asellion.app.user.User;
import com.asellion.app.user.UserStubs;

import java.util.ArrayList;
import java.util.List;

public class UserRoleStubs {
    public static UserRole generateUserRole() {
        User user = UserStubs.generateUserWithNoRole();
        Role role = RoleStubs.generateRole();
        return new UserRole(user, role);
    }

    public static List<UserRole> generateUserRoles() {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(generateUserRole());
        return userRoles;
    }
}
