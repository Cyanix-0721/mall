<template>
  <div>
    <h1>仓库地址</h1>
    <div class="table-container">
      <el-table :data="addresses" style="width: 100%" stripe>
        <el-table-column label="ID" prop="id" width="100" sortable />
        <el-table-column
          label="地址名称"
          prop="addressName"
          width="150"
          sortable
        />
        <el-table-column
          label="是否发货"
          prop="sendStatus"
          width="150"
          sortable
        />
        <el-table-column label="姓名" prop="name" width="150" sortable />
        <el-table-column label="电话" prop="phone" width="150" sortable />
        <el-table-column label="省份" prop="province" width="100" sortable />
        <el-table-column label="城市" prop="city" width="100" sortable />
        <el-table-column label="区/县" prop="region" width="100" sortable />
        <el-table-column label="详细地址" prop="detailAddress" width="300" />
      </el-table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import axios from "axios";
import { ElMessage } from "element-plus";

// 定义响应式数据
const addresses = ref([]);

// 获取收货地址数据
const fetchAddresses = async () => {
  try {
    const response = await axios.get("http://localhost:8080/CompanyAddress");
    addresses.value = response.data;
  } catch (error) {
    console.error("There was an error fetching the addresses!", error);
    ElMessage.error("获取收货地址数据失败，请稍后再试！");
  }
};

// 在组件挂载时获取收货地址数据
onMounted(() => {
  fetchAddresses();
});
</script>

<style scoped>
.table-container {
  max-width: 100%;
  overflow-x: auto;
  margin-top: 20px;
}
</style>
