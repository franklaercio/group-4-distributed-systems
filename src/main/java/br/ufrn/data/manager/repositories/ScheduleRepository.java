package br.ufrn.data.manager.repositories;

public interface ScheduleRepository {

    void sync(String routingKey, String resource);
}
