package com.example.demo.batch.reader;

import com.example.demo.model.Client;
import com.example.demo.model.Domain;
import com.example.demo.model.Transaction;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component("clientByTransactionReader")
public class ClientByTransactionReader
        implements ItemStreamReader<Domain>,
        ResourceAwareItemReaderItemStream<Domain> {
    private Object objActual;
    private final FlatFileItemReader<Domain> delegate;

    public ClientByTransactionReader(FlatFileItemReader<Domain> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Domain read() throws Exception {
        Client client = null;
        if (objActual == null) objActual = delegate.read();

        if (objActual instanceof Client client2) client = client2;

        objActual = null;
        while (peek() instanceof Transaction) {
            assert client != null;
            client.getTransactions().add((Transaction) objActual);
//            client.getClientTransaction().add((ClientTransaction) objActual);
        }
        return client;
    }

    private Object peek() throws Exception {
        objActual = delegate.read();
        return objActual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}
