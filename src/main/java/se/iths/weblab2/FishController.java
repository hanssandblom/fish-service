package se.iths.weblab2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class FishController {

    private final Service service;

    public FishController(Service service) {
        this.service = service;
    }

    @GetMapping("/fish")
    public List<FishDto> all(){
        return service.getAllFish();
    }

    @GetMapping("/fish/{id}")
    public FishDto one(@PathVariable Integer id){
        return service.getOne(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find id " + id));
    }

    @PostMapping("/fish")
    @ResponseStatus(HttpStatus.CREATED)
    public FishDto create(@RequestBody FishDto fish){
        return service.createFish(fish);
    }

    @DeleteMapping("/fish/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

    @PutMapping("/fish/{id}")
    public FishDto replace(@RequestBody FishDto fishDto, @PathVariable Integer id) {
        return service.replace(id, fishDto);
    }

    @PatchMapping("/fish/{id}")
    public FishDto update(@RequestBody FishDto fishDto, @PathVariable Integer id) {
        return service.update(id, fishDto);
    }
/*
    @RequestMapping(value ="/fish/search", method = RequestMethod.GET)
    public FishDto search(@RequestParam (value = "search", required = false)FishDto fishDto) {
        return fishDto;
    }
*/
}