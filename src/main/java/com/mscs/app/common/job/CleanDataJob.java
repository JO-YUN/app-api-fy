package com.mscs.app.common.job;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mscs.app.common.util.PropertiesUtil;
import com.mscs.app.common.util.TimeUtil;
import com.mscs.app.web.service.FileService;


@Component("cleanDataJob")
public class CleanDataJob {
	public static Logger logger = LoggerFactory.getLogger(CleanDataJob.class);
	
	@Autowired
	private  FileService fileService;
	static int iFile = 0;//清理的空文件夹的个数
	/**
	 * 清除脏数据附件临时文件job
	 * 表示每个星期六早上1点 
	 * @throws IOException
	 */
	//"0 30 23 L * ?" 每月最后一日的晚上23:30触发 
	//配置开通即可进行清理
	//@Scheduled(cron = "0 0 01 ? * SAT")
	public void cleanAttrAndData() throws IOException {
		// 任务一清理附件
		
		System.out.println("系统清理附件任务开始：" + TimeUtil.getTime());
		logger.info("系统清理附件任务开始：" + TimeUtil.getTime());
		try {
			//查出所有失效的附件	将失效的附件数据删除+删除物理路径下面失效的图片
			List<Map<String, String>> fileStateInvalidList= fileService.queryStateInvalidList();
			if (fileStateInvalidList.size() > 0) {
				for (Map<String, String> fileList : fileStateInvalidList) {
					// 删除单个文件 "D:/file/allfile/2018-10-09/21663639160991.png";
					System.out.println(fileList.get("physicalPath"));
					try{
						 fileService.deleteStateInvalidByAttachId(fileList.get("attachId"));
						 //清除文件
						 CleanDataJob.deleteFile(fileList.get("physicalPath"));	
					}catch (Exception e) {
						e.printStackTrace();
					}
				
				}
			}
			
			// 清理没有文件的空文件夹
			Properties  properties=PropertiesUtil.getKey("system.properties");
			String rootDir=properties.getProperty("baseDir");//根目录D:\\file
			//String rootDir = "D:\\file\\allfile";
			File dir = new File(rootDir);
			CleanDataJob.clear(dir);
			System.out.println("共清理了" + iFile + "个空文件夹");		
			logger.info("共清理了" + iFile + "个空文件夹");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("系统清理附件任务结束：" + TimeUtil.getTime());
		logger.info("系统清理附件任务结束：" + TimeUtil.getTime());
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 * @throws IOException
	 */
	//假设是D:\\file\\allfile\\2018-10-16\\a.jpg,b.jpg
	//D:\\file\\allfile\\2018-10-17\\a.jpg
	private static boolean deleteDir(File dir) throws IOException {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 
	 * @param path要删除的文件路径
	 * @param key过滤不删除的路径
	 * @return
	 * @throws IOException
	 */
	private static boolean dealDir(String path, String key, String separator) throws IOException {
		// 获得根目录路径
		File rootDir = new File(path);
		// 获得儿子名字集合
		String[] suns = rootDir.list();
		// 删除成功
		boolean success = true;
		if (suns != null) {
			for (String sun : suns) {
				// 扣除关键字，删除其他数据
				File sunDir = new File(rootDir + separator + sun);
				if (!key.equals(sun)) {
					success = deleteDir(sunDir);
				}
			}
		}
		return success;
	}
	

	public static void main(String[] args) {
		
		
		// 清理文件空文件夹
		String dir_str = "D:\\file";
		File dir = new File(dir_str);
		File[] files=dir.listFiles();
		for (File file : files) {
			System.out.println("子文件"+file.getName());
		}
		/*CleanDataJob.clear(dir);
		System.out.println("共清理了" + iFile + "个空文件夹");*/
		
	}
	//假设是D:\\file\\allfile\\2018-10-16\\a.jpg,b.jpg
		//D:\\file\\allfile\\2018-10-17\\a.jpg
		/*private static boolean deleteEmptyDir(File dir) throws IOException {
			if (dir.isDirectory()) {//根目录
				if() {//如果目录里面没有文件或者文件夹
					File[] files=dir.listFiles();//根目录下面的所有子文件+文件夹
					for (File file : files) {
						System.out.println("子文件"+file.getName());
						if(file.isDirectory()) {
							boolean success =deleteEmptyDir(file);// 递归删除
							if (!success) {
								return false;
							}
						}
					}
				}else {
					// 目录此时为空，可以删除
					dir.delete();
				}
			}
			
		}*/

	/**
	 * 删除文件，可以是文件或文件夹
	 *
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = CleanDataJob.deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = CleanDataJob.deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}

	public static void clear(File dir) {
		File[] dirs = dir.listFiles();//得到目录下所有文件和目录的绝对路径，返回的是File数组
	
		for (int i = 0; i < dirs.length; i++) {
			if (dirs[i].isDirectory()) {
				clear(dirs[i]);
			}
		}
		if (dir.isDirectory() && dir.delete()) {
			iFile++;
			System.out.println(dir + "清理成功");
		}

		
		
	}

}
