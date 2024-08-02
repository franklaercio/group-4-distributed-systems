package br.ufrn.data.manager.infrastructure.openfeign;

import br.ufrn.data.manager.domain.OpenDataEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cacheClient", url = "${external.api.cache.url}")
public interface CacheClient {

    @PostMapping("/api/v1/cache")
    ResponseEntity<Void> createCache(@RequestHeader("X-Application-Token") String token,
                     @RequestBody OpenDataEntity openData);
}
