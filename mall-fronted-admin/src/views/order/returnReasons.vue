<template>
  <div>
    <h1>退货原因管理</h1>
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-input v-model="searchReason" placeholder="输入退货原因进行搜索" />
      <el-button type="primary" @click="searchReturnReason">搜索</el-button>
      <el-button type="success" @click="showAddModal = true"
        >新增退货原因</el-button
      >
    </div>
    <!-- 数据表格容器 -->
    <div class="table-container">
      <el-table :data="returnReasons" style="width: 100%" stripe>
        <!-- 列定义 -->
        <el-table-column label="退货原因ID" prop="id" width="150" sortable />
        <el-table-column
          label="退货原因名称"
          prop="name"
          width="250"
          sortable
        />
        <el-table-column label="排序" prop="sort" width="150" sortable />
        <el-table-column
          label="状态"
          prop="status"
          width="150"
          sortable
          :formatter="formatStatus"
        />
        <el-table-column label="创建时间" prop="createTime" width="250" />
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="{ row }">
            <!-- 操作按钮 -->
            <el-button link class="edit-button" @click="showEditModal(row.id)"
              >编辑</el-button
            >
            <el-button
              link
              class="delete-button"
              @click="deleteReturnReason(row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
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
    <!-- 新增退货原因对话框 -->
    <el-dialog v-model="showAddModal" title="新增退货原因">
      <el-form :model="newReturnReason" label-width="120px">
        <el-form-item label="退货原因名称" prop="name">
          <el-input v-model="newReturnReason.name" required />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="newReturnReason.sort" type="number" required />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="newReturnReason.status">
            <el-radio :label="0">不启用</el-radio>
            <el-radio :label="1">启用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="showAddModal = false">取消</el-button>
          <el-button type="primary" @click="addReturnReason">提交</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 编辑退货原因对话框 -->
    <el-dialog v-model="showEditModalStatus" title="编辑退货原因">
      <el-form :model="editReturnReason" label-width="120px">
        <el-form-item label="退货原因名称" prop="name">
          <el-input v-model="editReturnReason.name" required />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="editReturnReason.sort" required />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="editReturnReason.status">
            <el-radio :label="0">不启用</el-radio>
            <el-radio :label="1">启用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="showEditModalStatus = false">取消</el-button>
          <el-button type="primary" @click="updateReturnReason">提交</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 成功消息提示 -->
    <el-alert v-if="successMessage" title="成功消息" type="success" show-icon>{{
      successMessage
    }}</el-alert>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, computed } from "vue";
import axios from "axios";
import { ElMessage } from "element-plus";

// 响应式数据
const returnReasons = ref([]);
const totalReturnReasons = ref(0);
const currentPage = ref(1);
const pageSize = ref(10);
const successMessage = ref("");
const jumpPage = ref(1);
const searchReason = ref("");
const showAddModal = ref(false);
const showEditModalStatus = ref(false);

// 新增退货原因表单数据
const newReturnReason = ref({
  name: "",
  sort: "",
  status: 0 // 默认为 "不启用"
});

// 编辑退货原因表单数据
const editReturnReason = ref({
  id: "",
  name: "",
  sort: "",
  status: ""
});

// 获取退货原因列表函数
const fetchReturnReasons = async () => {
  try {
    const response = await axios.get(
      "http://localhost:8080/returnReasons/list",
      {
        params: {
          pageSize: pageSize.value,
          pageNum: currentPage.value
        }
      }
    );
    returnReasons.value = response.data.records.map(item => ({
      id: item.id,
      name: item.name,
      sort: item.sort,
      status: item.status,
      createTime: new Date(item.createTime).toLocaleString()
    }));
    totalReturnReasons.value = response.data.total;
  } catch (error) {
    console.error("获取退货原因时出错！", error);
  }
};

// 搜索退货原因函数
const searchReturnReason = async () => {
  if (!searchReason.value) {
    fetchReturnReasons();
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8080/returnReasons/${searchReason.value}`
    );
    if (response.data) {
      returnReasons.value = [
        {
          id: response.data.id,
          name: response.data.name,
          sort: response.data.sort,
          status: response.data.status,
          createTime: new Date(response.data.createTime).toLocaleString()
        }
      ];
      totalReturnReasons.value = 1;
    } else {
      ElMessage.warning("未找到该退货原因！");
      fetchReturnReasons();
    }
  } catch (error) {
    console.error("搜索退货原因时出错！", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchReturnReasons();
  }
};

// 添加退货原因
const addReturnReason = async () => {
  if (!newReturnReason.value.name || !newReturnReason.value.sort) {
    ElMessage.warning("请填写完整的退货原因信息！");
    return;
  }
  try {
    await axios.post(
      "http://localhost:8080/returnReasons",
      newReturnReason.value
    );
    ElMessage.success("退货原因添加成功！");
    showAddModal.value = false;
    fetchReturnReasons();
    newReturnReason.value = { name: "", sort: "", status: 0 };
  } catch (error) {
    console.error("添加退货原因时出错！", error);
    ElMessage.error("添加退货原因失败，请稍后再试！");
  }
};

// 删除退货原因
const deleteReturnReason = async (id: string) => {
  try {
    await axios.delete(`http://localhost:8080/returnReasons/${id}`);
    fetchReturnReasons();
    ElMessage.success("删除成功！");
  } catch (error) {
    console.error("删除退货原因时出错！", error);
    ElMessage.error("删除失败，请稍后再试！");
  }
};

// 弹出模态框
const showEditModal = async (id: string) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/returnReasons/${id}`
    );
    if (response.data) {
      editReturnReason.value = {
        id: response.data.id,
        name: response.data.name,
        sort: response.data.sort,
        status: response.data.status
      };
      showEditModalStatus.value = true;
    }
  } catch (error) {
    console.error("获取退货原因时出错！", error);
    ElMessage.error("获取退货原因失败，请稍后再试！");
  }
};

// 编辑退货原因
const updateReturnReason = async () => {
  if (!editReturnReason.value.name || !editReturnReason.value.sort) {
    ElMessage.warning("请填写完整的退货原因信息！");
    return;
  }
  try {
    await axios.put(
      `http://localhost:8080/returnReasons/${editReturnReason.value.id}`,
      editReturnReason.value
    );
    ElMessage.success("退货原因更新成功！");
    showEditModalStatus.value = false;
    fetchReturnReasons();
  } catch (error) {
    console.error("更新原因时出错！", error);
    ElMessage.error("更新退货原因失败，请稍后再试！");
  }
};

// 计算总页数
const totalPage = computed(() =>
  Math.ceil(totalReturnReasons.value / pageSize.value)
);

// 格式化状态显示
const formatStatus = (row: any) => {
  return row.status === 1 ? "启用" : "不启用";
};

// 组件挂载时获取退货原因列表
onMounted(fetchReturnReasons);

// 监听跳转页码变化
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchReturnReasons();
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
    await fetchReturnReasons();
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
  width: 120px;
  margin-right: 10px;
}

.modal-content .form-group .form-control {
  flex: 1;
}

.modal-content .form-group.textarea-group {
  flex-direction: column;
}

.modal-content .form-group.textarea-group label {
  margin-bottom: 5px;
}

.modal-content .form-group.textarea-group .form-control {
  min-height: 100px;
}

.modal-footer {
  margin-top: 20px;
  text-align: right;
}

.modal-footer .btn {
  margin-left: 10px;
}

@media (max-width: 576px) {
  .modal-content {
    padding: 15px;
  }
  .modal-content .form-group {
    flex-direction: column;
    align-items: flex-start;
  }
  .modal-content .form-group label {
    margin-bottom: 5px;
    width: auto;
  }
  .modal-footer {
    margin-top: 15px;
  }
}
</style>
