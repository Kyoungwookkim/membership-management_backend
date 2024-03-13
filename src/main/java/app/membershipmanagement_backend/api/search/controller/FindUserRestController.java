package app.membershipmanagement_backend.api.search.controller;

import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.search.dto.FindUserDto;
import app.membershipmanagement_backend.api.search.dto.FindUserListDto;
import app.membershipmanagement_backend.api.search.service.FindUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class FindUserRestController {

    private final FindUserService findUserService;

    @GetMapping("/user/list")
    public Page<FindUserDto> getUserList(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int page){

        Pageable pageable = PageRequest.of(page, size);

        Page<FindUserDto> userList = findUserService.getUserList(pageable,keyword);
        return userList;

    }

    @GetMapping("/user/list/detail")
    public FindUserListDto getUserDetail(@RequestParam(name = "userNum", required = false) Long userNum) {

        return findUserService.getUserDetail(userNum);
    }

}
