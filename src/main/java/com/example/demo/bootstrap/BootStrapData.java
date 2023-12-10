package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;



    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Division az = new Division();
        az.setId(2L);
        az.setCountry_id(1L);
        Customer ken = new Customer();
        ken.setFirstName("Ken");
        ken.setLastName("Doll");
        ken.setPhone("999");
        ken.setPostal_code("9090");
        ken.setAddress("45 ln");
        ken.setDivisions(az);
        divisionRepository.save(az);
        customerRepository.save(ken);

        Customer rick = new Customer();
        rick.setFirstName("Rick");
        rick.setLastName("Man");
        rick.setPhone("998");
        rick.setPostal_code("9098");
        rick.setAddress("43 ln");
        rick.setDivisions(az);
        divisionRepository.save(az);
        customerRepository.save(rick);

        Customer sam = new Customer();
        sam.setFirstName("Sam");
        sam.setLastName("Well");
        sam.setPhone("994");
        sam.setPostal_code("9094");
        sam.setAddress("49 ln");
        sam.setDivisions(az);
        divisionRepository.save(az);
        customerRepository.save(sam);

        Customer mel = new Customer();
        mel.setFirstName("Mel");
        mel.setLastName("fay");
        mel.setPhone("991");
        mel.setPostal_code("9091");
        mel.setAddress("41 ln");
        mel.setDivisions(az);
        divisionRepository.save(az);
        customerRepository.save(mel);

        Customer tia = new Customer();
        tia.setFirstName("Tia");
        tia.setLastName("Yun");
        tia.setPhone("995");
        tia.setPostal_code("9095");
        tia.setAddress("40 ln");
        tia.setDivisions(az);
        divisionRepository.save(az);
        customerRepository.save(tia);





        System.out.println("Started in Bootstrap");
        System.out.println("Number of customers: " + customerRepository.count());
    }
}
