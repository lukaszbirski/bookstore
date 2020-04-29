package birski.bookstore.mappers;

import antlr.collections.List;
import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.daos.Role;
import birski.bookstore.models.dtos.CustomUserDto;
import birski.bookstore.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserMapper implements Mapper<CustomUser, CustomUserDto>{

    private RoleRepository roleRepository;

    public CustomUserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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
        customUserDto.setZipCode(from.getZip_code());
        customUserDto.setCity(from.getCity());
        Set<String> roles = new HashSet<>();
        Set<Role> roleSet = roleRepository.getRolesByUsers(from);
        for (Role role : roleSet) {
            roles.add(role.getRoleName());
        }
        customUserDto.setRoles(roles);

        return customUserDto;
    }

    @Override
    public CustomUser reverse(CustomUserDto to) {

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRoleName("USER");
        roles.add(role);

        CustomUser customUser = new CustomUser();
        customUser.setUsername(to.getUsername());
        customUser.setPassword(to.getPassword());
        customUser.setConfirmPassword(to.getConfirmPassword());
        customUser.setFirstName(to.getFirstName());
        customUser.setLastName(to.getLastName());
        customUser.setCreate_At(to.getCreate_At());
        customUser.setAddress(to.getAddress());
        customUser.setCity(to.getCity());
        customUser.setZip_code(to.getZipCode());
        customUser.setRoles(roles);

        return customUser;
    }
}
