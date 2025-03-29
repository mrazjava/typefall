package com.mrazjava.typefall.csi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CsiScreen {

    
    public void print(CSI csi) {
    	
    	log.debug(csi.getClass().getSimpleName() + "." + csi);
    	System.out.print(csi.value());
    }
}
