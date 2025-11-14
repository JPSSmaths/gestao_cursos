package br.com.gestao_cursos.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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
import br.com.gestao_cursos.modules.company.dto.AuthCompanyRequestDTO;
import br.com.gestao_cursos.modules.company.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthCompanyControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @DisplayName("Should not be able generate a token if the company don't exists")
    public void shoul_not_be_able_generate_a_token_if_the_company_dont_exists() throws Exception{
        AuthCompanyRequestDTO  authCompanyRequestDTO = AuthCompanyRequestDTO.builder()
            .username("nonexistent")
            .password("invalidpassword")
            .build();

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/company/auth/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(authCompanyRequestDTO))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Should not be able generate a token with invalid password")
    public void should_not_be_able_generate_a_token_with_invalid_password() throws Exception{
        CompanyEntity company = CompanyEntity.builder()
            .username("validcompany")
            .password("correctpassword")
            .email("EMAIL@gmail.com")
            .build();

        this.companyRepository.save(company);

        AuthCompanyRequestDTO  authCompanyRequestDTO = AuthCompanyRequestDTO.builder()
            .username("validcompany")
            .password("wrongpassword")
            .build();

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/company/auth/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(authCompanyRequestDTO))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
