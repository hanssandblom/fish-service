package se.iths.weblab2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FishService implements se.iths.weblab2.Service {

    public FishRepository fishRepository;

    public FishService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    public List<FishDto> getAllFish(){
        return mapp(fishRepository.findAll());
    }

    public Optional<FishDto> getOne(Integer id){
        return mapp(fishRepository.findById(id));
    }

    public FishDto createFish(FishDto fish){
        if (fish.getName().isEmpty())
            throw new RuntimeException();
        return mapp(fishRepository.save(mapp(fish)));
    }

    public void delete(Integer id) {
        fishRepository.deleteById(id);
    }

    public FishDto replace(Integer id, FishDto fishDto) {
        Optional<Fish> fish = fishRepository.findById(id);
        if(fish.isPresent())
        {
            Fish updateFish = fish.get();
            updateFish.setName(fishDto.getName());
            updateFish.setType(fishDto.getType());
            updateFish.setGender(fishDto.getGender());
            updateFish.setWeight(fishDto.getWeight());
            return mapp(fishRepository.save(updateFish));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    public FishDto update(Integer id, FishDto fishDto) {
        Optional<Fish> fish = fishRepository.findById(id);
        if( fish.isPresent())
        {
            Fish updateFish = fish.get();
            if( fishDto.getName() != null) {
                updateFish.setName(fishDto.getName());
            }
            if( fishDto.getType() != null) {
                updateFish.setType(fishDto.getType());
            }
            if( fishDto.getGender() != null) {
                updateFish.setGender(fishDto.getGender());
            }
            if( fishDto.getWeight() != 0) {
                updateFish.setWeight(fishDto.getWeight());
            }
            return mapp(fishRepository.save(updateFish));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    public FishDto mapp(Fish fish){
        return new FishDto(fish.getId(), fish.getName(), fish.getType(), fish.getGender(), fish.getWeight());
    }

    public Fish mapp(FishDto fishDto){
        return new Fish(fishDto.getId(), fishDto.getName(), fishDto.getType(), fishDto.getGender(), fishDto.getWeight());
    }

    public Optional<FishDto> mapp(Optional<Fish> optionalFish){
        if (optionalFish.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalFish.get()));
    }

    public List<FishDto> mapp(List<Fish> all){
        List<FishDto> fishDtoList = new ArrayList<>();
        for (var fish : all){
            fishDtoList.add(mapp(fish));
        }
        return fishDtoList;
    }

}
