package org.example.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.SubmissionDto;
import org.example.service.SectorService;
import org.example.service.SubmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FormController {
    private final SectorService sectorService;
    private final SubmissionService submissionService;

    @GetMapping("/")
    public String showForm(Model model, HttpSession session) {
        model.addAttribute("sectors", sectorService.getAllForSelect());

        SubmissionDto form = submissionService.loadForSession(session.getId())
                .orElseGet(SubmissionDto::new);

        model.addAttribute("form", form);
        return "index";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("form") SubmissionDto form,
                       BindingResult binding,
                       Model model,
                       HttpSession session) {

        if (binding.hasErrors()) {
            model.addAttribute("sectors", sectorService.getAllForSelect());
            return "index";
        }

        submissionService.saveOrUpdate(session.getId(), form);

        model.addAttribute("sectors", sectorService.getAllForSelect());
        model.addAttribute("saved", true);
        return "index";
    }
}
