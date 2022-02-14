package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.Manufacturer;
import com.zhane.laptops.Repositories.ManufacturerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("manufacturer")
public class ManufacturerController {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @GetMapping
    public List<Manufacturer> getAll(){
        return manufacturerRepository.findAll();
    }

    @GetMapping("{id}")
    public Manufacturer getById(@PathVariable long id){
        return manufacturerRepository.getOne(id);
    }

    @PostMapping
    public Manufacturer saveManufacturer(@RequestBody Manufacturer manufacturer){
        return manufacturerRepository.saveAndFlush(manufacturer);
    }

    @PutMapping
    public Manufacturer updateManufacturer(@RequestBody Manufacturer manufacturer){
        Manufacturer oldManu = manufacturerRepository.getOne(manufacturer.getId());
        BeanUtils.copyProperties(manufacturer, oldManu);
        return manufacturerRepository.saveAndFlush(oldManu);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteManufacturer(@PathVariable long id){
        manufacturerRepository.deleteById(id);
    }

}
