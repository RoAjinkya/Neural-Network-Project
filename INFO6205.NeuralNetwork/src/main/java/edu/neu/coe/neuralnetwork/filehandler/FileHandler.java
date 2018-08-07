package edu.neu.coe.neuralnetwork.filehandler;

import java.util.ArrayList;


/**
 * @author ajinkyarode
 *
 * @param <T>
 */
public interface FileHandler <T extends ResultData> {
	public boolean writecsv(String colName,String fileName,ArrayList<T> data);

}
