package com.sportoras.web.restController;

import com.sportoras.service.dto.Material.MaterialDto;
import com.sportoras.service.dto.reviewDto.ReviewDto;
import com.sportoras.service.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialController {

    private final static Logger LOGGER = LogManager.getLogger(MaterialController.class);
    private final MaterialService materialService;

    @GetMapping(value = "/materials/")
    public ResponseEntity<List<MaterialDto>> listAllMaterials() {
        List<MaterialDto> materials = materialService.findAllMaterials();
        if (materials == null) {
            LOGGER.error("Materials are not found");
            throw new EntityNotFoundException("Materials not found");
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @PostMapping(value = "/material-save/")
    public ResponseEntity<ReviewDto> saveMaterial(@RequestBody MaterialDto materialDto) {
        if (materialService.findByName(materialDto.getName()) != null) {
            LOGGER.error("This material with name " + materialDto.getName() + " already exists");
            throw new EntityExistsException("Materials already exists");
        }
        materialService.saveMaterial(materialDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/material-info/{id}")
    public ResponseEntity<MaterialDto> getMaterialById(@PathVariable("id") long id) {
        MaterialDto material = materialService.findById(id);
        if (material == null) {
            LOGGER.error("Material with id " + id + " not found.");
            throw new EntityNotFoundException("Material not found");
        }
        return new ResponseEntity<>(material, HttpStatus.OK);
    }
}
