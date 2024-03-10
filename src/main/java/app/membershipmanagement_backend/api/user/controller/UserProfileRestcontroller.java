package app.membershipmanagement_backend.api.user.controller;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileRegisterDto;
import app.membershipmanagement_backend.api.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserProfileRestcontroller {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/profile/register")
    public ResponseEntity<DefaultResultDto> registerProfile(@RequestBody UserProfileRegisterDto userProfileRegisterDto){

        return ResponseEntity.ok(userProfileService.profileRegister(userProfileRegisterDto));
    }



}
