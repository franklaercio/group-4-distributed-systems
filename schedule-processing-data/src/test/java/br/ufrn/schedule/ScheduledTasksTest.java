package br.ufrn.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("ScheduledTasks tests")
@ExtendWith(MockitoExtension.class)
class ScheduledTasksTest {

    @Mock
    private OpenDataService openDataService;

    @InjectMocks
    private ScheduledTasks scheduledTasks;

    @Test
    @DisplayName("Should call Ckan API with success")
    void callCkanShouldCallApi() throws ApiCallException {
        assertDoesNotThrow(() -> scheduledTasks.callCkan());
        verify(openDataService, times(1)).callApi("/api/v1/ckan");
    }

    @Test
    @DisplayName("Should call Dkan API with success")
    void callDkanShouldCallApi() throws ApiCallException {
        scheduledTasks.callDkan();
        verify(openDataService, times(1)).callApi("/api/v1/dkan");
    }

    @Test
    @DisplayName("Should call Socrata API with success")
    void callSocrataShouldCallApi() throws ApiCallException {
        assertDoesNotThrow(() -> scheduledTasks.callSocrata());
        verify(openDataService, times(1)).callApi("/api/v1/socrata");
    }

    @Test
    @DisplayName("Should handle API call exception when calling Ckan")
    void callCkanShouldHandleApiCallException() throws ApiCallException {
        doThrow(ApiCallException.class).when(openDataService).callApi("/api/v1/ckan");
        assertThrows(ApiCallException.class, () -> scheduledTasks.callCkan());
        verify(openDataService, times(1)).callApi("/api/v1/ckan");
    }

    @Test
    @DisplayName("Should handle API call exception when calling Dkan")
    void callDkanShouldHandleApiCallException() throws ApiCallException {
        doThrow(ApiCallException.class).when(openDataService).callApi("/api/v1/dkan");
        assertThrows(ApiCallException.class, () -> scheduledTasks.callDkan());
        verify(openDataService, times(1)).callApi("/api/v1/dkan");
    }

    @Test
    @DisplayName("Should handle API call exception when calling Socrata")
    void callSocrataShouldHandleApiCallException() throws ApiCallException {
        doThrow(ApiCallException.class).when(openDataService).callApi("/api/v1/socrata");
        assertThrows(ApiCallException.class, () -> scheduledTasks.callSocrata());
        verify(openDataService, times(1)).callApi("/api/v1/socrata");
    }
}
