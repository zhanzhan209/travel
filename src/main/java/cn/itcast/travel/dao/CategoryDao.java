package cn.itcast.travel.dao;


import cn.itcast.travel.domain.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryDao {

    //@Select("select * from tab_category order by cid")
    List<Category> findAll();

}
