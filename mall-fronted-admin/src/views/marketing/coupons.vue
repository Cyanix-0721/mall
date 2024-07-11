<template>
  <div>
    <h1>所有优惠劵</h1>
    <div class="search-bar">
      <input
        v-model="searchCouponId"
        type="text"
        placeholder="输入优惠劵ID进行搜索"
        class="search-input"
      />
      <button class="search-button" @click="searchCouponById">搜索</button>
      <input
        v-model="searchCouponName"
        type="text"
        placeholder="输入优惠劵名称进行搜索"
        class="search-input"
      />
      <button class="search-button" @click="searchCouponByName">搜索</button>
      <button class="add-button" @click="showAddCouponModal = true">
        新增优惠劵
      </button>
    </div>
    <table>
      <thead>
        <tr>
          <th>优惠劵ID</th>
          <th>优惠劵类型</th>
          <th>优惠劵名称</th>
          <th>发行数量</th>
          <th>金额</th>
          <th>每人限领张数</th>
          <th>开始时间</th>
          <th>结束时间</th>
          <th>备注</th>
          <th>已发放数量</th>
          <th>优惠劵码</th>
          <th>会员等级限制</th>
          <th>编辑</th>
          <th>删除</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="coupon in coupons" :key="coupon.id">
          <td>{{ coupon.id }}</td>
          <td>{{ coupon.type }}</td>
          <td>{{ coupon.name }}</td>
          <td>{{ coupon.count }}</td>
          <td>{{ coupon.amount }}</td>
          <td>{{ coupon.perLimit }}</td>
          <td>{{ coupon.startTime }}</td>
          <td>{{ coupon.endTime }}</td>
          <td>{{ coupon.note }}</td>
          <td>{{ coupon.publishCount }}</td>
          <td>{{ coupon.code }}</td>
          <td>{{ coupon.memberLevel }}</td>
          <td>
            <button class="update-button" @click="showEditCouponModal(coupon)">
              编辑
            </button>
          </td>
          <td>
            <button class="delete-button" @click="deleteCoupon(coupon.id)">
              删除
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="pagination">
      <button
        class="pagination-button"
        :disabled="currentPage === 1"
        @click="handlePageChange(currentPage - 1)"
      >
        上一页
      </button>
      <span>
        第
        <input
          v-model.number="jumpPage"
          type="number"
          min="1"
          :max="Math.ceil(totalCoupons / pageSize)"
          style="width: 50px; text-align: center"
        />
        页，共
        {{ totalPage }}
        页
      </span>
      <button
        class="pagination-button"
        :disabled="currentPage === totalPage"
        @click="handlePageChange(currentPage + 1)"
      >
        下一页
      </button>
    </div>
    <div v-if="successMessage" class="success-message">
      {{ successMessage }}
    </div>

    <!-- 新增优惠劵模态框 -->
    <div v-if="showAddCouponModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showAddCouponModal = false"
          >&times;</span
        >
        <h2>新增优惠劵</h2>
        <form @submit.prevent="addCoupon">
          <div class="form-group">
            <label for="id">优惠劵ID:</label>
            <input id="id" v-model="newCoupon.id" type="number" required />
          </div>
          <div class="form-group">
            <label for="type">优惠劵类型:</label>
            <input id="type" v-model="newCoupon.type" type="number" required />
          </div>
          <div class="form-group">
            <label for="name">优惠券名称:</label>
            <input id="name" v-model="newCoupon.name" type="text" required />
          </div>
          <div class="form-group">
            <label for="count">发行数量:</label>
            <input
              id="count"
              v-model="newCoupon.count"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="amount">金额:</label>
            <input
              id="amount"
              v-model="newCoupon.amount"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="perLimit">每人限领张数:</label>
            <input
              id="perLimit"
              v-model="newCoupon.perLimit"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="startTime">开始时间:</label>
            <input
              id="startTime"
              v-model="newCoupon.startTime"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="endTime">结束时间:</label>
            <input
              id="endTime"
              v-model="newCoupon.endTime"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="note">备注:</label>
            <input id="note" v-model="newCoupon.note" type="text" required />
          </div>
          <div class="form-group">
            <label for="publishCount">已发放数量:</label>
            <input
              id="publishCount"
              v-model="newCoupon.publishCount"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="code">优惠劵码:</label>
            <input id="code" v-model="newCoupon.code" type="text" required />
          </div>
          <div class="form-group">
            <label for="memberLevel">会员等级限制:</label>
            <input
              id="memberLevel"
              v-model="newCoupon.memberLevel"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <button type="submit" class="submit-button">提交</button>
          </div>
        </form>
      </div>
    </div>
    <!-- 更新优惠劵模态框 -->
    <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showEditModal = false">&times;</span>
        <h2>更新优惠劵</h2>
        <form @submit.prevent="updateCoupon">
          <div class="form-group">
            <label for="id">优惠劵ID:</label>
            <input id="id" v-model="editCoupon.id" type="number" required />
          </div>
          <div class="form-group">
            <label for="type">优惠劵类型:</label>
            <input id="type" v-model="editCoupon.type" type="number" required />
          </div>
          <div class="form-group">
            <label for="name">优惠券名称:</label>
            <input id="name" v-model="editCoupon.name" type="text" required />
          </div>
          <div class="form-group">
            <label for="count">发行数量:</label>
            <input
              id="count"
              v-model="editCoupon.count"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="amount">金额:</label>
            <input
              id="amount"
              v-model="editCoupon.amount"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="perLimit">每人限领张数:</label>
            <input
              id="perLimit"
              v-model="editCoupon.perLimit"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="startTime">开始时间:</label>
            <input
              id="startTime"
              v-model="editCoupon.startTime"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="endTime">结束时间:</label>
            <input
              id="endTime"
              v-model="editCoupon.endTime"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="note">备注:</label>
            <input id="note" v-model="editCoupon.note" type="text" required />
          </div>
          <div class="form-group">
            <label for="publishCount">已发放数量:</label>
            <input
              id="publishCount"
              v-model="editCoupon.publishCount"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="code">优惠劵码:</label>
            <input id="code" v-model="editCoupon.code" type="text" required />
          </div>
          <div class="form-group">
            <label for="memberLevel">会员等级限制:</label>
            <input
              id="memberLevel"
              v-model="editCoupon.memberLevel"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <button type="submit" class="submit-button">提交</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, computed } from "vue";
import axios from "axios";
import { ElMessage } from "element-plus"; // 引入 Element Plus 的 ElMessage 组件

// 定义组件名称
defineOptions({
  name: "Coupon"
});

// 定义响应式数据
const coupons = ref([]);
const totalCoupons = ref(0);
const currentPage = ref(1);
const pageSize = ref(5);
const successMessage = ref(""); // 存储删除成功消息
const jumpPage = ref(1); // 跳转的页码
const searchCouponId = ref(""); // 搜索的优惠劵ID
const showAddCouponModal = ref(false); // 控制新增优惠劵模态框的显示
const showEditModal = ref(false); // 控制模态框显示状态
const searchCouponName = ref("");
const newCoupon = ref({
  id: "",
  type: "",
  name: "",
  count: "",
  amount: "",
  perLimit: "",
  startTime: "",
  endTime: "",
  note: "",
  publishCount: "",
  code: "",
  memberLevel: ""
}); // 存储新增优惠劵的信息

// 计算总页数
const totalPage = computed(() =>
  Math.ceil(totalCoupons.value / pageSize.value)
);

// 获取优惠劵数据的函数
const fetchCoupons = async () => {
  try {
    const response = await axios.get("http://localhost:8080/coupons/list", {
      params: {
        pageSize: pageSize.value,
        pageNum: currentPage.value
      }
    });
    coupons.value = response.data.records.map(coupons => ({
      id: coupons.id,
      type: coupons.type,
      name: coupons.name,
      count: coupons.count,
      amount: coupons.amount,
      perLimit: coupons.perLimit,
      startTime: new Date(coupons.startTime).toLocaleString(), // 格式化时间
      endTime: new Date(coupons.endTime).toLocaleString(), // 格式化时间
      note: coupons.note,
      publishCount: coupons.publishCount,
      code: coupons.code,
      memberLevel: coupons.memberLevel
    })); // 假设后端返回的数据结构中有一个 records 数组包含订单数据
    totalCoupons.value = response.data.total; // 假设后端返回的数据结构中有一个 total 属性包含总记录数
  } catch (error) {
    console.error("There was an error fetching the coupons!", error);
  }
};

// 搜索优惠劵的函数
const searchCouponById = async () => {
  if (!searchCouponId.value) {
    ElMessage.warning("请输入优惠劵ID！");
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8080/coupons/${searchCouponId.value}`
    );
    if (response.data) {
      coupons.value = [response.data]; // 假设后端返回的数据结构为单个优惠劵对象
      totalCoupons.value = 1;
    } else {
      ElMessage.warning("未找到该优惠劵！");
      fetchCoupons(); // 如果未找到优惠劵，重新加载优惠劵列表
    }
  } catch (error) {
    console.error("There was an error searching the coupon!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchCoupons(); // 如果搜索失败，重新加载优惠劵列表
  }
};
// 搜索优惠劵的函数，现在根据名称搜索
const searchCouponByName = async () => {
  if (!searchCouponName.value) {
    ElMessage.warning("请输入优惠劵名称！");
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8080/coupons/name?name=${searchCouponName.value}` // 更新API路径以包含名称参数
    );
    if (response.data && response.data.length > 0) {
      coupons.value = response.data; // 假设后端返回的是包含多个匹配项的数组
      totalCoupons.value = response.data.length;
    } else {
      ElMessage.warning("未找到该优惠劵名称的优惠劵！");
      fetchCoupons(); // 如果未找到，重新加载优惠劵列表
    }
  } catch (error) {
    console.error("There was an error searching the coupon by name!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchCoupons(); // 如果搜索失败，重新加载优惠劵列表
  }
};

// 新增优惠劵的函数
const addCoupon = async () => {
  if (!newCoupon.value.id) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  try {
    await axios.post("http://localhost:8080/coupons", newCoupon.value);
    ElMessage.success("优惠劵新增成功！");
    showAddCouponModal.value = false; // 关闭模态框
    fetchCoupons(); // 刷新优惠劵列表
    newCoupon.value = {
      id: "",
      type: "",
      name: "",
      count: "",
      amount: "",
      perLimit: "",
      startTime: "",
      endTime: "",
      note: "",
      publishCount: "",
      code: "",
      memberLevel: ""
    }; // 重置表单
  } catch (error) {
    console.error("There was an error adding the coupon!", error);
    ElMessage.error("新增优惠劵失败，请稍后再试！");
  }
};

// 删除优惠劵的函数
const deleteCoupon = async id => {
  try {
    await axios.delete(`http://localhost:8080/coupons/${id}`);
    fetchCoupons(); // 删除成功后刷新优惠劵列表
    ElMessage.success("删除成功！"); // 显示 Element Plus 的成功消息提示
  } catch (error) {
    console.error("There was an error deleting the coupon!", error);
    ElMessage.error("删除失败，请稍后再试！"); // 显示 Element Plus 的错误消息提示
  }
};
// 要更新的优惠券数据模型
const editCoupon = ref({
  id: "",
  type: "",
  name: "",
  count: "",
  amount: "",
  perLimit: "",
  startTime: "",
  endTime: "",
  note: "",
  publishCount: "",
  code: "",
  memberLevel: ""
});

const showEditCouponModal = coupon => {
  console.log("Selected coupon data:", coupon); // 调试输出
  editCoupon.value = { ...coupon }; // 将选中的优惠劵信息赋值给 editCoupon
  showEditModal.value = true; // 显示编辑优惠劵模态框
};
// 编辑优惠劵的函数
const updateCoupon = async () => {
  if (!editCoupon.value.id) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  try {
    await axios.put(
      `http://localhost:8080/coupons/${editCoupon.value.id}`,
      editCoupon.value
    );
    ElMessage.success("优惠劵更新成功！");
    showEditModal.value = false; // 关闭模态框
    fetchCoupons(); // 刷新优惠劵列表
  } catch (error) {
    console.error("There was an error updating the couponr!", error);
    ElMessage.error("更新优惠劵失败，请稍后再试！");
  }
};
// 在组件挂载时获取优惠劵数据
onMounted(fetchCoupons);

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchCoupons();
  }
});

// 监听 currentPage 和 totalPage 的变化，确保页码显示正确
watch([currentPage, totalPage], () => {
  jumpPage.value = currentPage.value;
});

// 页码变化时调用的函数
const handlePageChange = newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchCoupons();
  }
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
  transition: background-color 0.3s;
}
.delete-button:hover {
  background-color: #ff7875;
}
.delete-button:focus {
  outline: none;
  box-shadow: 0 0 3px rgba(255, 0, 0, 0.5);
}
.update-button {
  background-color: #d69b1b;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}
.update-button:hover {
  background-color: #c4d01d;
}
.update-button:focus {
  outline: none;
  box-shadow: 0 0 3px rgba(216, 211, 70, 0.5);
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
  margin-right: 50px;
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
  margin-bottom: 15px; /* 设置表单项之间的底部间距为 15px */
}

.modal-content .form-group label {
  min-width: 120px; /* 根据需要调整标签的最小宽度 */
  margin-right: 20px; /* 调整标签与输入框之间的间距 */
}

.modal-content input,
.modal-content button {
  flex: 1; /* 让输入框和按钮占据剩余的空间 */
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
</style>
