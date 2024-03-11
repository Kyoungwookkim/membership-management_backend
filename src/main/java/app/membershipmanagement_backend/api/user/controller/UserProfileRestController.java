package app.membershipmanagement_backend.api.user.controller;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileEditDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileRegisterDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileDeleteDto;
import app.membershipmanagement_backend.api.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/profile")
public class UserProfileRestController {


    private final UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<DefaultResultDto> registerProfile(@Validated @RequestBody UserProfileRegisterDto userProfileRegisterDto){

        return ResponseEntity.ok(userProfileService.profileRegister(userProfileRegisterDto));
    }

    @PutMapping
    public ResponseEntity<DefaultResultDto> editProfile(@Validated @RequestBody UserProfileEditDto userProfileEditDto){

        return ResponseEntity.ok(userProfileService.profileUpdate(userProfileEditDto));
    }

    @DeleteMapping
    public ResponseEntity<DefaultResultDto> deleteProfile(@RequestBody UserProfileDeleteDto userProfiledeleteDto){

        return ResponseEntity.ok(userProfileService.deleteProfile(userProfiledeleteDto));
    }

}
