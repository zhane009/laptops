package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.Ram;
import com.zhane.laptops.Repositories.RamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ram")
public class RamController {

    @Autowired
    RamRepository ramRepository;

    @GetMapping
    public List<Ram> getAll(){
        return ramRepository.findAll();
    }

    @GetMapping("{id}")
    public Ram getById(@PathVariable long id){
        return ramRepository.getOne(id);
    }

    @PostMapping
    public Ram saveRam(@RequestBody Ram ram){
        return ramRepository.saveAndFlush(ram);
    }

    @PutMapping
    public Ram updateRam(@RequestBody Ram ram){
        Ram oldRam = ramRepository.getOne(ram.getId());
        BeanUtils.copyProperties(ram, oldRam, "freeSlot", "technology");
        return ramRepository.saveAndFlush(oldRam);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteRam(@PathVariable long id){
        ramRepository.deleteById(id);
    }

}
