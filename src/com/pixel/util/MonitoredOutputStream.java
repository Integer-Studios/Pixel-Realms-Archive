package com.pixel.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MonitoredOutputStream extends FileOutputStream{

	public MonitoredOutputStream(File file, StreamMonitor m) throws FileNotFoundException {
		super(file);
		ism = m;
	}
	
	public MonitoredOutputStream(String file, StreamMonitor m) throws FileNotFoundException {
		super(file);
		ism = m;
	}
	
	public void write(int b) throws IOException {
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
    	super.write(b);
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
