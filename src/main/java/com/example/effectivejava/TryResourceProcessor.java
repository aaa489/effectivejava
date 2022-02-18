package com.example.effectivejava;

import java.io.*;

/**
 * 《effective java》-9：优先使用try-with-resources而不是try-finally
 * 使用try-with-resource代替try-finally来关闭资源
 * 1、防止忘记调用close函数，虽然资源本身会通过finalize()函数进行关闭，但是效率低，见第8条：避免使用终结方法和清理方法
 * 2、代码更加简短、可读性更高
 * @author Don
 * @date 2021/12/24.
 */
public class TryResourceProcessor {

     /**
       * 使用try-finally
       **/
    public void copy(String src, String dst){
        OutputStream out;
        InputStream in;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[1024];
                int n;
                while ((n = in.read(buf)) >= 0)
                {
                    out.write(buf, 0, n);
                }
            }
            catch (Exception ex){
                //捕获异常
            }
            finally {
                out.close();
                in.close();
            }
        }
        catch (Exception ex){
            //捕获异常
        }
    }

     /**
       * 使用try-with-resource
       **/
    public void copyWithTryResource(String src, String dst) {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) >= 0)
            {
                out.write(buf, 0, n);
            }
        }
        catch (Exception ex){
            //捕获异常
        }

    }
}
