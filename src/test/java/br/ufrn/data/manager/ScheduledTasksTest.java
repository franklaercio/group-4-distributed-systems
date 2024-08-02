package br.ufrn.data.manager;

import br.ufrn.data.manager.domain.ResourceEnum;
import br.ufrn.data.manager.infrastructure.configs.RabbitMQProperties;
import br.ufrn.data.manager.schedule.ScheduledTasks;
import br.ufrn.data.manager.services.OpenDataSyncService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduledTasksTest {

    @Mock
    private OpenDataSyncService openDataSyncService;

    @Mock
    private RabbitMQProperties rabbitMQProperties;

    @InjectMocks
    private ScheduledTasks scheduledTasks;

    @Test
    void testCallOpenDataApis() {
        when(rabbitMQProperties.getCkanRoutingKey()).thenReturn("ckan.routing.key");
        when(rabbitMQProperties.getSocrataRoutingKey()).thenReturn("socrata.routing.key");

        scheduledTasks.callOpenDataApis();

        verify(openDataSyncService, times(1)).synchronizeData(eq("ckan.routing.key"), eq(ResourceEnum.CKAN.name()));
        verify(openDataSyncService, times(1)).synchronizeData(eq("socrata.routing.key"), eq(ResourceEnum.SOCRATA.name()));
        verify(openDataSyncService, never()).synchronizeData(eq("dkan.routing.key"), eq(ResourceEnum.DKAN.name()));
    }
}

