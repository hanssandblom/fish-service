package se.iths.weblab2;

import java.util.List;
import java.util.Optional;

public class TestService implements Service {
    @Override
    public List<FishDto> getAllFish() {
        return List.of(new FishDto(1, "Larre", "Lax", "Hane", 10),
                new FishDto(2, "Salmonella", "Lax", "Hona", 8));
    }

    @Override
    public Optional<FishDto> getOne(Integer id) {
        if( id == 1)
            return Optional.of(new FishDto(1, "Larre", "Lax", "Hane", 10));
        return Optional.empty();
    }

    @Override
    public FishDto createFish(FishDto fish) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public FishDto replace(Integer id, FishDto fishDto) {
        return null;
    }

    @Override
    public FishDto update(Integer id, FishDto fishDto) {
        return null;
    }
}
