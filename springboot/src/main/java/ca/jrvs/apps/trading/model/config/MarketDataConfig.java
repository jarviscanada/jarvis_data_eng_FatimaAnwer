package ca.jrvs.apps.trading.model.config;

// A configuration model which can be used to setup IEX HTTP client
public class MarketDataConfig {

    private String host;
    private String token;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
