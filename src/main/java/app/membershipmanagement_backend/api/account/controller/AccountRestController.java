package app.membershipmanagement_backend.api.account.controller;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.account.dto.UserRegisterDto;
import app.membershipmanagement_backend.api.account.service.UserAccountService;
import app.membershipmanagement_backend.api.account.dto.UserDeleteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountRestController {


    private final UserAccountService userAccountService;

    @PostMapping
    public ResponseEntity<DefaultResultDto> register(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        DefaultResultDto dto = userAccountService.register(userRegisterDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    public ResponseEntity<DefaultResultDto> deleteUser(@RequestBody UserDeleteDto userDeleteDto){

        return ResponseEntity.ok(userAccountService.deleteUser(userDeleteDto));
    }


}
