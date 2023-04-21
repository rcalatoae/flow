package osp.volumeCalculator;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import osp.model.Trade;

public class CryptoVolumeStrategy implements VolumeCalculatorStrategy {

    @Override
    public void calculateVolume(List<Trade> trades) {
        Map<String, BigDecimal> volumeBySymbol = new HashMap<>();
        
        for (Trade trade : trades) {
            String symbol = trade.getListing().split("-")[0];
            BigDecimal quantity = trade.getQuantity();
            BigDecimal price = trade.getPrice();

            if (volumeBySymbol.containsKey(symbol)) {
                BigDecimal totalVolume = volumeBySymbol.get(symbol).add(quantity.multiply(price));
                volumeBySymbol.put(symbol, totalVolume);
            } else {
                volumeBySymbol.put(symbol, quantity.multiply(price));
            }
        }
        for (Map.Entry<String, BigDecimal> set : volumeBySymbol.entrySet()) {
            System.out.println(set.getKey() + ": " + set.getValue());
        }
    }
    
}
