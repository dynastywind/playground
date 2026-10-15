package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 初始化表结构
//            Connection conn = session.getConnection();
//            Statement stmt = conn.createStatement();
//            stmt.execute(IOUtils.toString(Resources.getResourceAsReader("schema.sql")));

            TaskMapper mapper = session.getMapper(TaskMapper.class);
//            Task task = new Task(1L, "{\"configuration\": {\"sourceRegionInfos\": [{\"geo\": \"US-TTP\", \"yarnQueue\": {\"queue\": \"queue\"}}]}}");
//            mapper.insert(task);
//            session.commit();

            List<SourceRegionInfo> sourceRegionInfos = new ArrayList<>();
            sourceRegionInfos.add(SourceRegionInfo.builder().geo("US").build());
            SearchRequest request = SearchRequest.builder().sourceRegionInfos(sourceRegionInfos).build();
            List<Task> tasks = mapper.search(request);
            System.out.println(tasks.getFirst().getConf());
        }
    }
}
