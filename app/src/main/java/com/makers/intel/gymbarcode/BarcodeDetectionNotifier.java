package com.makers.intel.gymbarcode;

/**
 * Created by salgadom on 2/06/16.
 */
public interface BarcodeDetectionNotifier {

    void registerObserver(BarcodeDetectionObserver barcodeDetectionObserver);
    void removeObserver();
}
