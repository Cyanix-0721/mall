<template>
  <div>
    <h1>退货申请管理</h1>
    <!-- 数据表格容器 -->
    <div class="table-container">
      <el-table :data="returnApplies" style="width: 100%" stripe>
        <!-- 列定义 -->
        <el-table-column label="申请ID" prop="id" width="150" sortable />
        <el-table-column label="订单ID" prop="orderId" width="150" sortable />
        <el-table-column label="商品ID" prop="productId" width="150" sortable />
        <el-table-column label="订单编号" prop="orderSn" width="250" sortable />
        <el-table-column
          label="状态"
          prop="status"
          width="150"
          :formatter="formatStatus"
        />
        <el-table-column label="商品图片" width="150">
          <template #default="{ row }">
            <el-image
              :src="row.productPic"
              style="width: 100px; height: 100px"
              fit="cover"
            />
          </template>
        </el-table-column>
        <el-table-column label="商品名称" prop="productName" width="250" />
        <el-table-column label="会员用户名" prop="memberUsername" width="150" />
      </el-table>
    </div>
    <!-- 分页 -->
    <div class="pagination">
      <el-button
        :disabled="currentPage === 1"
        @click="handlePageChange(currentPage - 1)"
        >上一页</el-button
      >
      <span>
        第
        <el-input
          v-model.number="jumpPage"
          type="number"
          min="1"
          :max="totalPage"
          style="width: 50px; text-align: center"
        />
        页，共 {{ totalPage }} 页
      </span>
      <el-button
        :disabled="currentPage === totalPage"
        @click="handlePageChange(currentPage + 1)"
        >下一页</el-button
      >
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, computed } from "vue";
import axios from "axios";
import { ElMessage } from "element-plus";

// 响应式数据
const returnApplies = ref([]);
const totalReturnApplies = ref(0);
const currentPage = ref(1);
const pageSize = ref(10);
const jumpPage = ref(1);
const searchKeyword = ref("");

// 获取退货申请列表函数
const fetchReturnApplies = async () => {
  try {
    const response = await axios.get("http://localhost:8080/returnApply/list", {
      params: {
        pageSize: pageSize.value,
        pageNum: currentPage.value
      }
    });
    returnApplies.value = response.data.records.map(item => ({
      id: item.id,
      orderId: item.orderId,
      productId: item.productId,
      orderSn: item.orderSn,
      status: item.status,
      productPic: item.productPic,
      productName: item.productName,
      memberUsername: item.memberUsername
    }));
    totalReturnApplies.value = response.data.total;
  } catch (error) {
    console.error("获取退货申请时出错！", error);
    ElMessage.error("获取退货申请失败，请稍后再试！");
  }
};

// 计算总页数
const totalPage = computed(() =>
  Math.ceil(totalReturnApplies.value / pageSize.value)
);

// 格式化状态显示
const formatStatus = (row: any) => {
  // 根据实际状态字段的值进行适当的格式化
  return row.status === 1 ? "已处理" : "未处理";
};

// 组件挂载时获取退货申请列表
onMounted(fetchReturnApplies);

// 监听跳转页码变化
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchReturnApplies();
  }
});

// 监听当前页码和总页数变化，更新跳转页码
watch([currentPage, totalPage], () => {
  jumpPage.value = currentPage.value;
});

// 处理页码变化
const handlePageChange = async (newPage: number) => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    await fetchReturnApplies();
  }
};
</script>

<style scoped>
.search-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-bar el-input {
  flex: 1;
  margin-right: 10px;
}

.table-container {
  max-width: 100%;
  overflow-x: auto;
}

.pagination {
  margin-top: 20px;
  text-align: center;
}

.pagination-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 5px 10px;
  margin: 0 5px;
  border-radius: 4px;
  cursor: pointer;
  transition:
    background-color 0.3s,
    opacity 0.3s;
}

.pagination-button:hover {
  background-color: #0056b3;
}

.pagination-button:disabled {
  background-color: #ddd;
  cursor: not-allowed;
  opacity: 0.5;
}
</style>
