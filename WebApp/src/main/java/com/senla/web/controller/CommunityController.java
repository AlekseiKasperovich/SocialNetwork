package com.senla.web.controller;

import com.senla.web.dto.community.CommunityDto;
import com.senla.web.service.CommunityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "communities")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public String showCommunities(Model model) {
        Page<CommunityDto> pageCommunities = communityService.getCommunities();
        List<CommunityDto> communities = pageCommunities.getContent();
        model.addAttribute("communities", communities);
        return "communities";
    }
}
