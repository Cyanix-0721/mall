<template>
  <div>
    <h1>商品分类管理</h1>
    <div class="search-bar">
      <input
        v-model="searchProductCategoryId"
        type="text"
        placeholder="根据商品分类ID搜索"
        class="search-input"
      />
      <button class="search-button" @click="searchProductCategoryById">
        搜索
      </button>
      <button class="add-button" @click="showAddProductCategoryModal = true">
        新增商品分类
      </button>
    </div>
    <table>
      <thead>
        <tr>
          <th>分类ID</th>
          <th>分类名称</th>
          <th>商品数量</th>
          <th>商品单位</th>
          <th>排序</th>
          <th>编辑</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="productCategory in productCategorys"
          :key="productCategory.id"
        >
          <td>{{ productCategory.id }}</td>
          <td>{{ productCategory.name }}</td>
          <td>{{ productCategory.productCount }}</td>
          <td>{{ productCategory.productUnit }}</td>
          <td>{{ productCategory.sort }}</td>
          <td>
            <button
              class="update-button"
              @click="showEditProductCategoryModal(productCategory)"
            >
              修改
            </button>
            <button
              class="delete-button"
              @click="deleteProductCategory(productCategory.id)"
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
          :max="Math.ceil(totalProductCategorys / pageSize)"
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

    <!-- 新增分类模态框 -->
    <div v-if="showAddProductCategoryModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showAddProductCategoryModal = false"
          >&times;</span
        >
        <h2>新增商品分类</h2>
        <form @submit.prevent="addProductCategory">
          <div class="form-group">
            <label for="name">分类名称:</label>
            <input
              id="name"
              v-model="newProductCategory.name"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="productCount">商品数量:</label>
            <input
              id="productCount"
              v-model="newProductCategory.productCount"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="productUnit">商品单位:</label>
            <input
              id="productUnit"
              v-model="newProductCategory.productUnit"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="sort">排序:</label>
            <input
              id="sort"
              v-model="newProductCategory.sort"
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
    <!-- 更新分类模态框 -->
    <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showEditModal = false">&times;</span>
        <h2>更新商品</h2>
        <form @submit.prevent="updateProductCategory">
          <div class="form-group">
            <label for="name">分类名称:</label>
            <input
              id="name"
              v-model="editProductCategory.name"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="productCount">商品数量:</label>
            <input
              id="productCount"
              v-model="editProductCategory.productCount"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="productUnit">商品单位:</label>
            <input
              id="productUnit"
              v-model="editProductCategory.productUnit"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="sort">排序:</label>
            <input
              id="sort"
              v-model="editProductCategory.sort"
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
  name: "ProductCategory"
});

// 定义响应式数据
const productCategorys = ref([]);
const totalProductCategorys = ref(0);
const currentPage = ref(1);
const pageSize = ref(5);
const successMessage = ref(""); // 存储删除成功消息
const jumpPage = ref(1); // 跳转的页码
const searchProductCategoryId = ref(""); // 搜索的分类ID
const showAddProductCategoryModal = ref(false); // 控制新增分类模态框的显示
const showEditModal = ref(false); // 控制模态框显示状态
const newProductCategory = ref({
  id: "",
  name: "",
  productCount: "",
  productUnit: "",
  sort: ""
}); // 存储新增分类的信息

// 计算总页数
const totalPage = computed(() =>
  Math.ceil(totalProductCategorys.value / pageSize.value)
);

const fetchProductCategorys = async () => {
  try {
    const response = await axios.get(
      "http://localhost:8080/productCategory/list",
      {
        params: {
          pageSize: pageSize.value,
          pageNum: currentPage.value
        }
      }
    );
    productCategorys.value = response.data.data.list;
    totalProductCategorys.value = response.data.data.total;
  } catch (error) {
    console.error("There was an error fetching the productcategorys!", error);
  }
};

// 搜索分类的函数
const searchProductCategoryById = async () => {
  if (!searchProductCategoryId.value) {
    ElMessage.warning("请输入分类ID！");
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8080/productCategory/${searchProductCategoryId.value}`
    );
    if (response.data.data) {
      productCategorys.value = [response.data.data]; // 假设后端返回的数据结构为单个商品对象
      totalProductCategorys.value = 1;
    } else {
      ElMessage.warning("未找到该分类！");
      fetchProductCategorys(); // 如果未找到分类，重新加载分类列表
    }
  } catch (error) {
    console.error("There was an error searching the product!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchProductCategorys(); // 如果搜索失败，重新加载分类列表
  }
};
// 新增分类的函数
const addProductCategory = async () => {
  try {
    await axios.post(
      "http://localhost:8080/productCategory/create",
      newProductCategory.value
    );
    ElMessage.success("分类新增成功！");
    showAddProductCategoryModal.value = false; // 关闭模态框
    fetchProductCategorys(); // 刷新分类列表
    newProductCategory.value = {
      id: "",
      name: "",
      productCount: "",
      productUnit: "",
      sort: ""
    }; // 重置表单
  } catch (error) {
    console.error("There was an error adding the productCategory!", error);
    ElMessage.error("新增分类失败，请稍后再试！");
  }
};

// 删除分类的函数
const deleteProductCategory = async id => {
  try {
    await axios.delete(`http://localhost:8080/productCategory/delete/${id}`);
    fetchProductCategorys(); // 删除成功后刷新分类列表
    ElMessage.success("删除成功！"); // 显示 Element Plus 的成功消息提示
  } catch (error) {
    console.error("There was an error deleting the product!", error);
    ElMessage.error("删除失败，请稍后再试！"); // 显示 Element Plus 的错误消息提示
  }
};
// 要更新的分类数据模型
const editProductCategory = ref({
  id: "",
  name: "",
  productCount: "",
  productUnit: "",
  sort: ""
});

const showEditProductCategoryModal = productCategory => {
  console.log("Selected product data:", productCategory); // 调试输出
  editProductCategory.value = { ...productCategory }; // 将选中的分类信息赋值给 editProductCategory
  showEditModal.value = true; // 显示编辑分类模态框
};
// 修改分类的函数
const updateProductCategory = async () => {
  try {
    await axios.put(
      `http://localhost:8080/productCategory/update/${editProductCategory.value.id}`,
      editProductCategory.value
    );
    ElMessage.success("修改商品分类成功！");
    showEditModal.value = false; // 关闭模态框
    fetchProductCategorys(); // 刷新分类列表
  } catch (error) {
    console.error("There was an error updating the productCategory!", error);
    ElMessage.error("更新分类失败，请稍后再试！");
  }
};
// 在组件挂载时获取商品数据
onMounted(fetchProductCategorys);

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchProductCategorys();
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
    fetchProductCategorys();
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
