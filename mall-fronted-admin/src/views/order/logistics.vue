<template>
  <div>
    <h1>物流管理</h1>
    <div class="search-bar">
      <el-input v-model="searchOrderId" placeholder="输入订单ID进行搜索" />
      <el-button type="primary" @click="searchOrderById">搜索</el-button>
    </div>
    <div class="batch-actions">
      <el-button type="success" @click="batchUpdateStatus">一键发货</el-button>
      <el-button type="danger" @click="batchDeleteOrders">一键删除</el-button>
    </div>
    <div class="table-container">
      <el-table
        :data="orders"
        style="width: 100%"
        stripe
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" fixed="left" />
        <el-table-column label="订单ID" prop="id" width="100" sortable />
        <el-table-column label="会员ID" prop="memberId" width="100" sortable />
        <el-table-column label="订单号" prop="orderSn" width="200" sortable />
        <el-table-column
          label="支付金额"
          prop="payAmount"
          width="150"
          sortable
        />
        <el-table-column
          label="状态"
          width="100"
          sortable
          :formatter="formatStatus"
        >
          <template v-slot="scope">
            {{ orderStatusMap[scope.row.status] }}
          </template>
        </el-table-column>
        <el-table-column label="收货人姓名" prop="receiverName" width="150" />
        <el-table-column label="收货人电话" prop="receiverPhone" width="150" />
        <el-table-column label="备注" prop="note" width="200" />
      </el-table>
    </div>
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
import { ElMessage } from "element-plus"; // 引入 Element Plus 的 ElMessage 组件

//定义组件名称
defineOptions({
  name: "LogisticsManagement"
});

// 定义响应式数据
const orders = ref([]);
const totalOrders = ref(0);
const currentPage = ref(1);
const pageSize = ref(10);
const successMessage = ref("");
const jumpPage = ref(1);
const searchOrderId = ref("");
const selectedOrders = ref([]);

const orderStatusMap = {
  0: "待付款",
  1: "待发货",
  2: "已发货",
  3: "已完成",
  4: "已关闭",
  5: "无效订单"
};

// 计算总页数
const totalPage = computed(() => Math.ceil(totalOrders.value / pageSize.value));

const fetchOrders = async () => {
  try {
    const response = await axios.get("http://localhost:8080/orders/list", {
      params: {
        pageSize: pageSize.value,
        pageNum: currentPage.value
      }
    });
    orders.value = response.data.records;
    totalOrders.value = response.data.total;
  } catch (error) {
    console.error("There was an error fetching the orders!", error);
  }
};

// 搜索订单的函数
const searchOrderById = async () => {
  if (!searchOrderId.value) {
    fetchOrders();
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8080/orders/${searchOrderId.value}`
    );
    if (response.data) {
      orders.value = [response.data];
      totalOrders.value = 1;
    } else {
      ElMessage.warning("未找到该订单！");
      fetchOrders();
    }
  } catch (error) {
    console.error("There was an error searching the order!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchOrders();
  }
};

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchOrders();
  }
});

// 监听 currentPage 和 totalPage 的变化，确保页码显示正确
watch([currentPage, totalPage], () => {
  jumpPage.value = currentPage.value;
});

// 监听排序变化
const handleSortChange = ({ prop, order }) => {
  orders.value.sort((a, b) => {
    if (order === "ascending") {
      return a[prop] > b[prop] ? 1 : -1;
    } else {
      return a[prop] < b[prop] ? 1 : -1;
    }
  });
};

// 页码变化时调用的函数
const handlePageChange = newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchOrders();
  }
};

// 格式化订单状态
const formatStatus = row => {
  return orderStatusMap[row.status] || "未知状态";
};

// 批量修改订单状态
const batchUpdateStatus = async () => {
  if (selectedOrders.value.length === 0) {
    ElMessage.warning("请选择要修改状态的订单！");
    return;
  }

  try {
    await axios.post(
      "http://localhost:8080/orders/delivery",
      selectedOrders.value.map(order => order.id) // 发送数组
    );
    ElMessage.success("一键发货成功！");
    fetchOrders();
    selectedOrders.value = [];
  } catch (error) {
    console.error("There was an error updating the order status!", error);
    ElMessage.error("一键发货失败，请稍后再试！");
  }
};

// 批量删除订单
const batchDeleteOrders = async () => {
  if (selectedOrders.value.length === 0) {
    ElMessage.warning("请选择要删除的订单！");
    return;
  }

  try {
    await axios.post(
      "http://localhost:8080/orders/batchDelete",
      selectedOrders.value.map(order => order.id) // 发送数组
    );
    ElMessage.success("批量删除订单成功！");
    fetchOrders();
    selectedOrders.value = [];
  } catch (error) {
    console.error("There was an error deleting the orders!", error);
    ElMessage.error("批量删除订单失败，请稍后再试！");
  }
};

// 监听选中项变化
const handleSelectionChange = selection => {
  selectedOrders.value = selection;
};

// 在组件挂载时获取订单数据
onMounted(fetchOrders);
</script>

<style scoped>
.table-container {
  max-width: 100%;
  overflow-x: auto;
}

.batch-actions {
  margin-bottom: 20px;
}

.search-bar {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 20px;
}

.pagination {
  margin-top: 20px;
  text-align: center;
}

.el-button + .el-button {
  margin-left: 10px;
}
</style>
