package com.test.postgresql_test.controller;

import com.test.postgresql_test.Service.ServiceImpl.BoardService;
import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.Entity.*;
import com.test.postgresql_test.domain.dto.WriteBoardDto;
import com.test.postgresql_test.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CfrDataRepository cfrDataRepository;

    private final ReplyRepository replyRepository;

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
                            @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        model.addAttribute("state", "/?");
        model.addAttribute("replyList", replyRepository.findByBoard_IdOrderByCreateDateAsc(id));
        model.addAttribute("topRateCfr", cfrDataRepository.findTop10ByOrderByConfidenceDesc());
        model.addAttribute("boardView", boardService.findById(id));
        model.addAttribute("boards", boardService.boardList(cusPageable(pageable)));
        boardService.viewCount(id, request, response);
        return "index";
    }

    @GetMapping({"/{id}/search"})
    public String searchBoardView(@PathVariable final Long id,
                                  @RequestParam("target") final String target,
                                  @RequestParam("keyword") final String keyword,
                                  Model model,
                                  @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        boardService.viewCount(id, request, response);
        model.addAttribute("state", String.format("/search?target=%s&keyword=%s&", target, keyword));
        model.addAttribute("replyList", replyRepository.findByBoard_IdOrderByCreateDateAsc(id));
        model.addAttribute("topRateCfr", cfrDataRepository.findTop10ByOrderByConfidenceDesc());
        model.addAttribute("boardView", boardService.findById(id));
        model.addAttribute("boards", boardService.searchBoard(target, keyword, cusPageable(pageable)));
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
    public String login(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        String host = request.getHeader("Host");
        Object prePage = request.getSession().getAttribute("prePage");

        if (referer == null || referer.contains("/auth") || !referer.contains(host)) {
            referer = "/";
        }
        if (prePage == null) {
            request.getSession().setAttribute("prePage", referer);
        }

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
        if (principal == null || id != principal.getUsers().getId()) {
            throw new AccessDeniedException("권한없음");
        }
//        long start = System.currentTimeMillis();
//        try {
////            Set<CfrData> cfrList = usersService.findCfrAll(id);
//            List<CfrData> cfrList = cfrDataRepository.findByUsersId_IdOrderByCreateDateDesc(id);
//            model.addAttribute("cfrDataSet", cfrList);
//            return "cfr/list";
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("getCfrList 시간 : " + timeMs + "ms");
//        }
        List<CfrData> cfrList = cfrDataRepository.findByUsersId_IdOrderByCreateDateDesc(id);
        model.addAttribute("cfrDataSet", cfrList);
        return "cfr/list";
    }

    @GetMapping("/board")
    public String writeBoard(Model model,
                             @AuthenticationPrincipal PrincipalDetails principal) {
        if (principal == null) {
            return "redirect:/";
        }
        CfrData cfrData = cfrDataRepository.findTopByUsersId_IdOrderByCreateDateDesc(principal.getUsers().getId());
        model.addAttribute("writeBoardDto", new WriteBoardDto());
        model.addAttribute("cfrData", cfrData);
        return "board/writeBoard";
    }

}