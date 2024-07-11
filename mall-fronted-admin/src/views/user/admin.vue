<template>
  <div>
    <h1>管理员列表</h1>
    <div class="search-bar">
      <el-input
        v-model="searchAdminId"
        placeholder="输入用户ID/用户名进行搜索"
        class="search-input"
        style="max-width: 300px"
      />
      <el-button type="primary" @click="searchAdminById">搜索</el-button>
      <el-button @click="showAddAdminModal = true">新增管理员</el-button>
    </div>
    <div>
      <el-table :data="admins" style="width: 100%" height="490">
        <el-table-column
          prop="id"
          label="用户ID"
          align="center"
          header-align="center"
          width="80px"
          fixed
        />
        <el-table-column
          prop="username"
          label="用户名"
          align="center"
          header-align="center"
          fixed
        />
        <el-table-column
          prop="password"
          label="密码"
          width="200px"
          align="center"
          header-align="center"
          show-overflow-tooltip
        />
        <el-table-column
          prop="icon"
          label="头像"
          width="100px"
          align="center"
          header-align="center"
          show-overflow-tooltip
        >
          <template v-slot="scope">
            <img
              :src="scope.row.icon"
              alt="头像"
              style="width: 50px; height: 50px; border-radius: 50%"
            />
          </template>
        </el-table-column>
        <el-table-column
          prop="email"
          label="邮箱"
          align="center"
          header-align="center"
          width="100px"
          show-overflow-tooltip
        />
        <el-table-column
          prop="nickname"
          label="昵称"
          align="center"
          header-align="center"
        />
        <el-table-column
          prop="createTime"
          label="注册时间"
          align="center"
          header-align="center"
          width="100px"
          :formatter="row => formatDate(row.createTime)"
        />
        <el-table-column
          label="操作"
          width="180"
          align="center"
          header-align="center"
          fixed="right"
        >
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              @click="updateAdmin(scope.row.id)"
              >修改</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="deleteAdmin(scope.row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
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
          :max="Math.ceil(totalAdmins / pageSize)"
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

    <!-- 增加/修改模态框 -->
    <div>
      <template v-if="showAddAdminModal">
        <el-dialog v-model="showAddAdminModal" title="新增管理员">
          <el-form
            ref="addAdminForm"
            :model="newAdmin"
            :rules="rules"
            label-width="120px"
            @submit.prevent="addAdmin"
          >
            <el-form-item label="用户名:" prop="username">
              <el-input v-model="newAdmin.username" autocomplete="off" />
            </el-form-item>
            <el-form-item label="密码:" prop="password">
              <el-input
                v-model="newAdmin.password"
                autocomplete="off"
                show-password
              />
            </el-form-item>
            <el-form-item label="邮箱:" prop="email">
              <el-input v-model="newAdmin.email" autocomplete="off" />
            </el-form-item>
            <el-form-item label="昵称:" prop="nickname">
              <el-input v-model="newAdmin.nickname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="头像:" prop="icon">
              <el-input
                v-model="newAdmin.icon"
                autocomplete="off"
                placeholder="请输入头像图片的URL"
              />
              <div style="margin-top: 10px">
                请输入头像图片的 URL，例如：http://example.com/image.jpg
              </div>
              <div v-if="newAdmin.icon" style="margin-top: 10px">
                <label>头像预览:</label>
                <img
                  :src="newAdmin.icon"
                  alt="头像预览"
                  style="
                    max-width: 100px;
                    max-height: 100px;
                    border-radius: 50%;
                  "
                />
              </div>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addAdmin">提交</el-button>
              <el-button @click="showAddAdminModal = false">取消</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </template>
      <template v-else-if="showEditAdminModal">
        <el-dialog v-model="showEditAdminModal" title="修改管理员">
          <el-form
            ref="editAdminForm"
            :model="editAdmin"
            :rules="rules"
            label-width="120px"
            @submit.prevent="updateAdminSubmit"
          >
            <el-form-item label="用户ID">
              <el-input v-model="editAdmin.id" readonly />
            </el-form-item>
            <el-form-item label="用户名:" prop="username">
              <el-input
                v-model="editAdmin.username"
                autocomplete="off"
                readonly
              />
            </el-form-item>
            <el-form-item label="密码:" prop="password">
              <el-input
                v-model="editAdmin.password"
                autocomplete="off"
                show-password
              />
            </el-form-item>
            <el-form-item label="邮箱:" prop="email">
              <el-input v-model="editAdmin.email" autocomplete="off" />
            </el-form-item>
            <el-form-item label="昵称:" prop="nickname">
              <el-input v-model="editAdmin.nickname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="头像:" prop="icon">
              <el-input
                v-model="editAdmin.icon"
                autocomplete="off"
                placeholder="请输入头像图片的URL"
              />
              <div style="margin-top: 10px">
                请输入头像图片的 URL，例如：http://example.com/image.jpg
              </div>
              <div v-if="editAdmin.icon" style="margin-top: 10px">
                <label>头像预览:</label>
                <img
                  :src="editAdmin.icon"
                  alt="头像预览"
                  style="
                    max-width: 100px;
                    max-height: 100px;
                    border-radius: 50%;
                  "
                />
              </div>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateAdminSubmit"
                >提交</el-button
              >
              <el-button @click="showEditAdminModal = false">取消</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </template>
    </div>
    <!-- 删除管理员模态框 -->
    <div>
      <el-dialog
        v-model="dialogVisible"
        title="删除管理员"
        width="500"
        align-center
      >
        <span>通过ID删除该用户</span>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="danger" @click="confirmDeleteAdmin"
              >确认</el-button
            >
          </div>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, computed, reactive } from "vue";
import axios from "axios";
import {
  ElMessage,
  FormRules,
  ElForm,
  ElFormItem,
  ElInput,
  ElButton,
  ElDialog,
  ElMessageBox,
  formatter
} from "element-plus";
import moment from "moment";

// 定义组件名称
defineOptions({
  name: "Admin"
});

// 定义响应式数据
const admins = ref([]);
const totalAdmins = ref(0);
const currentPage = ref(1);
const pageSize = ref(10);
const successMessage = ref(""); // 存储操作成功消息
const jumpPage = ref(1); // 跳转的页码
const searchAdminId = ref(""); // 搜索的管理员ID
const showAddAdminModal = ref(false); // 控制新增管理员模态框的显示
const showEditAdminModal = ref(false); // 控制修改管理员模态框的显示
const addAdminForm = ref(null); // 新建管理员表单引用
const editAdminForm = ref(null); // 修改管理员表单引用
const dialogVisible = ref(false); // 控制删除管理员模态框的显示
const currentAdminId = ref(""); // 当前管理员ID
const newAdmin = ref({
  id: "",
  username: "",
  password: "",
  icon: "",
  email: "",
  nickname: "",
  createTime: "",
  status: ""
}); // 存储新增管理员的信息
const editAdmin = ref({
  id: "",
  username: "",
  password: "",
  icon: "",
  email: "",
  nickname: "",
  createTime: "",
  status: ""
}); // 存储修改管理员的信息

// 直接定义 formatDate 函数
const formatDate = date => {
  if (!date) return "";
  const parsedDate = moment(date);
  if (parsedDate.isValid()) {
    return parsedDate.format("YYYY-MM-DD");
  } else {
    return "日期无效";
  }
};

// 定义管理员状态映射
const adminStatusMap = {
  0: "关闭",
  1: "启用"
};

// 计算总页数
const totalPage = computed(() => Math.ceil(totalAdmins.value / pageSize.value));

// 获取管理员数据的函数
const fetchAdmins = async () => {
  console.log("请求参数：", { page: currentPage.value, size: pageSize.value });
  try {
    const response = await axios.get("http://localhost:8080/admins/pages", {
      params: {
        page: currentPage.value,
        size: pageSize.value
      }
    });
    console.log("接口返回数据：", response.data);
    // 检查HTTP状态码
    if (response.status !== 200) {
      throw new Error("Network response was not ok");
    }
    // 解析JSON数据
    const data = response.data;
    console.log("解析后数据：", data.data);
    // 检查业务状态码
    if (data.code !== 200) {
      throw new Error("Failed to fetch admins: " + data.msg);
    }
    admins.value = data.data.records; // 假设后端返回的数据结构中有一个 records 数组包含管理员数据
    totalAdmins.value = data.data.total; // 假设后端返回的数据结构中有一个 total 属性包含总记录数
  } catch (error) {
    console.error("There was an error fetching the admins!", error);
  }
};

// 搜索管理员的函数
const searchAdminById = async () => {
  // 如果searchAdminId为空，直接调用fetchAdmins函数搜索全部管理员
  if (!searchAdminId.value) {
    fetchAdmins();
    return;
  }

  let searchUrl = `http://localhost:8080/admins/${searchAdminId.value}`; // 默认使用ID搜索
  // 使用正则表达式检查searchAdminId.value是否包含字母，如果包含，则改用按用户名搜索
  if (/[a-zA-Z]/.test(searchAdminId.value)) {
    searchUrl = `http://localhost:8080/admins/username/${searchAdminId.value}`;
  }

  try {
    const response = await axios.get(searchUrl);
    // 检查后端是否返回了管理员数据
    if (response.data.data) {
      admins.value = [response.data.data]; // 将单个管理员对象放入数组中，以统一处理
      totalAdmins.value = 1; // 因为只搜索到一个管理员，所以总数为1
    } else {
      // 如果未找到管理员，显示警告信息并重新加载管理员列表
      ElMessage.warning("未找到该管理员！");
      fetchAdmins();
    }
  } catch (error) {
    // 捕获到错误时，显示错误信息并重新加载管理员列表
    console.error("搜索管理员时发生错误！", error);
    ElMessage.error("未找到该管理员！");
    fetchAdmins();
  }
};

const rules: FormRules = {
  username: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { min: 3, max: 15, message: "用户名长度在 3 到 15 个字符", trigger: "blur" }
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 8, message: "密码长度不能少于 8 个字符", trigger: "blur" }
  ],
  nickname: [
    {
      required: false,
      min: 1,
      max: 10,
      message: "昵称长度在 1 到 10 个字符",
      trigger: "blur"
    }
  ],
  icon: [
    {
      required: false,
      type: "url",
      message: "头像不是有效的URL",
      trigger: "blur"
    }
  ],
  email: [
    { required: true, message: "请输入邮箱地址", trigger: "blur" },
    {
      pattern: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
      message: "邮箱格式不正确",
      trigger: "blur"
    }
  ]
};

// 新增管理员的函数
const addAdmin = async () => {
  addAdminForm.value.validate(async valid => {
    if (!addAdminForm.value) {
      console.log("表单引用不存在！");
      return;
    }
    if (valid) {
      try {
        await axios.post("http://localhost:8080/admins", newAdmin.value);
        ElMessage.success("管理员新增成功！");
        // 关闭模态框，刷新列表，重置表单等操作
        showAddAdminModal.value = false;
        fetchAdmins();
        newAdmin.value = {
          id: "",
          username: "",
          password: "",
          icon: "",
          email: "",
          nickname: "",
          createTime: "",
          status: ""
        };
      } catch (error) {
        console.error("新增管理员失败", error);
        ElMessage.error("新增管理员失败，请稍后再试！");
      }
    } else {
      ElMessage.error("请检查输入是否正确！");
    }
  });
};

const getAdminDetails = async adminId => {
  const detailsUrl = `http://localhost:8080/admins/${adminId}`;
  try {
    const response = await axios.get(detailsUrl);
    if (response.data.data) {
      return response.data.data; // 返回管理员详细信息
    } else {
      console.warn("未找到该管理员！");
      ElMessage.warning("未找到该管理员！");
      return null;
    }
  } catch (error) {
    console.error("获取管理员详细信息时发生错误！", error);
    ElMessage.error("获取管理员信息失败！");
    return null;
  }
};

// 修改管理员的逻辑
const updateAdmin = async adminId => {
  const adminDetails = adminId ? await getAdminDetails(adminId) : {};
  console.log("管理员详细信息：", adminDetails);
  // 使用Object.assign更新editAdmin对象，确保响应性
  Object.assign(editAdmin.value, adminDetails);
  // 显示模态框的逻辑
  showEditAdminModal.value = true;
};

// 更新管理员信息的API调用
const updateAdminAPI = async admin => {
  try {
    const response = await axios.put(
      `http://localhost:8080/admins/${admin.id}`,
      admin
    );
    ElMessage.success("管理员信息已更新！");
    return response.data.data;
  } catch (error) {
    console.error("更新管理员信息失败:", error);
    ElMessage.error("更新管理员信息失败，请稍后再试！");
    throw error;
  }
};

// 提交表单，更新成员信息的逻辑
const updateAdminSubmit = async () => {
  try {
    await updateAdminAPI(editAdmin.value);
    showEditAdminModal.value = false;
    console.log("管理员信息已更新！");
  } catch {
    console.error("更新管理员信息失败！");
  }
};

// 删除管理员的函数
const deleteAdmin = async adminId => {
  currentAdminId.value = adminId;
  // 显示模态框的逻辑
  dialogVisible.value = true;
};

const confirmDeleteAdmin = async () => {
  if (!currentAdminId.value) {
    console.error("管理员ID为空！");
    ElMessage.error("管理员ID为空！");
    return;
  }
  try {
    await axios.delete(`http://localhost:8080/admins/${currentAdminId.value}`);
    fetchAdmins(); // 删除成功后刷新管理员列表
    ElMessage.success("删除成功！"); // 显示 Element Plus 的成功消息提示
    dialogVisible.value = false;
  } catch (error) {
    console.error("There was an error deleting the order!", error);
    ElMessage.error("删除失败，请稍后再试！"); // 显示 Element Plus 的错误消息提示
    dialogVisible.value = false;
  }
};

// 在组件挂载时获取管理员数据
onMounted(fetchAdmins);

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchAdmins();
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
    fetchAdmins();
  }
};
</script>

<style scoped>
.el-table .cell {
  font-size: 12px; /* 缩小字体大小 */
  white-space: normal; /* 允许换行 */
  word-break: break-all; /* 对于长单词或URL进行换行 */
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
  padding: 8px 10px; /* 增加内边距以调整输入框的大小 */
  border: none; /* 确保移除所有边框 */
  border-radius: 0; /* 移除圆角 */
  margin-right: 15px; /* 增加与搜索按钮的间隔 */
}

.search-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 8px 15px; /* 调整按钮的内边距以匹配输入框的高度 */
  border-radius: 8px; /* 增加边框圆角 */
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
  padding: 8px 15px; /* 调整按钮的内边距以匹配输入框的高度 */
  margin-left: 10px; /* 确保与搜索按钮有一定间隔 */
  border-radius: 8px; /* 增加边框圆角 */
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
  z-index: 1000;
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
  /* 设置表单项之间的底部间距为 15px */
}

.modal-content .form-group label {
  min-width: 120px;
  /* 根据需要调整标签的最小宽度 */
  margin-right: 20px;
  /* 调整标签与输入框之间的间距 */
}

.modal-content input,
.modal-content button {
  flex: 1;
  /* 让输入框和按钮占据剩余的空间 */
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
