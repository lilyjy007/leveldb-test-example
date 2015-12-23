package leveldbtest;

import org.iq80.leveldb.*;
import org.iq80.leveldb.impl.Iq80DBFactory;
//import static org.iq80.leveldb.impl.Iq80DBFactory.*;
import java.io.*;
import java.nio.charset.Charset;

public class test {
	public static void main(String[] args) throws IOException{
	//boolean cleanup = true;
	Charset charset = Charset.forName("utf-8");
	//String path = "/data/leveldb";
	DBFactory factory = new Iq80DBFactory();
	Options options = new Options();
	options.createIfMissing(true);
	DB db = factory.open(new File("example"), options);
	try {
		WriteOptions writeOptions = new WriteOptions().sync(true);
		db.put("key-001".getBytes(charset),"XIAOHONG".getBytes(charset),writeOptions);

		//write后立即进行磁盘同步写
		//WriteOptions writeOptions = new WriteOptions().sync(true);  //线程安全
		db.put("key-002".getBytes(charset),"XIAOMING".getBytes(charset),writeOptions);

		//read
		byte[] bv = db.get("key-01".getBytes(charset));
		if(bv != null && bv.length > 0) {
		    String value = new String(bv,charset);
		    System.out.println(value); // Use the db in here....
		}
	}finally {
		  // Make sure you close the db to shutdown the 
		  // database and avoid resource leaks.
		  db.close();
		}
	}
}

