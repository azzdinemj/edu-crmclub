package generator;

import com.wuxue.api.utils.plugins.MysqlPaginationPlugin;

public class MysqlPlugin extends MysqlPaginationPlugin {

    public static void main(String[] args) {
        String path = MysqlPlugin.class.getResource("").getPath().replace("target/test-classes", "src/test/java");
        try {
            createBean(path + "mybatis-gen.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
