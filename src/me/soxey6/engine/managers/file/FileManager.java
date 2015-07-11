package me.soxey6.engine.managers.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.xml.sax.ErrorHandler;



/**
 * This is the FileManager, all file functions are put here.
 * All functions should be threaded but aren't at this time.
 */
public class FileManager{

	private static FileManager fileManager;
	private String dataDirectory="data/";

	public FileManager()
	{
		fileManager=this;
	}
	
	/**
	 * Creates a file with nothing inside
	 * @param path relative to current position
	 * @param file name
	 * @return A code, if not 0 then something went wrong
	 * @see ErrorHandler
	 */
	public int createFile(String path, String name)
	{
		return 0;
	}
	
	/**
	 * Appends the content to the end of the file
	 * @param path relative to current position
	 * @param content to append
	 * @return A code, if not 0 then something went wrong
	 * @throws IOException 
	 * @see ErrorHandler
	 */
	public int appendFile(String path, String content) throws IOException
	{
		Writer writer;
		writer = new BufferedWriter(new FileWriter(path, true));
		writer.append(content);
		writer.close();
		return 0;
	}
	
	/**
	 * Writes over a current file with the content specified
	 * @param path relative to current position
	 * @param content to write
	 * @return A code, if not 0 then something went wrong
	 * @throws IOException 
	 * @see ErrorHandler
	 */
	public int overWriteFile(String path, String content) throws IOException
	{
		Writer writer;
		writer = new BufferedWriter(new FileWriter(path));
		writer.append(content);
		writer.close();
		return 0;
	}
	
	/**
	 * Reads a whole file and returns its contents
	 * @param path relative to current position
	 * @return the content of the file
	 */
	public String readFile(String path) throws IOException
	{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		return new String(data, "UTF-8");
		
	}
	
	/**
	 * Deletes a file
	 * @param path relative to current position
	 * @return A code, if not 0 then something went wrong
	 * @see ErrorHandler
	 */
	public int deleteFile(String path)
	{
		return 0;
	}
	
	public File getFilePath(Files files, String fileName)
	{
		switch(files)
		{
		case SOUND:
			return new File(getDataDirectory()+"/sound/",fileName);
		case MUSIC:
			return new File(getDataDirectory()+"/music/",fileName);
		case SAVE:
			return new File(getDataDirectory()+"/saves/",fileName);
		default:
			return null;
		}
	}


	public static FileManager getFileManager() {
		return fileManager;
	}

	public static void setFileManager(FileManager fileHandler) {
		FileManager.fileManager = fileHandler;
	}

	public String getDataDirectory() {
		return dataDirectory;
	}

	public void setDataDirectory(String dataDirectory) {
		this.dataDirectory = dataDirectory;
	}
}
