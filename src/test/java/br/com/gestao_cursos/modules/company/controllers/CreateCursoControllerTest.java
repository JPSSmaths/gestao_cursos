package br.com.gestao_cursos.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CreateCursoControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @DisplayName("Should not be able create a course without authentication token")
    public void should_not_be_able_create_a_course_without_authentication_token() throws Exception{
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/company/course/create")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Should not be able create a course with a invalid token")
    public void should_not_be_able_create_a_course_with_a_invalid_token() throws Exception{
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/company/course/create")
            .header("Authorization", "INVALID TOKEN VALUE")
        ).andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    
}
