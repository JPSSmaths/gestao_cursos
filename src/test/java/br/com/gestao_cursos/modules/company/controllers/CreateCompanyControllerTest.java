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

import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
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
        public void setUp() {
                this.mockMvc = MockMvcBuilders
                                .webAppContextSetup(this.webApplicationContext)
                                .build();
        }

        @Test
        @DisplayName("Should be able to create a new company")
        public void should_be_able_to_create_a_new_company() throws Exception {
                var companyDTO = CreateCompanyDTO.builder()
                                .username("COMPANY_TEST")
                                .description("DESCRIPTION_TEST")
                                .email("company@gmail.com")
                                .password("1234567890")
                                .build();

                this.mockMvc.perform(
                                MockMvcRequestBuilders.post("/company/create")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(JWTCompanyProviderTest.objectToJSON(companyDTO)))
                                .andExpect(MockMvcResultMatchers.status().isCreated());
        }

        @Test
        @DisplayName("Should not be able to create a new company with existing username")
        public void should_not_be_able_to_create_a_new_company_with_existing_username() throws Exception {
                var companyDTO = CreateCompanyDTO.builder()
                                .username("COMPANY_TEST")
                                .description("DESCRIPTION_TEST")
                                .email("email@gmail.com")
                                .password("1234567890")
                                .build();

                var existingCompany = CompanyEntity.builder()
                                .username("COMPANY_TEST")
                                .description("EXISTING_DESCRIPTION")
                                .email("existing@email.com")
                                .password("hashed_password")
                                .build();

                companyRepository.saveAndFlush(existingCompany);

                this.mockMvc.perform(
                                MockMvcRequestBuilders.post("/company/create")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(JWTCompanyProviderTest.objectToJSON(companyDTO)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        @DisplayName("Should not be able to create a new company with invalid email")
        public void should_not_be_able_to_create_a_new_company_with_invalid_email() throws Exception {
                var companyDTO = CreateCompanyDTO.builder()
                                .username("NEW_COMPANY")
                                .description("DESCRIPTION_TEST")
                                .email("invalid_email_format")
                                .password("1234567890")
                                .build();

                this.mockMvc.perform(
                                MockMvcRequestBuilders.post("/company/create")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(JWTCompanyProviderTest.objectToJSON(companyDTO)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        @DisplayName("Should not be able to create a new company with invalid username")
        public void should_not_be_able_to_create_a_new_company_with_invalid_username() throws Exception {
                var companyDTO = CreateCompanyDTO.builder()
                                .username("INVALID USERNAME")
                                .description("DESCRIPTION_TEST")
                                .email("email@gmail.com")
                                .password("1234567890")
                                .build();

                this.mockMvc.perform(
                                MockMvcRequestBuilders.post("/company/create")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(JWTCompanyProviderTest.objectToJSON(companyDTO)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        @DisplayName("Should not be able to create a new company with a passoword too short")
        public void should_not_be_able_to_create_a_new_company_with_a_passoword_too_short() throws Exception {
                var companyDTO = CreateCompanyDTO.builder()
                                .username("COMPANY_TEST")
                                .description("DESCRIPTION_TEST")
                                .email("company@gmail.com")
                                .password("123")
                                .build();

                this.mockMvc.perform(
                                MockMvcRequestBuilders.post("/company/create")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(JWTCompanyProviderTest.objectToJSON(companyDTO)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        @DisplayName("Should not be able to create a new company with a passoword too long")
        public void should_not_be_able_to_create_a_new_company_with_a_passoword_too_long() throws Exception {
                var companyDTO = CreateCompanyDTO.builder()
                                .username("COMPANY_TEST")
                                .description("DESCRIPTION_TEST")
                                .email("company@gmail.com")
                                .password("a".repeat(101))
                                .build();

                this.mockMvc.perform(
                                MockMvcRequestBuilders.post("/company/create")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(JWTCompanyProviderTest.objectToJSON(companyDTO)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        @DisplayName("Should not be able to create a compny without all required fields")
        public void should_not_be_able_to_create_a_compny_without_all_required_fields() throws Exception{
                var companyDTO = CreateCompanyDTO.builder()
                                .description("DESCRIPTION_TEST")
                                .email("company@gmail.com")
                                .password("a".repeat(101))
                                .build();

                this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/company/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JWTCompanyProviderTest.objectToJSON(companyDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        @DisplayName("Should not be able to create a compny with a existent email")
        public void should_not_be_able_to_create_a_compny_with_a_existent_email() throws Exception{
                var companyDTO = CreateCompanyDTO.builder()
                                .username("COMPANY_TEST")
                                .description("DESCRIPTION_TEST")
                                .email("existing@gmail.com")
                                .password("1234567890")
                                .build();

                var existingCompany = CompanyEntity.builder()
                                .username("COMPANY")
                                .description("EXISTING_DESCRIPTION")
                                .email("existing@email.com")
                                .password("hashed_password")
                                .build();

                companyRepository.saveAndFlush(existingCompany);

                this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/company/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JWTCompanyProviderTest.objectToJSON(companyDTO))
                ).andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

}
