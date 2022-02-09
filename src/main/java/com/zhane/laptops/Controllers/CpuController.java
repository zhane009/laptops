package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.Cpu;
import com.zhane.laptops.Repositories.CpuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("cpu")
public class CpuController {
    @Autowired
    CpuRepository cpuRepository;

    @GetMapping
    public List<Cpu> getAll(){
        return cpuRepository.findAll();
    }

    @GetMapping("{id}")
    public Cpu getById(@PathVariable long id){
        return cpuRepository.getOne(id);
    }

    @PostMapping
    public Cpu saveCpu(@RequestBody Cpu cpu){
        return cpuRepository.saveAndFlush(cpu);
    }

    @PutMapping
    public Cpu updateCpu(@RequestBody Cpu cpu){
        Cpu oldCpu = cpuRepository.getOne(cpu.getId());
        BeanUtils.copyProperties(cpu, oldCpu, "baseClockSpeed","maxClockSpeed");
        return cpuRepository.saveAndFlush(oldCpu);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteById(@PathVariable long id){
        cpuRepository.deleteById(id);
    }
}
