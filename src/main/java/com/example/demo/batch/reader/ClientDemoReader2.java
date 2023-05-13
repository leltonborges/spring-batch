package com.example.demo.batch.reader;

import com.example.demo.model.Client;
import com.example.demo.service.project.ClientService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Iterator;

@Component
@StepScope
public class ClientDemoReader2
        implements ItemReader<Client> {
    private final ClientService clientService;
    private Pageable paginacao;
    private Iterator<Client> dadosIterator;


    @Autowired
    public ClientDemoReader2(ClientService clientService) {
        paginacao = PageRequest.of(0, 10);
        this.clientService = clientService;
    }

    @Override
    public Client read() {
        pageData();
        return dadosIterator.hasNext() ? dadosIterator.next() : null;
    }

    private void pageData() {
        if (dadosIterator == null || !dadosIterator.hasNext()) {
            Page<Client> queryPage = this.clientService.queryPage(paginacao);
            dadosIterator = queryPage.iterator();
            setNextPage(queryPage);
        }
    }

    private void setNextPage(final Page<Client> queryPage) {
        if (checkNextPage(queryPage))
            paginacao = queryPage.nextPageable();
    }

    private static boolean checkNextPage(final Page<Client> queryPage) {
        return queryPage.hasNext();
    }

}
