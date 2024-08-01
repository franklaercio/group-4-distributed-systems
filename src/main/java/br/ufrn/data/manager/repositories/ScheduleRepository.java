package br.ufrn.data.manager.repositories;

import br.ufrn.data.manager.domain.ResourceEnum;

public interface ScheduleRepository {

    void sync(String routingKey, ResourceEnum resource);
}
