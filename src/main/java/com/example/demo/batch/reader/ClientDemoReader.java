package com.example.demo.batch.reader;

import com.example.demo.model.Client;
import com.example.demo.service.project.ClientService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ClientDemoReader
        implements ItemReader<Client> {
    private final ClientService clientService;

    private Page<Client> produtos;

    private int indiceProdutoAtual;

    @Autowired
    public ClientDemoReader(ClientService clientService) {
        this.indiceProdutoAtual = 0;
        this.clientService = clientService;
    }


    @Override
    public Client read() {
        if (produtos == null)
            produtos = clientService.queryPage(PageRequest.of(0, 2));

        if (isLastPage())
            return null;

        Client produtoAtual = getItemAtual();
        nextPage();

        return produtoAtual;
    }

    private void nextPage() {
        if (checkNextPage()) {
            produtos = clientService.queryPage(produtos.nextPageable());
            indiceProdutoAtual = 0;
        }
    }

    private boolean checkNextPage() {
        return indiceProdutoAtual == produtos.getNumberOfElements() && produtos.hasNext();
    }

    private Client getItemAtual() {
        Client produtoAtual = produtos.getContent().get(indiceProdutoAtual);
        indiceProdutoAtual++;
        return produtoAtual;
    }

    private boolean isLastPage() {
        return indiceProdutoAtual >= produtos.getNumberOfElements();
    }
}
