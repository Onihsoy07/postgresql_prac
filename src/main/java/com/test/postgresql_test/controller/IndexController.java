package com.test.postgresql_test.controller;

import com.test.postgresql_test.Service.ServiceImpl.BoardService;
import com.test.postgresql_test.Service.UsersService;
import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.CfrData;
import com.test.postgresql_test.domain.Entity.Reply;
import com.test.postgresql_test.domain.repository.BoardRepository;
import com.test.postgresql_test.domain.repository.CfrDataRepository;
import com.test.postgresql_test.domain.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UsersService usersService;

    private final CfrDataRepository cfrDataRepository;

    private final ReplyRepository replyRepository;

    private  final BoardRepository boardRepository;

    private final BoardService boardService;

    private PageRequest cusPageable(Pageable pageable) {
        System.out.println(pageable.getPageSize());
        return PageRequest.of((pageable.getPageNumber()==0)?0:pageable.getPageNumber()-1, 15, Sort.by("id").descending());
    }

    @GetMapping({"/",""})
    public String index(Model model,
                        @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("state", "/?");
        model.addAttribute("boards", boardService.boardList(cusPageable(pageable)));
        model.addAttribute("topRateCfr", cfrDataRepository.findTop10ByOrderByConfidenceDesc());
        return "index";
    }

    @GetMapping({"/{id}"})
    public String boardView(@PathVariable final Long id,
                            Model model,
                            @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("state", "/?");
        model.addAttribute("replyList", replyRepository.findByBoard_IdOrderByCreateDateAsc(id));
        model.addAttribute("topRateCfr", cfrDataRepository.findTop10ByOrderByConfidenceDesc());
        model.addAttribute("boardView", boardService.findById(id));
        model.addAttribute("boards", boardService.boardList(cusPageable(pageable)));
        return "index";
    }

    @GetMapping({"/search"})
    public String boardSearch(@RequestParam("target") final String target,
                            @RequestParam("keyword") final String keyword,
                            Model model,
                            @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
                                            ) {
        model.addAttribute("state", String.format("/search?target=%s&keyword=%s&", target, keyword));
        model.addAttribute("boards", boardService.searchBoard(target, keyword, cusPageable(pageable)));
        model.addAttribute("topRateCfr", cfrDataRepository.findTop10ByOrderByConfidenceDesc());
        return "index";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/auth/join")
    public String join() {
        return "user/join";
    }


    @GetMapping("/user/detail")
    public String userDetail() {
        return "user/detail";
    }

    @GetMapping("/user/modify")
    public String userModify() {
        return "user/modify";
    }

    @GetMapping("/cfr")
    public String cfr() {
        return "cfr/cfr";
    }

    @GetMapping("/cfr/{id}")
    public String getCfrList(@PathVariable("id") final Long id,
                                                     @AuthenticationPrincipal PrincipalDetails principal,
                                                     Model model) throws Exception {
        if (id != principal.getUsers().getId()) {
            throw new Exception("권한없음");
        }
        long start = System.currentTimeMillis();
        try {
//            Set<CfrData> cfrList = usersService.findCfrAll(id);
            List<CfrData> cfrList = cfrDataRepository.findByUsersId_IdOrderByCreateDateDesc(id);
            model.addAttribute("cfrDataSet", cfrList);
            return "cfr/list";
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("getCfrList 시간 : " + timeMs + "ms");
        }
    }

    @GetMapping("/board")
    public String writeBoard(Model model,
                             @AuthenticationPrincipal PrincipalDetails principal) {
        CfrData cfrData = cfrDataRepository.findTopByUsersId_IdOrderByCreateDateDesc(principal.getUsers().getId());
        model.addAttribute("cfrData", cfrData);
        return "board/writeBoard";
    }

}