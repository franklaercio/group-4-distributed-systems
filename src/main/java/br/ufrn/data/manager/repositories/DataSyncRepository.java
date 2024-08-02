package br.ufrn.data.manager.repositories;

public interface DataSyncRepository {

    void synchronizeData(String routingKey, String resource);
}
