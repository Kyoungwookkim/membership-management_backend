package app.membershipmanagement_backend.api.user.controller;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.user.dto.UserDeleteDto;
import app.membershipmanagement_backend.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @DeleteMapping
    public ResponseEntity<DefaultResultDto> deleteUser(@RequestBody UserDeleteDto userDeleteDto){

        return ResponseEntity.ok(userService.deleteUser(userDeleteDto));
    }


}
