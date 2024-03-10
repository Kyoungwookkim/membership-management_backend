package app.membershipmanagement_backend.api.search.controller;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.search.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class FindUserController {

    @Autowired
    private FindUserService findUserService;

    /*@PostMapping("/user")
    public ResponseEntity<DefaultResultDto>finduserlist(
            @RequestParam(name = "searchInput", defaultValue = "") String searchInput,
            @PathVariable int pageNum){

        return ResponseEntity.ok(findUserService)
    }*/

}
