package app.membershipmanagement_backend.api.user.controller;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileEditDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileRegisterDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileDeleteDto;
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
@RequestMapping("/api/user/profile")
public class UserProfileRestController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/register")
    public ResponseEntity<DefaultResultDto> registerProfile(@RequestBody UserProfileRegisterDto userProfileRegisterDto){

        return ResponseEntity.ok(userProfileService.profileRegister(userProfileRegisterDto));
    }


    @PostMapping("/edit")
    public ResponseEntity<DefaultResultDto> editProfile(@RequestBody UserProfileEditDto userProfileEditDto){

        return ResponseEntity.ok(userProfileService.profileUpdate(userProfileEditDto));
    }

    @PostMapping("/delete")
    public ResponseEntity<DefaultResultDto> deleteProfile(@RequestBody UserProfileDeleteDto userProfiledeleteDto){

        return ResponseEntity.ok(userProfileService.deleteProfile(userProfiledeleteDto));
    }


}
