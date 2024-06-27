package br.ufrn.data.manager.repositories;

public interface MessageRepository {

    void send(String queue, Object message);
}
