package br.ufrn.data.manager.repositories;

import br.ufrn.data.manager.model.OpenData;

public interface OpenDataRepository {

    OpenData sync(String datasource);
}
