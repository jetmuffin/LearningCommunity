package com.lst.LearningCommunity;

import org.apache.hadoop.hbase.CompoundConfiguration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.junit.Test;

public class TestHbase {
	 public static CompoundConfiguration configuration;  
	    static {  
	        configuration = (CompoundConfiguration) HBaseConfiguration.create();  
	        configuration.set("hbase.zookeeper.property.clientPort", "2181");  
	        configuration.set("hbase.zookeeper.quorum", "localhost");  
	        configuration.set("hbase.master", "localhost");  
	    }  
	    
	@Test
	public void test(){
		HTablePool pool = new HTablePool(configuration, 1000);  
        HTable table = (HTable) pool.getTable("123"); 
	}
}
