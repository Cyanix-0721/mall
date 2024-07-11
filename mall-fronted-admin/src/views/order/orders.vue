<template>
  <div>
    <h1>订单列表</h1>
    <div class="search-bar">
      <el-input v-model="searchOrderId" placeholder="输入订单ID进行搜索" />
      <el-button type="primary" @click="searchOrderById">搜索</el-button>
      <el-button type="success" @click="showAddOrderModal = true"
        >新增订单</el-button
      >
    </div>
    <div class="table-container">
      <el-table
        :data="orders"
        style="width: 100%"
        stripe
        @sort-change="handleSortChange"
      >
        <el-table-column label="订单ID" prop="id" width="100" sortable />
        <el-table-column label="会员ID" prop="memberId" width="100" sortable />
        <el-table-column label="订单号" prop="orderSn" width="200" sortable />
        <el-table-column
          label="支付金额"
          prop="payAmount"
          width="150"
          type="number"
          step="0.01"
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
        <el-table-column label="操作" width="180" fixed="right">
          <template v-slot="scope">
            <el-button
              class="edit-button"
              link
              @click="showEditOrderModal(scope.row)"
              >编辑</el-button
            >
            <el-button
              class="delete-button"
              link
              @click="deleteOrder(scope.row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
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
        页，共
        {{ totalPage }}
        页
      </span>
      <el-button
        :disabled="currentPage === totalPage"
        @click="handlePageChange(currentPage + 1)"
        >下一页</el-button
      >
    </div>
    <el-dialog v-model="showAddOrderModal" title="新增订单">
      <el-form :model="newOrder" label-width="120px">
        <el-form-item label="会员ID" prop="memberId">
          <el-input v-model="newOrder.memberId" required />
        </el-form-item>
        <el-form-item label="收货人姓名" prop="receiverName">
          <el-input v-model="newOrder.receiverName" required />
        </el-form-item>
        <el-form-item label="收货人电话" prop="receiverPhone">
          <el-input v-model="newOrder.receiverPhone" required />
        </el-form-item>
        <el-form-item label="订单号" prop="orderSn">
          <el-input v-model="newOrder.orderSn" required />
        </el-form-item>
        <el-form-item label="支付金额" prop="payAmount">
          <el-input
            v-model.number="newOrder.payAmount"
            type="number"
            step="0.01"
            required
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="newOrder.status" required />
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="newOrder.note"
        /></el-form-item>
      </el-form>
      <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="showAddOrderModal = false">取消</el-button>
          <el-button type="primary" @click="addOrder">提交</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog v-model="showEditModal" title="编辑订单">
      <el-form :model="editOrder" label-width="120px">
        <el-form-item label="会员ID" prop="memberId">
          <el-input v-model="editOrder.memberId" required />
        </el-form-item>
        <el-form-item label="收货人姓名" prop="receiverName">
          <el-input v-model="editOrder.receiverName" required />
        </el-form-item>
        <el-form-item label="收货人电话" prop="receiverPhone">
          <el-input v-model="editOrder.receiverPhone" required />
        </el-form-item>
        <el-form-item label="订单号" prop="orderSn">
          <el-input v-model="editOrder.orderSn" required />
        </el-form-item>
        <el-form-item label="支付金额" prop="payAmount">
          <el-input
            v-model="editOrder.payAmount"
            type="number"
            step="0.01"
            required
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="editOrder.status" required />
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="editOrder.note" />
        </el-form-item>
      </el-form>
      <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="showEditModal = false">取消</el-button>
          <el-button type="primary" @click="updateOrder">提交</el-button>
        </span>
      </template>
    </el-dialog>
    <el-alert v-if="successMessage" title="成功消息" type="success" show-icon>{{
      successMessage
    }}</el-alert>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, computed } from "vue";
import axios from "axios";
import { ElMessage } from "element-plus"; // 引入 Element Plus 的 ElMessage 组件

//定义组件名称
defineOptions({
  name: "Order"
});

// 定义响应式数据
const orders = ref([]);
const totalOrders = ref(0);
const currentPage = ref(1);
const pageSize = ref(10);
const successMessage = ref("");
const jumpPage = ref(1);
const searchOrderId = ref("");
const showAddOrderModal = ref(false);
const showEditModal = ref(false);

const newOrder = ref({
  memberId: "",
  receiverName: "",
  receiverPhone: "",
  orderSn: "",
  payAmount: 0.0,
  status: "",
  note: ""
}); // 存储新增订单的信息

const editOrder = ref({
  id: "",
  memberId: "",
  receiverName: "",
  receiverPhone: "",
  orderSn: "",
  payAmount: 0.0,
  status: "",
  note: ""
}); // 存储编辑订单的信息

// 定义订单状态映射
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

// 新增订单的函数
const addOrder = async () => {
  if (
    !newOrder.value.memberId ||
    !newOrder.value.receiverName ||
    !newOrder.value.receiverPhone
  ) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  if (newOrder.value.receiverPhone.length !== 11) {
    ElMessage.warning("手机号必须为11位！");
    return;
  }

  try {
    await axios.post("http://localhost:8080/orders", newOrder.value);
    ElMessage.success("订单新增成功！");
    showAddOrderModal.value = false; // 关闭模态框
    fetchOrders(); // 刷新订单列表
    newOrder.value = {
      memberId: "",
      receiverName: "",
      receiverPhone: "",
      orderSn: "",
      payAmount: 0,
      status: "",
      note: ""
    }; // 重置表单
  } catch (error) {
    console.error("There was an error adding the order!", error);
    ElMessage.error("新增订单失败，请稍后再试！");
  }
};

// 删除订单的函数
const deleteOrder = async id => {
  try {
    await axios.delete(`http://localhost:8080/orders/${id}`);
    fetchOrders();
    ElMessage.success("删除成功！");
  } catch (error) {
    console.error("There was an error deleting the order!", error);
    ElMessage.error("删除失败，请稍后再试！");
  }
};

//展示模态框，并显示原本的信息
const showEditOrderModal = order => {
  console.log("Selected order data:", order); // 调试输出
  editOrder.value = { ...order }; // 将选中的订单信息赋值给 editOrder
  showEditModal.value = true; // 显示编辑订单模态框
};

// 编辑订单的函数
const updateOrder = async () => {
  if (
    !editOrder.value.memberId ||
    !editOrder.value.receiverName ||
    !editOrder.value.receiverPhone
  ) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  if (editOrder.value.receiverPhone.length !== 11) {
    ElMessage.warning("手机号必须为11位！");
    return;
  }

  try {
    await axios.put(
      `http://localhost:8080/orders/${editOrder.value.id}`,
      editOrder.value
    );
    ElMessage.success("订单更新成功！");
    showEditModal.value = false; // 关闭模态框
    fetchOrders(); // 刷新订单列表
  } catch (error) {
    console.error("There was an error updating the order!", error);
    ElMessage.error("更新订单失败，请稍后再试！");
  }
};

// 在组件挂载时获取订单数据
onMounted(fetchOrders);

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
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
th,
td {
  padding: 8px;
  text-align: left;
  border: 1px solid #ddd;
}
th {
  background-color: #f2f2f2;
}
.delete-button {
  background-color: #ff4d4f;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  /* margin-right: -20px; */
  transition: background-color 0.3s;
}
.delete-button:hover {
  background-color: #ff7875;
}
.delete-button:focus {
  outline: none;
  box-shadow: 0 0 3px rgba(255, 0, 0, 0.5);
}
.edit-button {
  background-color: #ffc107;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-left: 5px;
  margin-right: 15px;
}
.edit-button:hover {
  background-color: #ffb300;
}
.edit-button:focus {
  outline: none;
  box-shadow: 0 0 3px rgba(255, 193, 7, 0.5);
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
.success-message {
  margin-top: 20px;
  color: green;
  text-align: center;
}
/* 搜索栏样式 */
.search-bar {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 20px;
}
.search-input {
  padding: 5px;
  border: 1px solid #ddd;
  border-radius: 4px;
}
.search-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 5px 10px;
  margin-left: 10px;
  margin-right: 670px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}
.search-button:hover {
  background-color: #0056b3;
}
.add-button {
  background-color: #28a745;
  color: white;
  border: none;
  padding: 5px 10px;
  margin-left: 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}
.add-button:hover {
  background-color: #218838;
}
.add-button:focus {
  outline: none;
  box-shadow: 0 0 3px rgba(40, 167, 69, 0.5);
}
/* 模态框样式 */
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 50px;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  background-color: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  width: 100%;
  max-height: 80%;
  overflow-y: auto;
  position: relative;
}

.modal-content .form-group {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.modal-content .form-group label {
  min-width: 120px;
  margin-right: 20px;
}

.modal-content input,
.modal-content button {
  flex: 1;
  padding: 8px;
  font-size: 16px;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 20px;
  cursor: pointer;
}

.submit-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  width: 100%;
  margin-top: 20px;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: #0056b3;
}

/* 横向滚动条容器样式 */
.table-container {
  max-width: 100%;
  overflow-x: auto;
}

/* 模态框中输入框样式 */
.modal-content .form-group input.custom-input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: rgba(
    232,
    243,
    247,
    0.2
  ); /* 设置背景颜色为浅蓝色，透明度为0.2 */
}

/* 模态框中输入框聚焦时样式 */
.modal-content .form-group input.custom-input:focus {
  outline: none;
  border-color: #007bff; /* 设置聚焦时的边框颜色 */
  box-shadow: 0 0 3px rgba(0, 123, 255, 0.5); /* 设置聚焦时的阴影效果 */
}
</style>
