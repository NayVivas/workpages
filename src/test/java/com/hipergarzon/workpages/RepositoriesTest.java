package com.hipergarzon.workpages;

import com.hipergarzon.workpages.models.Admin;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.repositories.AdminRepository;
import com.hipergarzon.workpages.repositories.ItemPersonalDataRepository;
import com.hipergarzon.workpages.repositories.SeekerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {

    @Autowired
    SeekerRepository seekerRepository;

    @Test
    public void existSeekers(){
        List<Seeker> seekers = seekerRepository.findAll();
        assertThat(seekers,is(not(empty())));
    }

    @Test
    public void existSeekersName(){
        List<Seeker> seekers = seekerRepository.findAll();
        assertThat(seekers, hasItem(hasProperty("firstName", is("luis"))));
    }

    @Autowired
    AdminRepository adminRepository;

    @Test
    public void existAdminName() {
        List<Admin> admins = adminRepository.findAll();
        assertThat(admins, hasItem(hasProperty("email", is("naylu24@admin.com"))));
    }

    @Autowired
    ItemPersonalDataRepository itemPersonalDataRepository;


}
