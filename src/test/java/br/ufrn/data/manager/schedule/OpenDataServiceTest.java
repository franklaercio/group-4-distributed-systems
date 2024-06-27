package br.ufrn.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@DisplayName("OpenDataService tests")
@ExtendWith(MockitoExtension.class)
class OpenDataServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenDataService openDataService;

    @Test
    @DisplayName("Should call API with success")
    void callApiShouldSucceed() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class))).thenReturn(responseEntity);

        assertDoesNotThrow(() -> openDataService.callApi("/api/v1/success"));
    }

    @Test
    @DisplayName("Should handle unsuccessful API call")
    void callApiShouldHandleUnsuccessfulResponse() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class))).thenReturn(responseEntity);

        assertThrows(ApiCallException.class, () -> openDataService.callApi("/api/v1/failure"));
    }

    @Test
    @DisplayName("Should handle exception during API call")
    void callApiShouldHandleException() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class))).thenThrow(new RuntimeException());

        assertThrows(ApiCallException.class, () -> openDataService.callApi("/api/v1/exception"));
    }
}