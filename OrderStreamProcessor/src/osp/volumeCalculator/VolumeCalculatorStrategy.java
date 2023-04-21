package osp.volumeCalculator;
import java.util.List;

import osp.model.Trade;

public interface VolumeCalculatorStrategy {
    public void calculateVolume(List<Trade> trades);
}
