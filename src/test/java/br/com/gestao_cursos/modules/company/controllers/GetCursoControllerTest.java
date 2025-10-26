package br.com.gestao_cursos.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.dto.AuthCompanyRequestDTO;
import br.com.gestao_cursos.modules.company.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GetCursoControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @DisplayName("Should not be able to get a course of a non existent company")
    public void should_not_be_able_to_get_a_course_of_a_non_existent_company() throws Exception{
        AuthCompanyRequestDTO authCompanyRequestDTO = AuthCompanyRequestDTO.builder().username("USERNAME_TEST")
                .password("PASSWORD_TEST").build();

        CompanyEntity company = this.companyRepository.findByUsernameOrEmail(authCompanyRequestDTO.getUsername(), null)
        .orElse(null);

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/company/course/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(authCompanyRequestDTO))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
