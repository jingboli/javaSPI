package cc.lijingbo.web;

import java.util.ServiceLoader;

/**
 * <p>Title: 应用模块名称</p>
 * <p>Description: 类功能描述</p>
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<IShot> shots = ServiceLoader.load(IShot.class);
        for (IShot s : shots) {
            s.shoot();
        }
    }
}
