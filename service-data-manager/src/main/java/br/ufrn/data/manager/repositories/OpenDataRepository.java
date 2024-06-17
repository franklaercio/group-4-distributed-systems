package br.ufrn.data.manager.repositories;

import br.ufrn.data.manager.model.OpenData;

public interface OpenDataRepository {

    OpenData syncCkan();

    OpenData syncDkan();

    OpenData syncSocrata();
}
