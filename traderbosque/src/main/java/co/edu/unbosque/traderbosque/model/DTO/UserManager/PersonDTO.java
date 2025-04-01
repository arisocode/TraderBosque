package co.edu.unbosque.traderbosque.model.DTO.UserManager;

public class PersonDTO {
    
    private String name, lastName, nit, phone, email, address;

    public PersonDTO(String name, String lastName, String nit, String phone,
                        String email, String address){
        this.name=name;
        this.lastName=lastName;
        this.nit=nit;
        this.phone=phone;
        this.email=email;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
