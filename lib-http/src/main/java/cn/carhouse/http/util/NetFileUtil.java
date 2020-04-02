package cn.carhouse.http.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * 创建目录
 */

public class NetFileUtil {

    // 文件
    public static final String DIR_FILE = "file";
    private static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 创建文件目录
     *
     * @param dirName 目录名称
     * @return
     */
    public static File createCacheDir(String dirName) {
        if (mContext == null) {
            throw new RuntimeException("请在Application调用init，初始化FileUtils");
        }
        if (TextUtils.isEmpty(dirName)) {
            dirName = DIR_FILE;
        }
        File file = null;
        if (checkSDExist()) {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), dirName);
        } else {
            file = new File(mContext.getFilesDir(), dirName);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.exists()) {
            return mContext.getFilesDir();
        }
        return file;
    }


    /**
     * 创建文件的存放目录
     *
     * @return
     */
    public static File createFileDir() {
        return createCacheDir(DIR_FILE);
    }


    /**
     * 删除文件夹
     *
     * @param file
     */
    public static void deleteFileDir(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.deleteOnExit(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFileDir(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }

    // 检查是否SDK准备好
    public static boolean checkSDExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
