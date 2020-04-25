package birski.bookstore.models.dtos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

public class CustomUserDto implements UserDetails {

    @Email(message = "Please provide e-mail address")
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Please enter your password")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Please enter your first name")
    private String firstName;

    @NotBlank(message = "Please enter your last name")
    private String lastName;

    @NotBlank(message = "Please enter your current address")
    private String address;

    @NotBlank(message = "Please enter city")
    private String city;

    @Size(min = 6, max = 6, message = "Please provide correct zip code")
    @NotBlank(message = "Please enter zip code")
    @Pattern(regexp = "[0-9][0-9]-[0-9][0-9][0-9]", message = "Zip code should have xx-xxx format")
    private String zipCode;

    private Date create_At;

    @Transient
    private String confirmPassword;

    @PrePersist
    protected void onCreate() {
        this.create_At = new Date();
    }

    public CustomUserDto() {
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     UserDetails interface methods
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
