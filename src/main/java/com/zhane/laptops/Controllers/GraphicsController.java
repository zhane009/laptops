package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.Graphics;
import com.zhane.laptops.Repositories.GraphicsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("graphics")
public class GraphicsController {
    @Autowired
    GraphicsRepository graphicsRepository;

    @GetMapping
    public List<Graphics> getAll(){
        return graphicsRepository.findAll();
    }

    @GetMapping("{id}")
    public Graphics getById(@PathVariable long id){
        return graphicsRepository.getOne(id);
    }

    @PostMapping
    public Graphics saveGraphics(@RequestBody Graphics graphics){
        return graphicsRepository.saveAndFlush(graphics);
    }

    @PutMapping
    public Graphics updateGraphics(@RequestBody Graphics graphics){
        Graphics oldGraphics = graphicsRepository.getOne(graphics.getId());
        BeanUtils.copyProperties(graphics, oldGraphics,  "manufacturer");
        return graphicsRepository.saveAndFlush(oldGraphics);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteGraphics(@PathVariable long id){
        graphicsRepository.deleteById(id);
    }
}
