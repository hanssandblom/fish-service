package se.iths.weblab2.services;

import se.iths.weblab2.dtos.FishDto;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<FishDto> getAllFish();

    Optional<FishDto> getOne(Integer id);

    FishDto createFish(FishDto fish);

    void delete(Integer id);

    FishDto replace(Integer id, FishDto fishDto);

    FishDto update(Integer id, FishDto fishDto);

}
