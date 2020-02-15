package birski.bookstore.restcontrollers;

import birski.bookstore.models.CoverType;
import birski.bookstore.services.CoverTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@RequestMapping(API_URL + COVER_TYPE_URL)
public class CoverTypeRestController {

    private CoverTypeService coverTypeService;

    public CoverTypeRestController(CoverTypeService coverTypeService) {
        this.coverTypeService = coverTypeService;
    }

    @PostMapping()
    public CoverType createCoverType(@RequestBody CoverType coverType){
        return coverTypeService.createCoverType(coverType);
    }

    @GetMapping(ID_URL)
    public CoverType getCoverType(@PathVariable long id){
        return coverTypeService.getCoverTypeById(id);
    }

    @GetMapping()
    public List<CoverType> getCoverTypes(){
        return coverTypeService.getCoverTypes();
    }

    @PutMapping(ID_URL)
    public CoverType updateCoverType(@PathVariable long id, @RequestBody CoverType coverType){
        return coverTypeService.updateCoverType(id, coverType);
    }

    @DeleteMapping(ID_URL)
    public ResponseEntity<?> deleteCoverType(@PathVariable long id){
        return coverTypeService.deleteCoverType(id);
    }
}
