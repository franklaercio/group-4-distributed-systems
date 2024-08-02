package br.ufrn.data.manager.services;

import br.ufrn.data.manager.domain.OpenDataEntity;
import br.ufrn.data.manager.domain.Tax;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class TaxService {
    private final List<Tax> taxInfoList = Arrays.asList(
            new Tax(1, "Janeiro", "IRPF", 20000, "2024"),
            new Tax(2, "Fevereiro", "IRPJ", 30000, "2024"),
            new Tax(3, "Março", "ISI", 25000, "2024"),
            new Tax(4, "Abril", "IRPF", 15000, "2024"),
            new Tax(5, "Maio", "IRPJ", 18000, "2024"),
            new Tax(6, "Junho", "ISI", 22000, "2024"),
            new Tax(7, "Julho", "IRPJ", 25000, "2024"),
            new Tax(8, "Janeiro", "ISI", 20000, "2024"),
            new Tax(9, "Fevereiro", "IRPF", 15000, "2024")
    );

    public OpenDataEntity getRandomTax(String datasource) {
        Random random = new Random();
        int index = random.nextInt(taxInfoList.size());

        OpenDataEntity openData = new OpenDataEntity();
        openData.setId(UUID.randomUUID().toString());
        openData.setDatabase(datasource.toLowerCase());
        openData.setData("teste");

        return openData;
    }
}
