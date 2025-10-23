package br.com.gestao_cursos.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.dto.CreateCompanyDTO;
import br.com.gestao_cursos.modules.company.utils.JWTCompanyProviderTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateCompanyControllerTest {
    
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .build();
    }

    @Test
    @DisplayName("Should be able to create a new company")
    public void should_be_able_to_create_a_new_company() throws Exception{
        var companyDTO = CreateCompanyDTO.builder()
                        .username("COMPANY_TEST")
                        .description("DESCRIPTION_TEST")
                        .email("company@gmail.com")
                        .password("1234567890")
                        .build();
        
        var result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/company/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JWTCompanyProviderTest.objectToJSON(companyDTO))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
