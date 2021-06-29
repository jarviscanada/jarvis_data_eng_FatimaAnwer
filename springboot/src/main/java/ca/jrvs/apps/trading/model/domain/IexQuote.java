package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "primaryExchange",
        "calculationPrice",
        "open",
        "openTime",
        "openSource",
        "close",
        "closeTime",
        "closeSource",
        "high",
        "highTime",
        "highSource",
        "low",
        "lowTime",
        "lowSource",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpdate",
        "latestVolume",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexLastUpdated",
        "delayedPrice",
        "delayedPriceTime",
        "oddLotDelayedPrice",
        "oddLotDelayedPriceTime",
        "extendedPrice",
        "extendedChange",
        "extendedChangePercent",
        "extendedPriceTime",
        "previousClose",
        "previousVolume",
        "change",
        "changePercent",
        "volume",
        "iexMarketPercent",
        "iexVolume",
        "avgTotalVolume",
        "iexBidPrice",
        "iexBidSize",
        "iexAskPrice",
        "iexAskSize",
        "iexOpen",
        "iexOpenTime",
        "iexClose",
        "iexCloseTime",
        "marketCap",
        "peRatio",
        "week52High",
        "week52Low",
        "ytdChange",
        "lastTradeTime",
        "isUSMarketOpen"
})

public class IexQuote {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("primaryExchange")
    private String primaryExchange;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("open")
    private double open;
    @JsonProperty("openTime")
    private long openTime;
    @JsonProperty("openSource")
    private String openSource;
    @JsonProperty("close")
    private double close;
    @JsonProperty("closeTime")
    private long closeTime;
    @JsonProperty("closeSource")
    private String closeSource;
    @JsonProperty("high")
    private double high;
    @JsonProperty("highTime")
    private long highTime;
    @JsonProperty("highSource")
    private String highSource;
    @JsonProperty("low")
    private double low;
    @JsonProperty("lowTime")
    private long lowTime;
    @JsonProperty("lowSource")
    private String lowSource;
    @JsonProperty("latestPrice")
    private double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private long latestUpdate;
    @JsonProperty("latestVolume")
    private int latestVolume;
    @JsonProperty("iexRealtimePrice")
    private double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private int iexRealtimeSize;
    @JsonProperty("iexLastUpdated")
    private long iexLastUpdated;
    @JsonProperty("delayedPrice")
    private double delayedPrice;
    @JsonProperty("delayedPriceTime")
    private long delayedPriceTime;
    @JsonProperty("oddLotDelayedPrice")
    private double oddLotDelayedPrice;
    @JsonProperty("oddLotDelayedPriceTime")
    private long oddLotDelayedPriceTime;
    @JsonProperty("extendedPrice")
    private double extendedPrice;
    @JsonProperty("extendedChange")
    private double extendedChange;
    @JsonProperty("extendedChangePercent")
    private double extendedChangePercent;
    @JsonProperty("extendedPriceTime")
    private long extendedPriceTime;
    @JsonProperty("previousClose")
    private double previousClose;
    @JsonProperty("previousVolume")
    private int previousVolume;
    @JsonProperty("change")
    private double change;
    @JsonProperty("changePercent")
    private double changePercent;
    @JsonProperty("volume")
    private int volume;
    @JsonProperty("iexMarketPercent")
    private double iexMarketPercent;
    @JsonProperty("iexVolume")
    private int iexVolume;
    @JsonProperty("avgTotalVolume")
    private int avgTotalVolume;
    @JsonProperty("iexBidPrice")
    private int iexBidPrice;
    @JsonProperty("iexBidSize")
    private int iexBidSize;
    @JsonProperty("iexAskPrice")
    private int iexAskPrice;
    @JsonProperty("iexAskSize")
    private int iexAskSize;
    @JsonProperty("iexOpen")
    private double iexOpen;
    @JsonProperty("iexOpenTime")
    private long iexOpenTime;
    @JsonProperty("iexClose")
    private double iexClose;
    @JsonProperty("iexCloseTime")
    private long iexCloseTime;
    @JsonProperty("marketCap")
    private long marketCap;
    @JsonProperty("peRatio")
    private double peRatio;
    @JsonProperty("week52High")
    private double week52High;
    @JsonProperty("week52Low")
    private double week52Low;
    @JsonProperty("ytdChange")
    private double ytdChange;
    @JsonProperty("lastTradeTime")
    private long lastTradeTime;
    @JsonProperty("isUSMarketOpen")
    private boolean isUSMarketOpen;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("primaryExchange")
    public String getPrimaryExchange() {
        return primaryExchange;
    }

    @JsonProperty("primaryExchange")
    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    @JsonProperty("calculationPrice")
    public String getCalculationPrice() {
        return calculationPrice;
    }

    @JsonProperty("calculationPrice")
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @JsonProperty("open")
    public double getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(double open) {
        this.open = open;
    }

    @JsonProperty("openTime")
    public long getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("openSource")
    public String getOpenSource() {
        return openSource;
    }

    @JsonProperty("openSource")
    public void setOpenSource(String openSource) {
        this.openSource = openSource;
    }

    @JsonProperty("close")
    public double getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(double close) {
        this.close = close;
    }

    @JsonProperty("closeTime")
    public long getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("closeSource")
    public String getCloseSource() {
        return closeSource;
    }

    @JsonProperty("closeSource")
    public void setCloseSource(String closeSource) {
        this.closeSource = closeSource;
    }

    @JsonProperty("high")
    public double getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(double high) {
        this.high = high;
    }

    @JsonProperty("highTime")
    public long getHighTime() {
        return highTime;
    }

    @JsonProperty("highTime")
    public void setHighTime(long highTime) {
        this.highTime = highTime;
    }

    @JsonProperty("highSource")
    public String getHighSource() {
        return highSource;
    }

    @JsonProperty("highSource")
    public void setHighSource(String highSource) {
        this.highSource = highSource;
    }

    @JsonProperty("low")
    public double getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(double low) {
        this.low = low;
    }

    @JsonProperty("lowTime")
    public long getLowTime() {
        return lowTime;
    }

    @JsonProperty("lowTime")
    public void setLowTime(long lowTime) {
        this.lowTime = lowTime;
    }

    @JsonProperty("lowSource")
    public String getLowSource() {
        return lowSource;
    }

    @JsonProperty("lowSource")
    public void setLowSource(String lowSource) {
        this.lowSource = lowSource;
    }

    @JsonProperty("latestPrice")
    public double getLatestPrice() {
        return latestPrice;
    }

    @JsonProperty("latestPrice")
    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    @JsonProperty("latestSource")
    public String getLatestSource() {
        return latestSource;
    }

    @JsonProperty("latestSource")
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    @JsonProperty("latestTime")
    public String getLatestTime() {
        return latestTime;
    }

    @JsonProperty("latestTime")
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    @JsonProperty("latestUpdate")
    public long getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("latestVolume")
    public int getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(int latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("iexRealtimePrice")
    public double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    @JsonProperty("iexRealtimePrice")
    public void setIexRealtimePrice(double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    @JsonProperty("iexRealtimeSize")
    public int getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    @JsonProperty("iexRealtimeSize")
    public void setIexRealtimeSize(int iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    @JsonProperty("iexLastUpdated")
    public long getIexLastUpdated() {
        return iexLastUpdated;
    }

    @JsonProperty("iexLastUpdated")
    public void setIexLastUpdated(long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    @JsonProperty("delayedPrice")
    public double getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("delayedPriceTime")
    public long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPrice")
    public double getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPrice")
    public void setOddLotDelayedPrice(double oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public long getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public void setOddLotDelayedPriceTime(long oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }

    @JsonProperty("extendedPrice")
    public double getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedChange")
    public double getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(double extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("extendedChangePercent")
    public double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("extendedPriceTime")
    public long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("previousClose")
    public double getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("previousVolume")
    public int getPreviousVolume() {
        return previousVolume;
    }

    @JsonProperty("previousVolume")
    public void setPreviousVolume(int previousVolume) {
        this.previousVolume = previousVolume;
    }

    @JsonProperty("change")
    public double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(double change) {
        this.change = change;
    }

    @JsonProperty("changePercent")
    public double getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("volume")
    public int getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @JsonProperty("iexMarketPercent")
    public double getIexMarketPercent() {
        return iexMarketPercent;
    }

    @JsonProperty("iexMarketPercent")
    public void setIexMarketPercent(double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    @JsonProperty("iexVolume")
    public int getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(int iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("avgTotalVolume")
    public int getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(int avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    @JsonProperty("iexBidPrice")
    public int getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(int iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    @JsonProperty("iexBidSize")
    public int getIexBidSize() {
        return iexBidSize;
    }

    @JsonProperty("iexBidSize")
    public void setIexBidSize(int iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    @JsonProperty("iexAskPrice")
    public int getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(int iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    @JsonProperty("iexAskSize")
    public int getIexAskSize() {
        return iexAskSize;
    }

    @JsonProperty("iexAskSize")
    public void setIexAskSize(int iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    @JsonProperty("iexOpen")
    public double getIexOpen() {
        return iexOpen;
    }

    @JsonProperty("iexOpen")
    public void setIexOpen(double iexOpen) {
        this.iexOpen = iexOpen;
    }

    @JsonProperty("iexOpenTime")
    public long getIexOpenTime() {
        return iexOpenTime;
    }

    @JsonProperty("iexOpenTime")
    public void setIexOpenTime(long iexOpenTime) {
        this.iexOpenTime = iexOpenTime;
    }

    @JsonProperty("iexClose")
    public double getIexClose() {
        return iexClose;
    }

    @JsonProperty("iexClose")
    public void setIexClose(double iexClose) {
        this.iexClose = iexClose;
    }

    @JsonProperty("iexCloseTime")
    public long getIexCloseTime() {
        return iexCloseTime;
    }

    @JsonProperty("iexCloseTime")
    public void setIexCloseTime(long iexCloseTime) {
        this.iexCloseTime = iexCloseTime;
    }

    @JsonProperty("marketCap")
    public long getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("peRatio")
    public double getPeRatio() {
        return peRatio;
    }

    @JsonProperty("peRatio")
    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    @JsonProperty("week52High")
    public double getWeek52High() {
        return week52High;
    }

    @JsonProperty("week52High")
    public void setWeek52High(double week52High) {
        this.week52High = week52High;
    }

    @JsonProperty("week52Low")
    public double getWeek52Low() {
        return week52Low;
    }

    @JsonProperty("week52Low")
    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    @JsonProperty("ytdChange")
    public double getYtdChange() {
        return ytdChange;
    }

    @JsonProperty("ytdChange")
    public void setYtdChange(double ytdChange) {
        this.ytdChange = ytdChange;
    }

    @JsonProperty("lastTradeTime")
    public long getLastTradeTime() {
        return lastTradeTime;
    }

    @JsonProperty("lastTradeTime")
    public void setLastTradeTime(long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    @JsonProperty("isUSMarketOpen")
    public boolean isIsUSMarketOpen() {
        return isUSMarketOpen;
    }

    @JsonProperty("isUSMarketOpen")
    public void setIsUSMarketOpen(boolean isUSMarketOpen) {
        this.isUSMarketOpen = isUSMarketOpen;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
