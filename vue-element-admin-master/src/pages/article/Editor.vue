<template>
  <div class="article_editor">
    <el-button type="text" @click="back">返回</el-button>
    {{form}}
    {{categories}}
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="所属栏目">
        <el-select v-model="form.categoryId" placeholder="请选择所属栏目">
          <el-option label="IT" value="3"></el-option>
          <el-option label="娱乐" value="4"></el-option>
          <!-- <el-option v-for="c in categories" :key="c.id" :label="c.name" :value="c.id"></el-option> -->
        </el-select>
      </el-form-item>
      <el-form-item label="文章标题">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="正文">
        <el-input type="textarea" v-model="form.content" rows="10"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">发布</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import request from "@/utils/request";
import qs from "querystring";
export default {
  data() {
    return {
      form: {},
      categories: []
    };
  },
  created() {
    this.form = this.$route.query;
    // this.loadcategories();
  },
  methods: {
    
    //加载下拉栏目数据
    // loadcategories() {
      
    //     requset.get("http://localhost:8088/category/findAll").then(result => {
    //       this.categories = result.data;
    //     });
    // //   request.request({
    // //     url,
    // //     method: "get",
    // //     headers: {
    // //       "Content-Type": "application/x-www-form-urlencoded"
    // //     },
    // //     data: qs.stringify(this.form)
    // //   });
    // },

    //返回按钮实现
    back() {
      this.$router.go(-1);
    },
    onSubmit() {
      //通过ajax将前端手机的数据this.form发送给服务接口
      let url = "http://localhost:8088/article/saveOrUpdate";
      request
        .request({
          url,
          method: "post",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          data: qs.stringify(this.form)
        })
        .then(result => {
          this.$message({
            message: result.message,
            type: "success"
          });
          this.back();
        });
    }
  }
};
</script>