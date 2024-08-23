package org.example.task.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.task.domain.user.User;
import org.example.task.repository.RoleRepository;
import org.example.task.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public  class UserServiceImpl implements UserDetailsService {

    UserRepository userRepository;
    RoleRepository roleRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with this email %s not found.", email)));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList()));
    }


    public void createUser(User user){
        user.setRoles(List.of(roleRepository.findByRole("ROLE_USER").get()));
        userRepository.save(user);
    }

}
