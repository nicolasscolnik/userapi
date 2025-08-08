// package com.example.userapi.controller;

// import com.example.userapi.model.User;
// import com.example.userapi.service.UserService;
// import jakarta.validation.Valid;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     private final UserService service;

//     public UserController(UserService service) {
//         this.service = service;
//     }

//     @GetMapping
//     public List<User> getAllUsers() {
//         return service.getAllUsers();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<User> getUser(@PathVariable Long id) {
//         return service.getUserById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//         User savedUser = service.createUser(user);
//         return ResponseEntity.ok(savedUser);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
//         User updated = service.updateUser(id, user);
//         return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//         service.deleteUser(id);
//         return ResponseEntity.noContent().build();
//     }

//     @GetMapping("/hello")
//     public String hello() {
//     return "Swagger is working!";
//     }
// }

package com.example.userapi.controller;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Operaciones CRUD sobre usuarios")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @Operation(summary = "Actualizar un usuario por su ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updated = service.updateUser(id, user);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un usuario por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
