package br.ufrn.data.manager;

import br.ufrn.data.manager.domain.OpenDataResource;
import br.ufrn.data.manager.infrastructure.openfeign.CacheClient;
import br.ufrn.data.manager.infrastructure.openfeign.OpenDataClient;
import br.ufrn.data.manager.repositories.MessageQueueRepository;
import br.ufrn.data.manager.services.OpenDataSyncService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("Unit tests for OpenDataSyncService")
@ExtendWith(MockitoExtension.class)
class OpenDataSyncServiceTest {

    @Mock
    private OpenDataClient openDataClient;

    @Mock
    private CacheClient cacheClient;

    @Mock
    private MessageQueueRepository messageQueueRepository;

    @InjectMocks
    private OpenDataSyncService openDataSyncService;

    @Test
    @DisplayName("Should synchronize data successfully")
    void synchronizeData_successful() {
        String routingKey = "testRoutingKey";
        String datasource = "testDatasource";
        OpenDataResource openDataResource = new OpenDataResource();
        openDataResource.setId("testId");

        when(openDataClient.getData(datasource)).thenReturn(openDataResource);
        when(cacheClient.createCache(any(), any())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        doNothing().when(messageQueueRepository).sendMessage(eq(routingKey), eq(openDataResource.getId()));

        openDataSyncService.synchronizeData(routingKey, datasource);

        verify(openDataClient).getData(datasource);
        verify(cacheClient).createCache(any(), any());
        verify(messageQueueRepository).sendMessage(eq(routingKey), eq(openDataResource.getId()));
    }

    @Test
    @DisplayName("Should throw exception when cache creation fails")
    void synchronizeData_cacheCreationFails() {
        String routingKey = "testRoutingKey";
        String datasource = "testDatasource";
        OpenDataResource openDataResource = new OpenDataResource();
        openDataResource.setId("testId");

        ResponseEntity<Void> cacheResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(openDataClient.getData(datasource)).thenReturn(openDataResource);
        when(cacheClient.createCache(any(), any())).thenReturn(cacheResponse);

        assertThrows(RuntimeException.class, () -> openDataSyncService.synchronizeData(routingKey, datasource));

        verify(openDataClient).getData(datasource);
        verify(cacheClient).createCache(any(), any());
        verify(messageQueueRepository, never()).sendMessage(any(), any());
    }

    @Test
    @DisplayName("Should throw exception when data fetch fails")
    void synchronizeData_exceptionThrown() {
        String routingKey = "testRoutingKey";
        String datasource = "testDatasource";

        when(openDataClient.getData(datasource)).thenThrow(new RuntimeException("Data fetch error"));

        assertThrows(RuntimeException.class, () -> openDataSyncService.synchronizeData(routingKey, datasource));

        verify(openDataClient).getData(datasource);
        verify(cacheClient, never()).createCache(any(), any());
        verify(messageQueueRepository, never()).sendMessage(any(), any());
    }
}
