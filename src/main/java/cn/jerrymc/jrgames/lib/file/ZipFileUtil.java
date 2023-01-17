package cn.jerrymc.jrgames.lib.file;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public final class ZipFileUtil {
    /**
     * 压缩文件
     * @param dir 文件
     * @param zipFile 输出
     * @throws IOException 错误
     */
    public static void zipDirectory(File dir, File zipFile) throws IOException {
        FileOutputStream fout = new FileOutputStream(zipFile);
        ZipOutputStream zout = new ZipOutputStream(fout);
        zout.setComment("1145141919810bcmray");
        zipSubDirectory("", dir, zout);
        zout.close();
    }

    /**
     * 压缩子文件
     * @param basePath 压缩目录
     * @param dir 子文件
     * @param zout 输出流
     * @throws IOException 错误
     */
    private static void zipSubDirectory(String basePath, File dir, ZipOutputStream zout) throws IOException {
        byte[] buffer = new byte[4096];
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                String path = basePath + file.getName() + "/";
                zout.putNextEntry(new ZipEntry(path));
                zipSubDirectory(path, file, zout);
                zout.closeEntry();
            } else {
                FileInputStream fin = new FileInputStream(file);
                zout.putNextEntry(new ZipEntry(basePath + file.getName()));
                int length;
                while ((length = fin.read(buffer)) > 0) {
                    zout.write(buffer, 0, length);
                }
                zout.closeEntry();
                fin.close();
            }
        }
    }

    /**
     * 解压文件
     * @param file 文件
     * @param jiniHomeParentDir 目标目录
     * @throws IOException 解压失败
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void unzipFileIntoDirectory(File file, File jiniHomeParentDir) throws IOException {
        if (!file.exists()) return;
        @SuppressWarnings("resource")
        ZipFile zipFile = new ZipFile(file);
        Enumeration<?> files = zipFile.entries();
        File f;
        FileOutputStream fos = null;

        while (files.hasMoreElements()) {
            try {
                ZipEntry entry = (ZipEntry) files.nextElement();
                InputStream eis = zipFile.getInputStream(entry);
                byte[] buffer = new byte[1024];
                int bytesRead;

                f = new File(jiniHomeParentDir.getAbsolutePath(), entry.getName());

                if (entry.isDirectory()) {
                    f.mkdirs();
                    continue;
                } else {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                }

                fos = new FileOutputStream(f);

                while ((bytesRead = eis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException ignored) {
                        // 草, 跑路, 忽略!
                    }
                }
            }
        }
    }
}