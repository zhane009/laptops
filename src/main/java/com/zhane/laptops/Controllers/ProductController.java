package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.*;
import com.zhane.laptops.Repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CpuRepository cpuRepository;

    @Autowired
    GraphicsRepository graphicsRepository;

    @Autowired
    RamRepository ramRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    StorageRepository storageRepository;

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable long id) {
        return productRepository.getOne(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.saveAndFlush(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        Product oldProduct = productRepository.getOne(product.getId());
        BeanUtils.copyProperties(product, oldProduct);
        return productRepository.saveAndFlush(oldProduct);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteById(@PathVariable long id) {
        productRepository.deleteById(id);
    }

    @PostMapping(value = "cpu")
    public Product addCpu(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Cpu> cpus = product.getAvailableCpus();
        if(cpus == null){
            cpus = new HashSet<>();
        }
        cpus.add(cpuRepository.getOne(payload.get("cpuId")));
        product.setAvailableCpus(cpus);
        return productRepository.saveAndFlush(product);
    }

    @DeleteMapping(value = "cpu")
    public Product deleteCpu(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Cpu> cpus = product.getAvailableCpus();
        if (cpus == null){
            cpus = new HashSet<>();
        }
        cpus.removeIf(cpu -> cpu.getId() == payload.get("cpuId"));
        product.setAvailableCpus(cpus);
        return productRepository.saveAndFlush(product);
    }

    @PostMapping(value = "graphics")
    public Product addGraphics(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Graphics> graphics = product.getAvailableGraphics();
        if (graphics == null){
            graphics = new HashSet<>();
        }
        graphics.add(graphicsRepository.getOne(payload.get("graphicsId")));
        product.setAvailableGraphics(graphics);
        return productRepository.saveAndFlush(product);
    }

    @DeleteMapping(value = "graphics")
    public Product deleteGraphics(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Graphics> graphics = product.getAvailableGraphics();
        if (graphics == null){
            graphics = new HashSet<>();
        }
        graphics.removeIf(graphics1 -> graphics1.getId() == payload.get("cpuId"));
        product.setAvailableGraphics(graphics);
        return productRepository.saveAndFlush(product);
    }

    @PostMapping(value = "ram")
    public Product addRam(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Ram> rams = product.getAvailableRams();
        if (rams == null){
            rams = new HashSet<>();
        }
        rams.add(ramRepository.getOne(payload.get("ramId")));
        product.setAvailableRams(rams);
        return productRepository.saveAndFlush(product);
    }

    @DeleteMapping(value = "ram")
    public Product deleteRam(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Ram> rams = product.getAvailableRams();
        if (rams == null){
            rams = new HashSet<>();
        }
        rams.removeIf(ram -> ram.getId() == payload.get("ramId"));
        product.setAvailableRams(rams);
        return productRepository.saveAndFlush(product);
    }

    @PostMapping(value = "screen")
    public Product addScreen(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Screen> screens = product.getAvailableScreens();
        if (screens == null){
            screens = new HashSet<>();
        }
        screens.add(screenRepository.getOne(payload.get("screenId")));
        product.setAvailableScreens(screens);
        return productRepository.saveAndFlush(product);
    }

    @DeleteMapping(value = "screen")
    public Product deleteScreen(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Screen> screens = product.getAvailableScreens();
        if (screens == null){
            screens = new HashSet<>();
        }
        screens.removeIf(screen -> screen.getId() == payload.get("screenId"));
        product.setAvailableScreens(screens);
        return productRepository.saveAndFlush(product);
    }

    @PostMapping(value = "storage")
    public Product addStorage(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Storage> storages = product.getAvailableStorages();
        if (storages == null){
            storages = new HashSet<>();
        }
        storages.add(storageRepository.getOne(payload.get("storageId")));
        product.setAvailableStorages(storages);
        return productRepository.saveAndFlush(product);
    }

    @DeleteMapping(value = "storage")
    public Product deleteStorage(@RequestBody Map<String, Long> payload){
        Product product = productRepository.getOne(payload.get("productId"));
        Set<Storage> storages = product.getAvailableStorages();
        if (storages == null){
            storages = new HashSet<>();
        }
        storages.removeIf(storage -> storage.getId() == payload.get("storageId"));
        product.setAvailableStorages(storages);
        return productRepository.saveAndFlush(product);
    }

}
