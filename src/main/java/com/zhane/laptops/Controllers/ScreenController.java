package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.Screen;
import com.zhane.laptops.Repositories.ScreenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("screen")
public class ScreenController {

    @Autowired
    ScreenRepository screenRepository;

    @GetMapping
    public List<Screen> getAll(){
        return screenRepository.findAll();
    }

    @GetMapping("{id}")
    public Screen getById(@PathVariable long id){
        return screenRepository.getOne(id);
    }

    @PostMapping
    public Screen saveScreen(@RequestBody Screen screen){
        return screenRepository.saveAndFlush(screen);
    }

    @PutMapping
    public Screen updateScreen(@RequestBody Screen screen){
        Screen oldScreen = screenRepository.getOne(screen.getId());
        BeanUtils.copyProperties(screen, oldScreen, "technology");
        return screenRepository.saveAndFlush(oldScreen);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteScreen(@PathVariable long id){
        screenRepository.deleteById(id);
    }

}
