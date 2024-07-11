<template>
  <div>
    <h1>首页广告管理</h1>
    <div class="search-bar">
      <input
        v-model="searchHomeAdvertiseId"
        type="text"
        placeholder="输入首页广告ID进行搜索"
        class="search-input"
      />
      <button class="search-button" @click="searchHomeAdvertiseById">
        搜索
      </button>
      <button class="add-button" @click="showAddHomeAdvertiseModal = true">
        新增首页广告
      </button>
    </div>
    <table>
      <thead>
        <tr>
          <th>广告ID</th>
          <th>广告名称</th>
          <th>开始日期</th>
          <th>结束日期</th>
          <th>上下线状态</th>
          <th>点击数</th>
          <th>下单数</th>
          <th>编辑</th>
          <th>删除</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="homeAdvertise in homeAdvertises" :key="homeAdvertise.id">
          <td>{{ homeAdvertise.id }}</td>
          <td>{{ homeAdvertise.name }}</td>
          <td>{{ homeAdvertise.startTime }}</td>
          <td>{{ homeAdvertise.endTime }}</td>
          <td>{{ homeAdvertise.status }}</td>
          <td>{{ homeAdvertise.clickCount }}</td>
          <td>{{ homeAdvertise.orderCount }}</td>
          <td>
            <button
              class="update-button"
              @click="showEditHomeAdvertiseModal(homeAdvertise)"
            >
              编辑
            </button>
          </td>
          <td>
            <button
              class="delete-button"
              @click="deleteHomeAdvertise(homeAdvertise.id)"
            >
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
          :max="Math.ceil(totalHomeAdvertises / pageSize)"
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

    <!-- 新增首页广告模态框 -->
    <div v-if="showAddHomeAdvertiseModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showAddHomeAdvertiseModal = false"
          >&times;</span
        >
        <h2>新增首页广告</h2>
        <form @submit.prevent="addHomeAdvertise">
          <div class="form-group">
            <label for="id">广告ID:</label>
            <input
              id="id"
              v-model="newHomeAdvertise.id"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="name">广告名称:</label>
            <input
              id="name"
              v-model="newHomeAdvertise.name"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="startTime">开始时间:</label>
            <input
              id="startTime"
              v-model="newHomeAdvertise.startTime"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="endTime">结束时间:</label>
            <input
              id="endTime"
              v-model="newHomeAdvertise.endTime"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="status">上下线状态:</label>
            <input
              id="status"
              v-model="newHomeAdvertise.status"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="clickCount">点击数:</label>
            <input
              id="clickCount"
              v-model="newHomeAdvertise.clickCount"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="orderCount">下单数:</label>
            <input
              id="orderCount"
              v-model="newHomeAdvertise.orderCount"
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
    <!-- 更新首页广告模态框 -->
    <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showEditModal = false">&times;</span>
        <h2>更新首页广告</h2>
        <form @submit.prevent="updateHomeAdvertise">
          <div class="form-group">
            <label for="id">广告ID:</label>
            <input
              id="id"
              v-model="editHomeAdvertise.id"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="name">广告名称:</label>
            <input
              id="name"
              v-model="editHomeAdvertise.name"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="startTime">开始日期:</label>
            <input
              id="startTime"
              v-model="editHomeAdvertise.startTime"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="endTime">结束日期:</label>
            <input
              id="endTime"
              v-model="editHomeAdvertise.endTime"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="status">上下线状态:</label>
            <input
              id="status"
              v-model="editHomeAdvertise.status"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="clickCount">点击数:</label>
            <input
              id="clickCount"
              v-model="editHomeAdvertise.clickCount"
              type="clickCount"
              required
            />
          </div>
          <div class="form-group">
            <label for="orderCount">下单数:</label>
            <input
              id="orderCount"
              v-model="editHomeAdvertise.orderCount"
              type="orderCount"
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
  name: "HomeAdvertise"
});

// 定义响应式数据
const homeAdvertises = ref([]);
const totalHomeAdvertises = ref(0);
const currentPage = ref(1);
const pageSize = ref(5);
const successMessage = ref(""); // 存储删除成功消息
const jumpPage = ref(1); // 跳转的页码
const searchHomeAdvertiseId = ref(""); // 搜索的首页广告ID
const showAddHomeAdvertiseModal = ref(false); // 控制新增首页广告模态框的显示
const showEditModal = ref(false); // 控制模态框显示状态
const newHomeAdvertise = ref({
  id: "",
  name: "",
  startTime: "",
  endTime: "",
  status: "",
  clickCount: "",
  orderCount: ""
}); // 存储新增首页广告的信息
// 计算总页数
const totalPage = computed(() =>
  Math.ceil(totalHomeAdvertises.value / pageSize.value)
);

// 获取首页广告的函数
const fetchHomeAdvertises = async () => {
  try {
    const response = await axios.get("http://localhost:8080/home-advertises", {
      params: {
        pageSize: pageSize.value,
        pageNum: currentPage.value
      }
    });
    homeAdvertises.value = response.data.records.map(homeAdvertises => ({
      id: homeAdvertises.id,
      name: homeAdvertises.name,
      startTime: new Date(homeAdvertises.startTime).toLocaleString(),
      endTime: new Date(homeAdvertises.endTime).toLocaleString(),
      status: homeAdvertises.status, // 格式化时间
      clickCount: homeAdvertises.clickCount,
      orderCount: homeAdvertises.orderCount
    })); // 假设后端返回的数据结构中有一个 records 数组包含首页广告
    totalHomeAdvertises.value = response.data.total; // 假设后端返回的数据结构中有一个 total 属性包含总记录数
  } catch (error) {
    console.error("There was an error fetching the homeAdvertises!", error);
  }
};

// 搜索首页广告的函数
const searchHomeAdvertiseById = async () => {
  if (!searchHomeAdvertiseId.value) {
    ElMessage.warning("请输入首页广告ID！");
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8080/home-advertises/${searchHomeAdvertiseId.value}`
    );
    if (response.data) {
      homeAdvertises.value = [response.data]; // 假设后端返回的数据结构为单个首页广告对象
      totalHomeAdvertises.value = 1;
    } else {
      ElMessage.warning("未找到该首页广告！");
      fetchHomeAdvertises(); // 如果未找到首页广告，重新加载首页广告列表
    }
  } catch (error) {
    console.error("There was an error searching the homeAdvertise!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchHomeAdvertises(); // 如果搜索失败，重新加载首页广告列表
  }
};

// 新增首页广告的函数
const addHomeAdvertise = async () => {
  if (!newHomeAdvertise.value.id) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  try {
    await axios.post(
      "http://localhost:8080/home-advertises",
      newHomeAdvertise.value
    );
    ElMessage.success("首页广告新增成功！");
    showAddHomeAdvertiseModal.value = false; // 关闭模态框
    fetchHomeAdvertises(); // 刷新首页广告列表
    newHomeAdvertise.value = {
      id: "",
      name: "",
      startTime: "",
      endTime: "",
      status: "",
      clickCount: "",
      orderCount: ""
    }; // 重置表单
  } catch (error) {
    console.error("There was an error adding the homeAdvertise!", error);
    ElMessage.error("新增首页广告失败，请稍后再试！");
  }
};

// 删除首页广告的函数
const deleteHomeAdvertise = async id => {
  try {
    await axios.delete(`http://localhost:8080/home-advertises/${id}`);
    fetchHomeAdvertises(); // 删除成功后刷新首页广告列表
    ElMessage.success("删除成功！"); // 显示 Element Plus 的成功消息提示
  } catch (error) {
    console.error("There was an error deleting the homeAdvertise!", error);
    ElMessage.error("删除失败，请稍后再试！"); // 显示 Element Plus 的错误消息提示
  }
};
// 要更新的首页广告数据模型
const editHomeAdvertise = ref({
  id: "",
  name: "",
  startTime: "",
  endTime: "",
  status: "",
  clickCount: "",
  orderCount: ""
});

const showEditHomeAdvertiseModal = homeAdvertise => {
  console.log("Selected homeAdvertise data:", homeAdvertise); // 调试输出
  editHomeAdvertise.value = { ...homeAdvertise }; // 将选中的首页广告信息赋值给 edithomeAdvertise
  showEditModal.value = true; // 显示编辑首页广告模态框
};
// 编辑首页广告的函数
const updateHomeAdvertise = async () => {
  if (!editHomeAdvertise.value.id) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  try {
    await axios.put(
      `http://localhost:8080/home-advertises/${editHomeAdvertise.value.id}`,
      editHomeAdvertise.value
    );
    ElMessage.success("订单更新成功！");
    showEditModal.value = false; // 关闭模态框
    fetchHomeAdvertises(); // 刷新首页广告列表
  } catch (error) {
    console.error("There was an error updating the homeAdvertise!", error);
    ElMessage.error("更新首页广告失败，请稍后再试！");
  }
};
// 在组件挂载时获取首页广告数据
onMounted(fetchHomeAdvertises);

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchHomeAdvertises();
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
    fetchHomeAdvertises();
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
