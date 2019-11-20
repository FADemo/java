<template>
  <div class="article_list">
    <!-- 按钮 -->
    <div class="btns">
      <el-button type="primary" style="small" @click="toPublishArticle">发布文章</el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="articles" style="width: 100%">
      <el-table-column prop="id" label="编号" width="180"></el-table-column>
      <el-table-column prop="title" label="标题" width="180"></el-table-column>
      <el-table-column prop="authorId" label="作者"></el-table-column>
      <el-table-column prop="category.name" label="所属栏目"></el-table-column>

      <el-table-column fixed="right" label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button @click="toDelete(scope.row.id)" type="text" size="small">删除</el-button>
          <el-button @click="toReview(scope.row)" type="text" size="small">查看</el-button>
          <el-button @click="toEdit(scope.row)" type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
  </div>
</template>
<script>
import requset from "@/utils/request";
// import { type } from "os";
export default {
  data() {
    return {
      articles: []
    };
  },
  // 生命周期
  created() {
    // requset.get("http://localhost:8088/article/cascadeFindAll")
    // .then(result=>{
    //     this.articles = result.data;
    // })
    this.reloadData();
  },
  methods: {
    reloadData() {
      let url = "http://localhost:8088/article/cascadeFindAll";
      requset.get(url).then(result => {
        this.articles = result.data;
      });
    },
    //编辑
    toEdit(record) {
      this.$router.push({ path: "/article/editor", query: record });
    },
    //查看
    toReview() {},
    //删除
    toDelete(id) {
      //alert(id);
      
      //正常
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        //交互
        let url = "http://localhost:8088/article/deleteById";
        requset
        .get(url, { params: { id:id } })
        .then(response => {
          //通知
          this.$message({
              type: "success",
            message: "success"
          });
          //重载数据
          this.reloadData();
        });
      });
    },

    toPublishArticle() {
      // 跳转到编辑界面
      this.$router.push({ path: "/article/editor" });
    }
  }
};
</script>
<style scoped>
</style>