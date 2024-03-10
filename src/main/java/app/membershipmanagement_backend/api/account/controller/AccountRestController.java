package app.membershipmanagement_backend.api.account.controller;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.account.dto.UserRegisterDto;
import app.membershipmanagement_backend.api.account.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/register")
    public ResponseEntity<DefaultResultDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        System.out.println("나 컨트롤러 들어온 응애");
        return ResponseEntity.ok(userAccountService.register(userRegisterDto));
    }

    @PostMapping("/check/userId")
    public ResponseEntity<String> checkId(@RequestBody String userId) throws IOException{
        System.out.println("나 여기 들어옴");
        System.out.println(userId);

        return ResponseEntity.ok(userId);
    }


}
