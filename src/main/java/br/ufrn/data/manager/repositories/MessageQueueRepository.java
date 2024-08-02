package br.ufrn.data.manager.repositories;

public interface MessageQueueRepository {

    void sendMessage(String queue, Object message);
}
