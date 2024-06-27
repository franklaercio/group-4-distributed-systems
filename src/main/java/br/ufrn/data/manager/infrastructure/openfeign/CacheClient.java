package br.ufrn.data.manager.infrastructure.openfeign;

import br.ufrn.data.manager.model.OpenData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${external.api.cache.url}", name = "cache-client", path = "/api/v1")
public interface CacheClient {

    @PostMapping("/cache")
    ResponseEntity<Void> createCache(OpenData openData);
}
