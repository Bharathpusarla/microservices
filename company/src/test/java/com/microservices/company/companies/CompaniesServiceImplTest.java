package com.microservices.company.companies;

import com.microservices.company.companies.Impl.CompaniesServiveImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompaniesServiceImplTest {
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompaniesServiveImpl companiesServive;

    @Test
   void getallcompaniesTest(){

        // Arrange
        List<Company> mockCompanies = List.of(
                new Company(1L, "Company A", "Tech"),
                new Company(2L, "Company B", "Finance")
        );
        when(companyRepository.findAll()).thenReturn(mockCompanies);

        // Act
        List<Company> result = companiesServive.getallcompanies();

        // Assert
        assertNotNull(result); // Ensure the result is not null
        assertEquals(2, result.size()); // Ensure the result has the correct number of companies
        assertEquals("Company A", result.get(0).getName()); // Verify details of the first company
        verify(companyRepository, times(1)).findAll();
    }

}
