<template>
  <div>
    <h1>所有秒杀活动</h1>
    <div class="search-bar">
      <input
        v-model="searchFlashPromotionId"
        type="text"
        placeholder="输入秒杀活动ID进行搜索"
        class="search-input"
      />
      <button class="search-button" @click="searchFlashPromotionById">
        搜索
      </button>
      <button class="add-button" @click="showAddFlashPromotionModal = true">
        新增秒杀活动
      </button>
    </div>
    <table>
      <thead>
        <tr>
          <th>秒杀活动ID</th>
          <th>秒杀时间段名称</th>
          <th>开始日期</th>
          <th>结束日期</th>
          <th>上下线状态</th>
          <th>创建时间</th>
          <th>编辑</th>
          <th>删除</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="flashPromotion in flashPromotions" :key="flashPromotion.id">
          <td>{{ flashPromotion.id }}</td>
          <td>{{ flashPromotion.title }}</td>
          <td>{{ flashPromotion.startDate }}</td>
          <td>{{ flashPromotion.endDate }}</td>
          <td>{{ flashPromotion.status }}</td>
          <td>{{ flashPromotion.createTime }}</td>
          <td>
            <button
              class="update-button"
              @click="showEditFlashPromotionModal(flashPromotion)"
            >
              编辑
            </button>
          </td>
          <td>
            <button
              class="delete-button"
              @click="deleteFlashPromotion(flashPromotion.id)"
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
          :max="Math.ceil(totalFlashPromotions / pageSize)"
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

    <!-- 新增秒杀活动模态框 -->
    <div v-if="showAddFlashPromotionModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showAddFlashPromotionModal = false"
          >&times;</span
        >
        <h2>新增秒杀活动</h2>
        <form @submit.prevent="addFlashPromotion">
          <div class="form-group">
            <label for="id">秒杀活动ID:</label>
            <input
              id="id"
              v-model="newFlashPromotion.id"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="title">秒杀时间段名称:</label>
            <input
              id="title"
              v-model="newFlashPromotion.title"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="startDate">开始日期:</label>
            <input
              id="startDate"
              v-model="newFlashPromotion.startDate"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="endDate">结束日期:</label>
            <input
              id="endDate"
              v-model="newFlashPromotion.endDate"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="status">上下线状态:</label>
            <input
              id="status"
              v-model="newFlashPromotion.status"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="createTime">创建时间:</label>
            <input id="createTime" type="date" required />
          </div>
          <div class="form-group">
            <button type="submit" class="submit-button">提交</button>
          </div>
        </form>
      </div>
    </div>
    <!-- 更新秒杀活动模态框 -->
    <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showEditModal = false">&times;</span>
        <h2>更新秒杀活动</h2>
        <form @submit.prevent="updateFlashPromotion">
          <div class="form-group">
            <label for="id">秒杀活动ID:</label>
            <input
              id="id"
              v-model="editFlashPromotion.id"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="title">秒杀时间段名称:</label>
            <input
              id="title"
              v-model="editFlashPromotion.title"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="startDate">开始日期:</label>
            <input
              id="startDate"
              v-model="editFlashPromotion.startDate"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="endDate">结束日期:</label>
            <input
              id="endDate"
              v-model="editFlashPromotion.endDate"
              type="date"
              required
            />
          </div>
          <div class="form-group">
            <label for="status">上下线状态:</label>
            <input
              id="status"
              v-model="editFlashPromotion.status"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="createTime">创建时间:</label>
            <input
              id="createTime"
              v-model="editFlashPromotion.createTime"
              type="date"
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
  name: "FlashPromotion"
});

// 定义响应式数据
const flashPromotions = ref([]);
const totalFlashPromotions = ref(0);
const currentPage = ref(1);
const pageSize = ref(5);
const successMessage = ref(""); // 存储删除成功消息
const jumpPage = ref(1); // 跳转的页码
const searchFlashPromotionId = ref(""); // 搜索的秒杀活动ID
const showAddFlashPromotionModal = ref(false); // 控制新增秒杀活动模态框的显示
const showEditModal = ref(false); // 控制模态框显示状态
const newFlashPromotion = ref({
  id: "",
  title: "",
  startDate: "",
  endDate: "",
  status: "",
  createTime: ""
}); // 存储新增秒杀活动的信息

// 计算总页数
const totalPage = computed(() =>
  Math.ceil(totalFlashPromotions.value / pageSize.value)
);

// 获取订单数据的函数
const fetchFlashPromotions = async () => {
  try {
    const response = await axios.get("http://localhost:8080/flashPromotions", {
      params: {
        pageSize: pageSize.value,
        pageNum: currentPage.value
      }
    });
    flashPromotions.value = response.data.records.map(flashPromotions => ({
      id: flashPromotions.id,
      title: flashPromotions.title,
      startDate: new Date(flashPromotions.startDate).toLocaleString(),
      endDate: new Date(flashPromotions.endDate).toLocaleString(),
      status: flashPromotions.status, // 格式化时间
      createTime: new Date(flashPromotions.createTime).toLocaleString()
    }));
    totalFlashPromotions.value = response.data.total; // 假设后端返回的数据结构中有一个 total 属性包含总记录数
  } catch (error) {
    console.error("There was an error fetching the flashPromotions!", error);
  }
};

// 搜索秒杀活动的函数
const searchFlashPromotionById = async () => {
  if (!searchFlashPromotionId.value) {
    ElMessage.warning("请输入秒杀活动ID！");
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8080/flashPromotions/${searchFlashPromotionId.value}`
    );
    if (response.data) {
      flashPromotions.value = [response.data]; // 假设后端返回的数据结构为单个秒杀活动对象
      totalFlashPromotions.value = 1;
    } else {
      ElMessage.warning("未找到该订单！");
      fetchFlashPromotions(); // 如果未找到秒杀活动，重新加载秒杀活动列表
    }
  } catch (error) {
    console.error("There was an error searching the flashPromotion!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchFlashPromotions(); // 如果搜索失败，重新加载秒杀活动列表
  }
};

// 新增订单的函数
const addFlashPromotion = async () => {
  if (!newFlashPromotion.value.id) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  try {
    await axios.post(
      "http://localhost:8080/flashPromotions",
      newFlashPromotion.value
    );
    ElMessage.success("秒杀活动新增成功！");
    showAddFlashPromotionModal.value = false; // 关闭模态框
    fetchFlashPromotions(); // 刷新秒杀活动列表
    newFlashPromotion.value = {
      id: "",
      title: "",
      startDate: "",
      endDate: "",
      status: "",
      createTime: ""
    }; // 重置表单
  } catch (error) {
    console.error("There was an error adding the flashPromotion!", error);
    ElMessage.error("新增秒杀活动失败，请稍后再试！");
  }
};

// 删除秒杀活动的函数
const deleteFlashPromotion = async id => {
  try {
    await axios.delete(`http://localhost:8080/flashPromotions/${id}`);
    fetchFlashPromotions(); // 删除成功后刷新秒杀活动列表
    ElMessage.success("删除成功！"); // 显示 Element Plus 的成功消息提示
  } catch (error) {
    console.error("There was an error deleting the flashPromotion!", error);
    ElMessage.error("删除失败，请稍后再试！"); // 显示 Element Plus 的错误消息提示
  }
};
// 要更新的秒杀活动数据模型
const editFlashPromotion = ref({
  id: "",
  title: "",
  startDate: "",
  endDate: "",
  status: "",
  createTime: ""
});

const showEditFlashPromotionModal = flashPromotion => {
  console.log("Selected flashPromotion data:", flashPromotion); // 调试输出
  editFlashPromotion.value = { ...flashPromotion }; // 将选中的秒杀活动信息赋值给 editFlashPromotion
  showEditModal.value = true; // 显示编辑秒杀活动模态框
};
// 编辑秒杀活动的函数
const updateFlashPromotion = async () => {
  if (!editFlashPromotion.value.id) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  try {
    await axios.put(
      `http://localhost:8080/flashPromotions/${editFlashPromotion.value.id}`,
      editFlashPromotion.value
    );
    ElMessage.success("订单更新成功！");
    showEditModal.value = false; // 关闭模态框
    fetchFlashPromotions(); // 刷新秒杀活动列表
  } catch (error) {
    console.error("There was an error updating the flashPromotion!", error);
    ElMessage.error("更新秒杀活动失败，请稍后再试！");
  }
};
// 在组件挂载时获取秒杀活动数据
onMounted(fetchFlashPromotions);

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchFlashPromotions();
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
    fetchFlashPromotions();
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
