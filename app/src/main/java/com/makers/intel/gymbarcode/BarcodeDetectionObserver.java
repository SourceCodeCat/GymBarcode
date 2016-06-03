package com.makers.intel.gymbarcode;

import com.makers.intel.gymbarcode.ui.camera.GraphicOverlay;

/**
 * Created by salgadom on 2/06/16.
 */
public interface BarcodeDetectionObserver {
    void onBarcodeDetected(BarcodeGraphic value);
}
