package com.sportoras.web.controller;

import com.sportoras.database.entity.Material;
import com.sportoras.service.dto.Material.MaterialDto;
import com.sportoras.service.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialController {

    private  final MaterialService materialService;

    @GetMapping("/materials")
    public String getAllMaterials(Model model) {
        List<MaterialDto> materials = materialService.findAllMaterials();
        model.addAttribute("materials", materials);
        return "materials";
    }

    @GetMapping("/material-save")
    public String openMaterialSave(Model model) {
        model.addAttribute("materialDto", new MaterialDto());
        return "/material-save";
    }

    @PostMapping("/material-save")
    public String saveMaterial(MaterialDto materialDto) {
        materialService.saveMaterial(materialDto);
        return "redirect:/materials";
    }

    @GetMapping("/material-info")
    public String materialInfo(Model model, Long id) {
        MaterialDto material = materialService.findById(id);
        model.addAttribute("material", material);
        return "/material-info";
    }
}
