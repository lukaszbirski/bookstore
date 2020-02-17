package birski.bookstore.services.dtos;

import birski.bookstore.exceptions.CoverTypeDtoNameException;
import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.mappers.CoverTypeMapper;
import birski.bookstore.models.dtos.CategoryDto;
import birski.bookstore.models.dtos.CoverTypeDto;
import birski.bookstore.repositories.CoverTypeRepository;
import birski.bookstore.services.validation.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoverTypeDtoService {

    private CoverTypeRepository coverTypeRepository;
    private CoverTypeMapper coverTypeMapper;
    private MapValidationErrorService mapValidationErrorService;

    public CoverTypeDtoService(CoverTypeRepository coverTypeRepository, CoverTypeMapper coverTypeMapper, MapValidationErrorService mapValidationErrorService) {
        this.coverTypeRepository = coverTypeRepository;
        this.coverTypeMapper = coverTypeMapper;
        this.mapValidationErrorService = mapValidationErrorService;
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

    public ResponseEntity<?> createCoverTypeDto(CoverTypeDto coverTypeDto, BindingResult bindingResult){
        ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
        if (errors != null) return errors;
        coverTypeRepository.save(coverTypeMapper.reverse(coverTypeDto));
        return new ResponseEntity<CoverTypeDto>(coverTypeDto, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateCoverTypeDto(String name, CoverTypeDto coverTypeDto, BindingResult bindingResult){
        ResponseEntity<?> errors = mapValidationErrorService.MapValidationService(bindingResult);
        if (errors != null) return errors;
        return coverTypeRepository.getCoverTypeByName(name).map(c -> {
            c.setName(coverTypeDto.getName());
            coverTypeMapper.map(coverTypeRepository.save(c));
            return new ResponseEntity<CoverTypeDto>(coverTypeDto, HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("Cover Type name: " + name + " not found."));
    }

    public ResponseEntity<?> deleteCoverTypeDto(String name){
        return coverTypeRepository.getCoverTypeByName(name).map(c -> {
            coverTypeRepository.delete(c);
            return new ResponseEntity<>("Cover Type name: " + name + " was deleted!", HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("Cover Type name: " + name + " not found."));
    }
}

//todo poprawić zabezpeczenie przed stworzeniem dwóch takich samych rekordów w create i update