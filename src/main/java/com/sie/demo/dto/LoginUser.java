package com.sie.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sie.demo.model.Permission;
import com.sie.demo.model.User;
import com.sie.demo.util.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan
 * @description:
 * @date 2020-07-30 10:01:35
 */
@Data
public class LoginUser extends User implements UserDetails {

    private List<Permission> permissions;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.parallelStream()
                .filter(p -> !StringUtils.isEmpty(p.getIdentification()))
                .map(p -> new SimpleGrantedAuthority(p.getIdentification())).collect(Collectors.toSet());

    }

    @Override
    public boolean isAccountNonExpired() {
        return getStatus()!= Status.DISABLED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getStatus()!= Status.DISABLED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
