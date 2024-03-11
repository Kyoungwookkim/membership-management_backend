package app.membershipmanagement_backend.api.account.controller;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.account.dto.UserRegisterDto;
import app.membershipmanagement_backend.api.account.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountRestController {


    private final UserAccountService userAccountService;

    @PostMapping("/register")
    public ResponseEntity<DefaultResultDto> register(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        System.out.println("나 컨트롤러 들어온 응애");
        System.out.println(userRegisterDto.getUserName());
        DefaultResultDto dto = userAccountService.register(userRegisterDto);
        return ResponseEntity.ok(dto);
    }


}
