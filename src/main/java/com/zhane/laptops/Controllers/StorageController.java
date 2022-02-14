package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.Storage;
import com.zhane.laptops.Repositories.StorageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("storage")
public class StorageController {

    @Autowired
    StorageRepository storageRepository;

    @GetMapping
    public List<Storage> getAll(){
        return storageRepository.findAll();
    }

    @GetMapping("{id}")
    public Storage getById(@PathVariable long id){
        return storageRepository.getOne(id);
    }

    @PostMapping
    public Storage saveStorage(@RequestBody Storage storage){
        return storageRepository.saveAndFlush(storage);
    }

    @PutMapping
    public Storage updateStorage(@RequestBody Storage storage){
        Storage oldStorage = storageRepository.getOne(storage.getId());
        BeanUtils.copyProperties(storage, oldStorage, "technology");
        return storageRepository.saveAndFlush(oldStorage);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteStorage(@PathVariable long id){
        storageRepository.deleteById(id);
    }

}
