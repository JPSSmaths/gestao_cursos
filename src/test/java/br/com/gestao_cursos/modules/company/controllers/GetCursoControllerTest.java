package br.com.gestao_cursos.modules.company.controllers;

import java.util.Arrays;
import java.util.UUID;

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
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
    public void should_not_be_able_to_get_a_course_of_a_non_existent_company() throws Exception {
        UUID nonExistentCompanyId = UUID.randomUUID();

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/company/course/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", TestUtils.generateToken(nonExistentCompanyId)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Should return courses for valid company with courses")
    public void should_return_courses_for_valid_company_with_courses() throws Exception{
        CompanyEntity companyEntity = CompanyEntity.builder()
        .username("USERNAME_TEST")
        .password("PASSOWRD_TEST")
        .email("email@gmail.com")
        .build();

        this.companyRepository.saveAndFlush(companyEntity);

        this.cursoRepository.saveAll(
            Arrays.asList(CursoEntity.builder().name("JAVA_TEST").company(companyEntity).build(),
                CursoEntity.builder().name("C_TEST").company(companyEntity).build())
        );

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/company/course/get")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", TestUtils.generateToken(companyEntity.getId()))
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("JAVA_TEST"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("C_TEST"));
    }

    @Test
    @DisplayName("Should return empty list when company has no courses")
    public void should_return_empty_list_when_company_has_no_courses() throws Exception{
        CompanyEntity company = CompanyEntity.builder()
        .username("COMPANY_TETS")
        .password("PASSWORD_TEST")
        .email("EMAIL@gmail.com")
        .build();

        this.companyRepository.saveAndFlush(company);

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/company/course/get")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", TestUtils.generateToken(company.getId()))
        )
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("Should not allow acess with inavalid token")
    public void should_not_allow_acess_with_inavalid_token() throws Exception{
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/company/course/get")
            .header("Authorization", "Bearer INVALID.TOKE.VALUE")
        ).andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("Should not allow acess without authentication token")
    public void should_not_allow_acess_without_authentication_token() throws Exception{
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/company/course/get")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
