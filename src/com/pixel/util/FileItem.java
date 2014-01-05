package com.pixel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileItem extends File implements StreamMonitor{
	
	//call method things//

	public FileItem(File parent, String child) {
		super(parent, child);
		stream = this;
	}
	
    public FileItem(String string) {
		super(string);
		stream = this;
	}
    
    public FileItem(String parent, String child) {
    	super(parent, child);
		stream = this;
    }
    
    public FileItem(URI u) {
    	super(u);
		stream = this;
    }
    
	public FileItem(File parent, String child, StreamMonitor sm) {
		super(parent, child);
		stream = sm;
	}
	
    public FileItem(String string, StreamMonitor sm) {
		super(string);
		stream = sm;
	}
    
    public FileItem(String parent, String child, StreamMonitor sm) {
    	super(parent, child);
		stream = sm;
    }
    
    public FileItem(URI u, StreamMonitor sm) {
    	super(u);
		stream = sm;
    }
    
    
	//methods//

    public boolean create() {
    	try {
    		String path = this.getAbsolutePath();
        	String delims = "[.]+";
     		String[] file = path.split(delims);
     		if (file.length > 1) {
     			return createNewFile();
     		} else {
     			return createNewDirectory();
     		}
    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }

	public boolean createNewFile() throws IOException {
    	String path = this.getAbsolutePath();
    	String delims = "[/]+";
 		String[] dirs = path.split(delims);
 		String curPath = "";
 		File f;
 		for (int i = 1; i < dirs.length-1; i++) {
 			curPath +=  "/" + dirs[i];
 				f = new File(curPath);
 			if (!f.exists()) {
 				if (!f.mkdir())
 					return false;
 			}
 		}
 		f = new File(path);
 		return f.createNewFile();
     }
	
    public boolean createNewDirectory() throws IOException {
    	return mkdir();
    }

    public boolean move(String path) {
    	return renameTo(new File(path));
    }
    
    public boolean move(File f) {
    	return renameTo(f);
    }

    public boolean copy(String path) {
    	try {
			return copyDirectory(this, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean copySafe(String path) {
    	try {
			return safeCopyDirectory(this, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean copy(File f) {
    	try {
			return copyDirectory(this, f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean copySafe(File f) {
    	try {
			return safeCopyDirectory(this, f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
	
	public boolean delete() {
		if (isDirectory()) {
			return deleteFolder(new File(this.getAbsolutePath()));
		} else {
			File f = new File(this.getAbsolutePath());
			return f.delete();
		}
	}
	
	private boolean deleteFolder(File directory) {

		  if (directory == null)
		    return false;
		  if (!directory.exists())
		    return true;
		  if (!directory.isDirectory())
		    return false;

		  String[] list = directory.list();

		  if (list != null) {
		    for (int i = 0; i < list.length; i++) {
		      File entry = new File(directory, list[i]);

		      if (entry.isDirectory())
		      {
		        if (!deleteFolder(entry))
		          return false;
		      }
		      else
		      {
		        if (!entry.delete())
		          return false;
		      }
		    }
		  }

		  return directory.delete();
	}
	
	public boolean copyDirectory(File srcPath, File dstPath) throws IOException{
		if (srcPath.isDirectory())
		{
			if (!dstPath.exists())
			{
				if (!dstPath.mkdir())
					return false;
			}

			String files[] = srcPath.list();
			for(int i = 0; i < files.length; i++)
			{
				if (!copyDirectory(new File(srcPath, files[i]), new File(dstPath, files[i])))
					return false;
			}
		}
		else
		{
			if(!srcPath.exists())
			{
				return false;
			}
			else
			{
				InputStream in = new MonitoredInputStream(srcPath, this);
		        OutputStream out = new MonitoredOutputStream(dstPath, this);
    
				// Transfer bytes from in to out
		        byte[] buf = new byte[1024];
				int len;
		        while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
		        out.close();
			}
		}
		return true;
	}
	
	public boolean safeCopyDirectory(File srcPath, File dstPath) throws IOException{
		if (dstPath.exists()) {
			return false;
		}
		if (srcPath.isDirectory())
		{
			if (!dstPath.exists())
			{
				if (!dstPath.mkdir())
					return false;
			}

			String files[] = srcPath.list();
			for(int i = 0; i < files.length; i++)
			{
				if (!copyDirectory(new File(srcPath, files[i]), new File(dstPath, files[i])))
					return false;
			}
		}
		else
		{
			if(!srcPath.exists())
			{
				return false;
			}
			else
			{
				InputStream in = new MonitoredInputStream(srcPath, this);
		        OutputStream out = new MonitoredOutputStream(dstPath, this);
    
				// Transfer bytes from in to out
		        byte[] buf = new byte[1024];
				int len;
		        while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
		        out.close();
			}
		}
		return true;
	}
	
	public boolean zip() {
	    try {
			ZipOutputStream zip = null;
			MonitoredOutputStream fileWriter = null;

			fileWriter = new MonitoredOutputStream(getAbsolutePath() + ".zip", this);
			zip = new ZipOutputStream(fileWriter);

			addFolderToZip("", getAbsolutePath(), zip);
			zip.flush();
			zip.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	  }

	 private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
	      throws Exception {

	    File folder = new File(srcFile);
	    if (folder.isDirectory()) {
	      addFolderToZip(path, srcFile, zip);
	    } else {
	      byte[] buf = new byte[1024];
	      int len;
		@SuppressWarnings("resource")
		FileInputStream in = new MonitoredInputStream(srcFile, this);
	      zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
	      while ((len = in.read(buf)) > 0) {
	        zip.write(buf, 0, len);
	      }
	    }
	  }

	 private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
	      throws Exception {
	    File folder = new File(srcFolder);

	    for (String fileName : folder.list()) {
	      if (path.equals("")) {
	        addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
	      } else {
	        addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
	      }
	    }
	  }
	
	@SuppressWarnings("resource")
	public boolean unzip() {
		  ZipFile zipFile;
			try {
				zipFile = new ZipFile(getAbsolutePath());

			  File jiniHomeParentDir = new File(getAbsolutePath().substring(0, getAbsolutePath().lastIndexOf("/")));
			  Enumeration<?> files = zipFile.entries();
			    File f = null;
			    MonitoredOutputStream fos = null;
			    
			    while (files.hasMoreElements()) {
			      try {
			        ZipEntry entry = (ZipEntry) files.nextElement();
			        if (!(entry.getName().contains("__MACOSX"))) {
			        InputStream eis = zipFile.getInputStream(entry);
			        byte[] buffer = new byte[1024];
			        int bytesRead = 0;
			  
			        f = new File(jiniHomeParentDir.getAbsolutePath() + File.separator + entry.getName());
			        
			        if (entry.isDirectory()) {
			          f.mkdirs();
			          continue;
			        } else {
			          f.getParentFile().mkdirs();
			          f.createNewFile();
			        }
			        
			        fos = new MonitoredOutputStream(f, this);
			  
			        while ((bytesRead = eis.read(buffer)) != -1) {
			          fos.write(buffer, 0, bytesRead);
			        }
			        }
			      } catch (IOException e) {
			        e.printStackTrace();
					return false;
			      } finally {
			        if (fos != null) {
			          try {
			            fos.close();
			          } catch (IOException e) {
			            // ignore
			          }
			        }
			      }
			    }
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
		return true;
	}
	
	//variables//
	
	private static final long serialVersionUID = 1L;

	@Override
	public void bytesTransfered(int i) {
	}

	@Override
	public void KBsTransfered(int i) {
	}

	@Override
	public void MBsTransfered(int i) {

	}

	@Override
	public void GBsTransfered(int i) {
	}

	public StreamMonitor stream;

}
