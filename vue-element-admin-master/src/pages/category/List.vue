<template>
  <div class="category_list">
    <!-- 按钮区域 -->
    <div class="btns">
      <el-button type="primary" style="small" @click="toAdd">新增</el-button>
      <el-button type="danger" style="small" @click="toBatchDelete">批量删除</el-button>
    </div>

    <!-- 表格 -->
    {{ids}}
    <el-table :data="categorys" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="编号" width="180"></el-table-column>
      <el-table-column prop="name" label="栏目名称" width="180"></el-table-column>
      <el-table-column prop="description" label="栏目描述"></el-table-column>
      <el-table-column prop="parentId" label="父栏目"></el-table-column>

      <el-table-column fixed="right" label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button @click="toDelete(scope.row.id)" type="text" size="small">删除</el-button>
          <el-button @click="toEdit(scope.row)" type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 模态框 -->
    <el-dialog title="新增栏目信息" :visible.sync="dialogFormVisible">
      {{form}}
      <el-form :model="form">
        <el-form-item label="栏目名称" label-width="80px">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属栏目" label-width="80px">
          <el-select clearable v-model="form.parentId" placeholder="请选择所属栏目">
            <el-option v-for="c in categorys" :key="c.id" :label="c.name" :value="c.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="栏目描述" label-width="80px">
          <el-input type="textarea" v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="submitHandler">确 定</el-button>
      </div>
    </el-dialog>
    <!-- /模态框 -->
  </div>
</template>
<script>
import requset from "@/utils/request";
import qs from "querystring";
export default {
  data() {
    return {
      dialogFormVisible: false,
      form: {},
      categorys: [],
      ids: []
    };
  },
  // 生命周期
  created() {
    this.reloadData();
  },
  methods: {
    //批量删除多选框
    handleSelectionChange(val) {

      this.ids = val.map(item => item.id);
    },

    // 新增内容点击提交
    submitHandler() {
      // alert(qs.stringify(this.form));
      requset
        .request({
          url: "http://localhost:8088/category/saveOrUpdate",
          method: "post",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
    
          data: qs.stringify(this.form)
        })
        .then(response => {
          //提示信息
          this.$message({
            message: response.message,
            type: "success"
          });
          //关闭模态框
          this.dialogFormVisible = false;
          //重载数据
          this.reloadData();
        });
    },
    // 加载数据的方法
    reloadData() {
      let url = "http://localhost:8088/category/findAll";
      requset.get(url).then(result => {
        this.categorys = result.data;
      });
    },
    //编辑
    toEdit(record) {
      this.dialogFormVisible = true;
      // 为form绑定要修改的值
      this.form = record;
    },
    // 批量删除的实现
    toBatchDelete() {
       this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
      let url = "http://localhost:8088/category/batchDelete"
      requset.post(url,this.ids)
      .then(response=>{
        this.$message({
          type: "success",
            message: "success"
        })
        this.reloadData();
      })
      });
    },
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
        let url = "http://localhost:8088/category/deleteById";
        requset.get(url, { params: { id: id } }).then(response => {
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

    toAdd() {
      // 先把值置空，不然新增一项内容后，再次点击新增，会出现上次提交的内容
      this.form = {};
      // 跳转
      this.dialogFormVisible = true;
    }
  }
};
</script>
<style scoped>
</style>