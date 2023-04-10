package com.megapet.backendmegapet.user.api;

import com.megapet.backendmegapet.user.domain.service.UserService;
import com.megapet.backendmegapet.user.mapping.UserMapper;
import com.megapet.backendmegapet.user.resource.CreateUserResource;
import com.megapet.backendmegapet.user.resource.LoginUserResource;
import com.megapet.backendmegapet.user.resource.UpdateUserResource;
import com.megapet.backendmegapet.user.resource.UserResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UserService userService;
    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListPage(userService.getAll(), pageable);
    }

    @GetMapping("{userId}")
    public UserResource getUserById (@PathVariable Long userId) {
        return mapper.toResource(userService.getById(userId));
    }

    @PostMapping
    public UserResource createUser (@RequestBody CreateUserResource resource) {
        return mapper.toResource(userService.create(mapper.toModel(resource)));
    }

    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return mapper.toResource(userService.update(userId, mapper.toModel(resource)));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }

    @PostMapping("/login")
    public UserResource login(@RequestBody LoginUserResource resource) {
        return mapper.toResource(userService.login(resource.getEmail(), resource.getPassword()));
    }
}
