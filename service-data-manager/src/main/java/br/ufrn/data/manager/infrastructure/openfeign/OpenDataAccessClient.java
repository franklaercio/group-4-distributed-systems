package br.ufrn.data.manager.infrastructure.openfeign;

import br.ufrn.data.manager.model.OpenData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${external.api.access.data.url}", name = "access-data-client", path = "/api/v1")
public interface OpenDataAccessClient {

    @GetMapping("/ckan")
    OpenData getCkanData();

    @GetMapping("/dkan")
    OpenData getDkanData();

    @GetMapping("/socrata")
    OpenData getSocrataData();
}
