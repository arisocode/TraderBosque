package co.edu.unbosque.traderbosque.service.alpaca.implementation;


import co.edu.unbosque.traderbosque.model.DTO.alpaca.*;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockRestServiceServer
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockRestServiceServer server;

    @Test
    void testCreateUser_Success() throws Exception {
        AccountDTO dto = new AccountDTO();

        ContactDTO contact = new ContactDTO();
        contact.setEmailAddress("juan111@example.com"); //Toca cambiar esto seguido
        contact.setPhoneNumber("3001234567");
        contact.setStreetAddress(List.of("20 N San Mateo Dr"));
        contact.setCity("San Mateo");
        contact.setState("CA");
        contact.setPostalCode("94401");

        IdentityDTO identity = new IdentityDTO();
        identity.setGivenName("Juan");
        identity.setFamilyName("Perez");
        identity.setDateOfBirth("1990-01-01");
        identity.setTaxId("444-55-4321");
        identity.setTaxIdType("USA_SSN");
        identity.setFundingSource(List.of("employment_income"));
        identity.setTaxResidence("USA");

        DisclosuresDTO disclosures = new DisclosuresDTO();


        AgreementDTO customerAgreement = new AgreementDTO();
        customerAgreement.setAgreement("customer_agreement");
        customerAgreement.setSignedAt("2023-01-01T00:00:00Z");
        customerAgreement.setIpAddress("127.0.0.1");

        AgreementDTO marginAgreement = new AgreementDTO();
        marginAgreement.setAgreement("margin_agreement");
        marginAgreement.setSignedAt("2023-01-01T00:00:00Z");
        marginAgreement.setIpAddress("127.0.0.1");

        List<AgreementDTO> agreements = List.of(customerAgreement, marginAgreement);
        dto.setAgreements(agreements);

        DocumentDTO document = new DocumentDTO();
        document.setDocumentType("identity_verification");

        String base64Content = "iVBORw0KGgoAAAANSUhEUgAAAAUA"
                + "AAAFCAYAAACNbyblAAAAHElEQVQI12P4"
                + "//8/w38GIAXDIBKE0DHxgljNBAAO"
                + "9TXL0Y4OHwAAAABJRU5ErkJggg==";

        document.setContent(base64Content);
        document.setMimeType("image/png");
        List<DocumentDTO> documents = List.of(document);

        TrustedContactDTO trusted = new TrustedContactDTO();
        trusted.setGivenName("Maria");
        trusted.setFamilyName("Gonzalez");
        trusted.setEmailAddress("maria@example.com");

        dto.setContact(contact);
        dto.setIdentity(identity);
        dto.setDisclosures(disclosures);
        dto.setAgreements(agreements);
        dto.setDocuments(documents);
        dto.setTrustedContact(trusted);
        dto.setEnabledAssets(List.of("us_equity"));
        dto.setAccountType("trading");
        dto.setTradingType("cash");

        dto.setUsername("juanp");
        dto.setPassword("supersegura123");



        AlpacaAccountResponseDTO responseDTO = new AlpacaAccountResponseDTO();
        responseDTO.setId("alpaca-id-123");
        responseDTO.setStatus("APPROVED");

        server.expect(ExpectedCount.once(),
                        requestTo("https://broker-api.sandbox.alpaca.markets/v1/accounts"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(
                        new ObjectMapper().writeValueAsString(responseDTO),
                        MediaType.APPLICATION_JSON
                ));

        AlpacaAccountResponseDTO result = userService.create(dto);

        assertNotNull(result.getId());
        assertFalse(result.getId().isEmpty());
        assertTrue(userRepository.findByEmail(dto.getContact().getEmailAddress()).isPresent());
    }
}

