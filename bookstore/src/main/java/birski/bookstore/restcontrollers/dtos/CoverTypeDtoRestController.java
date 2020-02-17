package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.dtos.CoverTypeDto;
import birski.bookstore.services.dtos.CoverTypeDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@RequestMapping(API_URL + DTO_URL + COVER_TYPE_URL)
public class CoverTypeDtoRestController {

    private CoverTypeDtoService coverTypeDtoService;

    public CoverTypeDtoRestController(CoverTypeDtoService coverTypeDtoService) {
        this.coverTypeDtoService = coverTypeDtoService;
    }

    @GetMapping()
    public List<CoverTypeDto> getCoverTypesDto(){
        return coverTypeDtoService.getCoverTypesDto();
    }

    @GetMapping(NAME_URL)
    public CoverTypeDto getCoverTypeDto(@PathVariable String name){
        return coverTypeDtoService.getCoverTypeDto(name);
    }

    @PostMapping()
    public ResponseEntity<?> createCoverTypeDto(@Valid @RequestBody CoverTypeDto coverTypeDto, BindingResult bindingResult){
        return coverTypeDtoService.createCoverTypeDto(coverTypeDto, bindingResult);
    }

    @PutMapping(NAME_URL)
    public ResponseEntity<?> updateCoverTypeDto(@PathVariable ("name") String name, @Valid @RequestBody CoverTypeDto coverTypeDto, BindingResult bindingResult){
        return coverTypeDtoService.updateCoverTypeDto(name, coverTypeDto, bindingResult);
    }

    @DeleteMapping(NAME_URL)
    public ResponseEntity<?> deleteCategoryDto(@PathVariable ("name") String name){
        return coverTypeDtoService.deleteCoverTypeDto(name);
    }
}