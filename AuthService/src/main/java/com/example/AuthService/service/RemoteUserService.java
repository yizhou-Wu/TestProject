package com.example.AuthService.service;

import com.example.AuthService.dto.UserDTO;
import com.example.AuthService.entity.Role;
import com.example.AuthService.entity.User;
import com.example.AuthService.security.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RemoteUserService implements UserDetailsService {
    @Value("${user.service.url}")
    private String userServiceURL;
    private final RestTemplate restTemplate;
    @Autowired
    public RemoteUserService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        String url = String.format("%s/user/getByUsername/?username=%s",userServiceURL,username);
        System.out.println(url);
        ResponseEntity<UserDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                UserDTO.class
        );
        UserDTO dto = response.getBody();
        if (dto == null){
            throw new UsernameNotFoundException("User not Found");
        }
        User user = dto.getUser();
        return AuthUserDetail.builder() // spring security's userDetail
                .username(user.getUsername())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(getAuthoritiesFromUser(dto.getRoleSet()))
                .user_id(user.getId())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

    }
    private List<GrantedAuthority> getAuthoritiesFromUser(Set<Role> set){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        for (Role role :  set){
            userAuthorities.add(new SimpleGrantedAuthority(role.getRole_name()));    // SimpleGrantedAuthority can be created from role Strings
        }
        return userAuthorities;
    }

}
