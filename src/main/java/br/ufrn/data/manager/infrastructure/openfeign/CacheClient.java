package br.ufrn.data.manager.infrastructure.openfeign;

import br.ufrn.data.manager.domain.OpenDataEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${external.api.cache.url}", name = "cache-client", path = "/cache")
public interface CacheClient {

    @PostMapping
    ResponseEntity<Void> createCache(OpenDataEntity openDataEntity);
}