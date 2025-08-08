// package com.example.userapi.controller;

// import com.example.userapi.model.User;
// import com.example.userapi.service.UserService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/users")
// @Tag(name = "User API", description = "Operaciones CRUD sobre usuarios")
// public class UserController {

//     private final UserService service;

//     public UserController(UserService service) {
//         this.service = service;
//     }

//     @Operation(summary = "Obtener todos los usuarios")
//     @GetMapping
//     public List<User> getAllUsers() {
//         return service.getAllUsers();
//     }

//     @Operation(summary = "Obtener un usuario por su ID")
//     @GetMapping("/{id}")
//     public ResponseEntity<User> getUser(@PathVariable Long id) {
//         return service.getUserById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @Operation(summary = "Crear un nuevo usuario")
//     @PostMapping
//     public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//         User savedUser = service.createUser(user);
//         return ResponseEntity.ok(savedUser);
//     }

//     @Operation(summary = "Actualizar un usuario por su ID")
//     @PutMapping("/{id}")
//     public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
//         User updated = service.updateUser(id, user);
//         return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
//     }

//     @Operation(summary = "Eliminar un usuario por su ID")
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//         service.deleteUser(id);
//         return ResponseEntity.noContent().build();
//     }
// }

package com.example.userapi.controller;

import com.example.userapi.dto.UserCreateDto;
import com.example.userapi.dto.UserDto;
import com.example.userapi.dto.UserUpdateDto;
import com.example.userapi.mapper.UserMapper;
import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Listar usuarios (paginado)")
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        Page<UserDto> page = service.getAllUsers(pageable).map(mapper::toDto);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Listar usuarios (lista completa) — solo dev")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsersList() {
        return ResponseEntity.ok(service.getAllUsers().stream().map(mapper::toDto).toList());
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return service.getUserById(id)
                .map(u -> ResponseEntity.ok(mapper.toDto(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un usuario")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto body) {
        User toSave = mapper.toEntity(body);
        User saved = service.createUser(toSave);
        return ResponseEntity
                .created(URI.create("/api/users/" + saved.getId()))
                .body(mapper.toDto(saved));
    }

    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto body) {
        // obtenemos y actualizamos con el mapper para no exponer entidad
        return service.getUserById(id)
                .map(entity -> {
                    mapper.updateEntity(body, entity);
                    User updated = service.updateUser(id, entity);
                    return ResponseEntity.ok(mapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
