package com.pixel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MonitoredInputStream extends FileInputStream {

	public MonitoredInputStream(File path, StreamMonitor im) throws FileNotFoundException {
		super(path);
		ism = im;
	}
	
	public MonitoredInputStream(String path, StreamMonitor im) throws FileNotFoundException {
		super(path);
		ism = im;
	}
	
    public int read() throws IOException {
    	transfered ++;
    	ism.bytesTransfered(transfered);
    	if (transfered / 1000 == 1) {
    		kb ++;
        	ism.KBsTransfered(kb);
        	if (kb /1000 == 1) {
        		mb++;
        		ism.MBsTransfered(mb);
        		if (mb /1000 == 1) {
            		gb++;
            		ism.GBsTransfered(gb);
            	}
        	}
    	}
    	return super.read();
    }
    
    public void clear() {
    	transfered = 0;
    	kb = 0;
    	mb = 0;
    	gb = 0;
    }
    
    
    
    public int transfered = 0;
    public int kb = 0;
    public int mb = 0;
    public int gb = 0;
    public StreamMonitor ism;

}
