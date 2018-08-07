package edu.neu.coe.neuralnetwork.filehandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author ajinkyarode
 *
 * @param <T>
 */
public class FileHandlerImpl_CSV<T extends ResultData> implements FileHandler<T> {
	
//	@Override
	public boolean writecsv(String colName,String fileName,ArrayList<T> data) {
    	File f = new File(fileName);
    	
    	try {
			f.createNewFile();
			FileWriter fWriter = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fWriter);
			bw.write(colName);
			for (T row:data) {
				bw.write(row.toFile());
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
