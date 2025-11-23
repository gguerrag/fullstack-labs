package com.example.users.servicios;

import com.example.users.entidades.Role;
import com.example.users.entidades.User;
import com.example.users.repositorio.RoleRepository;
import com.example.users.repositorio.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Usado por Spring Security para autenticar.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado: " + username));

        Collection<? extends GrantedAuthority> authorities = user.getRoles()
                .stream()
                // ROLE_ prefijo estándar de Spring
                .map(Role::getName)
                .map(name -> new SimpleGrantedAuthority("ROLE_" + name))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    /**
     * Registrar un usuario con rol BASIC.
     * Usado por tu AuthController en /api/auth/register.
     */
    public User registerBasicUser(User user) {

        // Reglas de unicidad básicas
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("El username ya está en uso");
        }

        if (user.getEmail() != null && userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }

        if (user.getRut() != null && userRepository.existsByRut(user.getRut())) {
            throw new IllegalArgumentException("El RUT ya está en uso");
        }

        // Buscar rol BASIC en la tabla ROLES
        Role basicRole = roleRepository.findByName("BASIC")
                .orElseThrow(() -> new IllegalStateException("Rol BASIC no existe"));

        // Encriptar password
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);

        // Fecha de creación (si tu entidad la tiene)
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        // Asignar rol BASIC
        user.getRoles().clear();
        user.getRoles().add(basicRole);

        return userRepository.save(user);
    }

    // --- Métodos de apoyo opcionales, por si los usas en otros controladores ---

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
