package com.senla.web.controller;

import com.senla.web.dto.community.CreateCommunityDto;
import com.senla.web.dto.community.UpdateCommunityDto;
import com.senla.web.service.AdminCommunityService;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "admin/community")
public class AdminCommunityController {

    private final AdminCommunityService adminCommunityService;

    private static final String MESSAGE = "message";

    @GetMapping
    public String showCreateCommunityForm(Model model) {
        CreateCommunityDto createCommunityDto = new CreateCommunityDto();
        model.addAttribute("community", createCommunityDto);
        return "createCommunity";
    }

    @PostMapping("create")
    public String createCommunity(
            @ModelAttribute("community") @Valid CreateCommunityDto createCommunityDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "createCommunity";
        }
        adminCommunityService.createCommunity(createCommunityDto);
        redirectAttributes.addFlashAttribute(MESSAGE, "Community create successfully!");
        return "redirect:/admin/community?success";
    }

    @GetMapping("show/update")
    public String showUpdateCommunityForm() {
        return "updateCommunity";
    }

    @PostMapping("update/{communityId}")
    public String updateCommunityForm(
            @PathVariable UUID communityId, RedirectAttributes redirectAttributes) {
        UpdateCommunityDto updateCommunityDto = new UpdateCommunityDto();
        updateCommunityDto.setId(communityId);
        redirectAttributes.addFlashAttribute("updateCommunity", updateCommunityDto);
        return "redirect:/admin/community/show/update";
    }

    @PostMapping("update")
    public String updateCommunity(
            @ModelAttribute("updateCommunity") @Valid UpdateCommunityDto updateCommunityDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "updateCommunity";
        }
        adminCommunityService.updateCommunity(updateCommunityDto);
        return "redirect:/communities";
    }

    @PostMapping("delete/{communityId}")
    public String deleteCommunity(@PathVariable UUID communityId) {
        adminCommunityService.deleteCommunity(communityId);
        return "redirect:/communities";
    }
}
