package ru.geekbrains.book.market.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.book.market.entities.Role;
import ru.geekbrains.book.market.entities.User;
import ru.geekbrains.book.market.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByUserLogin(String userLogin){
       return userRepository.findByLogin(userLogin);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {

        User user = findByUserLogin(userLogin).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь с данным логин не найден"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    };
}
