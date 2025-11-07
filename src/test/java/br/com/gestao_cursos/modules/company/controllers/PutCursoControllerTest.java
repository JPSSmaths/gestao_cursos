package br.com.gestao_cursos.modules.company.controllers;

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
import br.com.gestao_cursos.modules.company.curso.Active;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.PutCursoDTO;
import br.com.gestao_cursos.modules.company.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PutCursoControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @DisplayName("Should not be able update a course without authentication token")
    public void should_not_be_able_update_a_course_without_authentication_token() throws Exception{
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/company/course/update/{course_id}", UUID.randomUUID())
        ).andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("Should not be able update a course with a invalid token")
    public void should_not_be_able_update_a_course_with_a_invalid_token() throws Exception{
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/company/course/update/{course_id}", UUID.randomUUID())
            .header("Authorization", "Bearer INVALID.TOKEN.VALUE")
        ).andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("Should be able update a existent course that have a company")
    public void should_be_able_update_a_existent_course_that_have_a_company() throws Exception{
        CompanyEntity companyEntity = CompanyEntity.builder()
        .username("USERNAME_TEST")
        .password("PASSOWRD_TEST")
        .email("EMAIL@gmail.com")
        .build();

        this.companyRepository.saveAndFlush(companyEntity);

        CursoEntity courseEntity = CursoEntity.builder()
        .name("COURSE_NAME_TEST")
        .category("CATEGORY_TEST")
        .active(Active.INACTIVE)
        .build();

        this.cursoRepository.saveAndFlush(courseEntity);

        PutCursoDTO putCourseDTO = PutCursoDTO.builder()
        .name("COURSE_NAME_UPDATED")
        .category("CATEGORY_TEST")
        .active(Active.ACTIVE)
        .build();

        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/company/course/put/{course_id}", courseEntity.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(putCourseDTO))
            .header("Authorization", TestUtils.generateToken(companyEntity.getId()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    
}
