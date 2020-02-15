package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.CoverTypeMapper;
import birski.bookstore.models.dtos.CoverTypeDto;
import birski.bookstore.repositories.CoverTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoverTypeDtoService {

    private CoverTypeRepository coverTypeRepository;
    private CoverTypeMapper coverTypeMapper;

    public CoverTypeDtoService(CoverTypeRepository coverTypeRepository, CoverTypeMapper coverTypeMapper) {
        this.coverTypeRepository = coverTypeRepository;
        this.coverTypeMapper = coverTypeMapper;
    }

    public List<CoverTypeDto> getCoverTypesDto(){
        List<CoverTypeDto> coverTypeDtos = new ArrayList<>();
        coverTypeRepository.findAll().forEach(c -> coverTypeDtos.add(coverTypeMapper.map(c)));
        return coverTypeDtos;
    }

    public CoverTypeDto getCoverTypeDto(String name){
        return coverTypeRepository.getCoverTypeByName(name).map(c -> {
            CoverTypeDto coverTypeDto = coverTypeMapper.map(c);
            return coverTypeDto;
        }).orElseThrow(() -> new ResourceNotFoundException("Category name: " + name + " not found."));
    }

    public ResponseEntity<CoverTypeDto> createCoverTypeDto(CoverTypeDto coverTypeDto){
        coverTypeRepository.save(coverTypeMapper.reverse(coverTypeDto));
        return new ResponseEntity<CoverTypeDto>(coverTypeDto, HttpStatus.CREATED);
    }

    public CoverTypeDto updateCoverTypeDto(String name, CoverTypeDto coverTypeDto){
        return coverTypeRepository.getCoverTypeByName(name).map(c -> {
            c.setName(coverTypeDto.getName());
            return coverTypeMapper.map(coverTypeRepository.save(c));
        }).orElseThrow(() -> new ResourceNotFoundException("Cover Type name: " + name + " not found."));
    }

    public ResponseEntity<?> deleteCoverTypeDto(String name){
        return coverTypeRepository.getCoverTypeByName(name).map(c -> {
            coverTypeRepository.delete(c);
            return new ResponseEntity<>("Cover Type name: " + name + " was deleted!", HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("Cover Type name: " + name + " not found."));
    }
}

//todo do dodawania dodać zabezpieczenie przez stworzeniem dwóch takich samych rekordów