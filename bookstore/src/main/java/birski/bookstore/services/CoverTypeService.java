package birski.bookstore.services;

import birski.bookstore.exceptions.ResourceNotFoundException;
import birski.bookstore.models.CoverType;
import birski.bookstore.repositories.CoverTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverTypeService {

    private CoverTypeRepository coverTypeRepository;

    public CoverTypeService(CoverTypeRepository coverTypeRepository) {
        this.coverTypeRepository = coverTypeRepository;
    }

    public CoverType createCoverType(CoverType coverType){
        return coverTypeRepository.save(coverType);
    }

    public CoverType getCoverTypeById(long id){
        return coverTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CoverType id: " + id + " not found."));
    }

    public List<CoverType> getCoverTypes(){
        return coverTypeRepository.findAll();
    }

    public CoverType updateCoverType(long id, CoverType coverType){
        return coverTypeRepository.findById(id).map(c -> {
            c.setName(coverType.getName());
            return coverTypeRepository.save(c);
        }).orElseThrow(() -> new ResourceNotFoundException("CoverType id: " + id + " not found."));
    }

    public ResponseEntity<?> deleteCoverType(long id){
        return coverTypeRepository.findById(id).map(b -> {
            coverTypeRepository.deleteById(id);
            return new ResponseEntity<>("CoverType id: " + id + " was deleted!", HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("CoverType id: " + id + " not found."));
    }
}
