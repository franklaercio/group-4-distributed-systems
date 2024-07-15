package br.ufrn.data.manager.infrastructure.openfeign;

import br.ufrn.data.manager.domain.OpenDataEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${external.api.access.data.url}", name = "access-data-client", path = "/api/v1")
public interface OpenDataAccessClient {

    @GetMapping("/opendata/{datasource}")
    OpenDataEntity getOpenDataByDatasource(@PathVariable String datasource);

}
