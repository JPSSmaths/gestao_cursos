package br.com.gestao_cursos.modules.company.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class DeleteCursoControllerTest {
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
    @DisplayName("Should be able detete a course of a existent company")
    public void should_be_able_detete_a_course_of_a_existent_company() throws Exception{
        CompanyEntity companyEntity = CompanyEntity.builder()
        .username("USERNAME_TEST")
        .password("PASSWORD_TEST")
        .email("EMAIL@gmail.com")
        .build();

        this.companyRepository.saveAndFlush(companyEntity);

        CursoEntity cursoEntity  = CursoEntity.builder()
        .company(companyEntity).build();

        this.cursoRepository.saveAndFlush(cursoEntity);

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/company/course/delete/{id}", cursoEntity.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", TestUtils.generateToken(companyEntity.getId()))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        assertFalse(this.cursoRepository.findById(cursoEntity.getId()).isPresent());
        assertTrue(this.companyRepository.findById(companyEntity.getId()).isPresent());
    }

    
}
