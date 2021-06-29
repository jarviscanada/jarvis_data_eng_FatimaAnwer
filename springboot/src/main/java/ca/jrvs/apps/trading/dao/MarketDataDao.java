package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.util.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig){
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0) {
            return Optional.empty();
        } else if (quotes.size() == 1) {
            iexQuote = Optional.of(quotes.get(0));
        } else {
            throw new DataRetrievalFailureException("Unexpected Number of quotes");
        }
        return iexQuote;
    }

    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers) {

        // Validate all tickers first
        for(String ticker:tickers){
            if(ticker.length() > 5 || ticker.matches(".*\\d+.*")){
                throw new IllegalArgumentException("Invalid Symbol, Symbol exceeds 5 characters or contains digits.");
            }
        }

        String symbols = String.join(",", tickers);
        String uri = String.format(IEX_BATCH_URL, symbols);

        //HTTP response
        String response = executeHttpGet(uri)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

        //Array of JSON documents
        JSONObject IexQuotesJson = new JSONObject(response);

        //Get number of documents
        if (IexQuotesJson.length() == 0) {
            throw new IllegalArgumentException("Invalid ticker");
        }

        List<IexQuote> iexQuotes = new ArrayList<>();

        for (String ticker : tickers) {
            try {
                iexQuotes.add(JsonParser
                        .toObjectFromJson(IexQuotesJson.getJSONObject(ticker).getJSONObject("quote").toString(),
                                IexQuote.class));
            } catch (IOException e) {
                throw new RuntimeException("Error reading response.", e);
            }
        }
        return iexQuotes;
    }

    private Optional<String> executeHttpGet(String url){

        HttpGet httpGetRequest = new HttpGet(URI.create(url));
        HttpResponse httpResponse;
        try {
            httpResponse = getHttpClient().execute(httpGetRequest);
        } catch(IOException e){
            throw new DataRetrievalFailureException("httpGet response failed", e);
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        if (statusCode == 404) {
            return Optional.empty();
        } else if (statusCode == 200) {
            try {
                return Optional.of(EntityUtils.toString(httpResponse.getEntity()));
            } catch (IOException e) {
                throw new DataRetrievalFailureException("Cannot read response", e);
            }
        } else {
            throw new DataRetrievalFailureException("Unexpected status code: " + statusCode);
        }
    }

    private CloseableHttpClient getHttpClient(){
        return HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                //prevent connectionManager shutdown when calling httpClient.close()
                .setConnectionManagerShared(true)
                .build();
    }

    @Override
    public <S extends IexQuote> S save(S s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote iexQuote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
