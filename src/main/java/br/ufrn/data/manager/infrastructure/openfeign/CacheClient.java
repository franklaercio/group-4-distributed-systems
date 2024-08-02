package br.ufrn.data.manager.infrastructure.openfeign;

import br.ufrn.data.manager.domain.OpenDataEntity;
import br.ufrn.data.manager.infrastructure.configs.FeignConfig;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${external.api.cache.url}", name = "cache-client", path = "/api/v1/cache", configuration = FeignConfig.class)
public interface CacheClient {

    @PostMapping
    @Headers("X-Application-Token: {token}")
    ResponseEntity<Void> createCache(@Param("token") String token, OpenDataEntity openDataEntity);
}
