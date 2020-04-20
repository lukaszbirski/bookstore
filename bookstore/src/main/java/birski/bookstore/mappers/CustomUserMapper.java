package birski.bookstore.mappers;

import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.dtos.CustomUserDto;
import org.springframework.stereotype.Component;

@Component
public class CustomUserMapper implements Mapper<CustomUser, CustomUserDto>{

    public CustomUserMapper() {
    }

    @Override
    public CustomUserDto map(CustomUser from) {

        CustomUserDto customUserDto = new CustomUserDto();
        customUserDto.setUsername(from.getUsername());
        customUserDto.setPassword(from.getPassword());
        customUserDto.setConfirmPassword(from.getConfirmPassword());
        customUserDto.setFirstName(from.getFirstName());
        customUserDto.setLastName(from.getLastName());
        customUserDto.setCreate_At(from.getCreate_At());
        customUserDto.setAddress(from.getAddress());
        return customUserDto;
    }

    @Override
    public CustomUser reverse(CustomUserDto to) {

        CustomUser customUser = new CustomUser();
        customUser.setUsername(to.getUsername());
        customUser.setPassword(to.getPassword());
        customUser.setConfirmPassword(to.getConfirmPassword());
        customUser.setFirstName(to.getFirstName());
        customUser.setLastName(to.getLastName());
        customUser.setCreate_At(to.getCreate_At());
        customUser.setAddress(to.getAddress());

        return customUser;
    }
}
