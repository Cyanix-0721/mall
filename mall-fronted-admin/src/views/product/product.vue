<template>
  <div>
    <h1>商品管理</h1>
    <div class="search-bar">
      <input
        v-model="searchProductNameOrSn"
        type="text"
        placeholder="根据商品名称或货号模糊搜索"
        class="search-input"
      />
      <button class="search-button" @click="searchProductByNameOrSn">
        搜索
      </button>
      <input
        v-model="searchProductId"
        type="text"
        placeholder="根据商品ID搜索"
        class="search-input"
      />
      <button class="search-button" @click="searchProductById">搜索</button>
      <button class="add-button" @click="showAddProductModal = true">
        新增商品
      </button>
    </div>
    <table>
      <thead>
        <tr>
          <th>商品ID</th>
          <th>商品名称</th>
          <th>品牌名称</th>
          <th>商品价格</th>
          <th>商品货号</th>
          <th>上架状态</th>
          <th>新品状态</th>
          <th>推荐状态</th>
          <th>排序</th>
          <th>库存</th>
          <th>销量</th>
          <th>编辑</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.id }}</td>
          <td>{{ product.name }}</td>
          <td>{{ product.brandName }}</td>
          <td>{{ product.price }}</td>
          <td>{{ product.productSn }}</td>
          <td>{{ product.publishStatus }}</td>
          <td>{{ product.newStatus }}</td>
          <td>{{ product.recommandStatus }}</td>
          <td>{{ product.sort }}</td>
          <td>{{ product.stock }}</td>
          <td>{{ product.sale }}</td>
          <td>
            <button
              class="update-button"
              @click="showEditProductModal(product)"
            >
              修改
            </button>
            <button class="delete-button" @click="deleteProduct(product.id)">
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
          :max="Math.ceil(totalProducts / pageSize)"
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

    <!-- 新增商品模态框 -->
    <div v-if="showAddProductModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showAddProductModal = false"
          >&times;</span
        >
        <h2>新增商品</h2>
        <form @submit.prevent="addProduct">
          <div class="form-group">
            <label for="name">商品名称:</label>
            <input id="name" v-model="newProduct.name" type="text" required />
          </div>
          <div class="form-group">
            <label for="brandName">品牌名称:</label>
            <input
              id="brandName"
              v-model="newProduct.brandName"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="price">商品价格:</label>
            <input
              id="price"
              v-model="newProduct.price"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="productSn">商品货号:</label>
            <input
              id="productSn"
              v-model="newProduct.productSn"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="publishStatus">上架状态:</label>
            <input
              id="publishStatus"
              v-model="newProduct.publishStatus"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="newStatus">新品状态:</label>
            <input
              id="newStatus"
              v-model="newProduct.newStatus"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="recommandStatus">推荐状态:</label>
            <input
              id="recommandStatus"
              v-model="newProduct.recommandStatus"
              type="stock"
              required
            />
          </div>
          <div class="form-group">
            <label for="sort">排序:</label>
            <input id="sort" v-model="newProduct.sort" type="number" required />
          </div>
          <div class="form-group">
            <label for="stock">库存:</label>
            <input
              id="stock"
              v-model="newProduct.stock"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="sale">销量:</label>
            <input id="sale" v-model="newProduct.sale" type="number" required />
          </div>
          <div class="form-group">
            <button type="submit" class="submit-button">提交</button>
          </div>
        </form>
      </div>
    </div>
    <!-- 更新商品模态框 -->
    <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showEditModal = false">&times;</span>
        <h2>更新商品</h2>
        <form @submit.prevent="updateProduct">
          <div class="form-group">
            <label for="name">商品名称:</label>
            <input id="name" v-model="editProduct.name" type="text" required />
          </div>
          <div class="form-group">
            <label for="brandName">品牌名称:</label>
            <input
              id="brandName"
              v-model="editProduct.brandName"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="price">商品价格:</label>
            <input
              id="price"
              v-model="editProduct.price"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="productSn">商品货号:</label>
            <input
              id="productSn"
              v-model="editProduct.productSn"
              type="text"
              required
            />
          </div>
          <div class="form-group">
            <label for="publishStatus">上架状态:</label>
            <input
              id="publishStatus"
              v-model="editProduct.publishStatus"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="newStatus">新品状态:</label>
            <input
              id="newStatus"
              v-model="editProduct.newStatus"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="recommandStatus">推荐状态:</label>
            <input
              id="recommandStatus"
              v-model="editProduct.recommandStatus"
              type="stock"
              required
            />
          </div>
          <div class="form-group">
            <label for="sort">排序:</label>
            <input
              id="sort"
              v-model="editProduct.sort"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="stock">库存:</label>
            <input
              id="stock"
              v-model="editProduct.stock"
              type="number"
              required
            />
          </div>
          <div class="form-group">
            <label for="sale">销量:</label>
            <input
              id="sale"
              v-model="editProduct.sale"
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
  name: "Product"
});

// 定义响应式数据
const products = ref([]);
const totalProducts = ref(0);
const currentPage = ref(1);
const pageSize = ref(5);
const successMessage = ref(""); // 存储删除成功消息
const jumpPage = ref(1); // 跳转的页码
const searchProductId = ref(""); // 搜索的商品ID
const showAddProductModal = ref(false); // 控制新增商品模态框的显示
const showEditModal = ref(false); // 控制模态框显示状态
const searchProductNameOrSn = ref("");
const newProduct = ref({
  id: "",
  name: "",
  brandName: "",
  price: "",
  productSn: "",
  publishStatus: "",
  newStatus: "",
  recommandStatus: "",
  sort: "",
  stock: "",
  sale: ""
}); // 存储新增商品的信息

// 计算总页数
const totalPage = computed(() =>
  Math.ceil(totalProducts.value / pageSize.value)
);

const fetchProducts = async () => {
  try {
    const response = await axios.get("http://localhost:8080/product/list", {
      params: {
        pageSize: pageSize.value,
        pageNum: currentPage.value
      }
    });
    products.value = response.data.data.list;
    totalProducts.value = response.data.data.total;
  } catch (error) {
    console.error("There was an error fetching the products!", error);
  }
};

// 搜索商品的函数
const searchProductById = async () => {
  if (!searchProductId.value) {
    ElMessage.warning("请输入商品ID！");
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8080/product/updateInfo/${searchProductId.value}`
    );
    if (response.data.data) {
      products.value = [response.data.data]; // 假设后端返回的数据结构为单个商品对象
      totalProducts.value = 1;
    } else {
      ElMessage.warning("未找到该商品！");
      fetchProducts(); // 如果未找到商品，重新加载商品列表
    }
  } catch (error) {
    console.error("There was an error searching the product!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchProducts(); // 如果搜索失败，重新加载商品列表
  }
};
// 搜索商品的函数，现在根据名称或货号模糊搜索
const searchProductByNameOrSn = async () => {
  // 检查是否输入了搜索关键词
  if (!searchProductNameOrSn.value) {
    ElMessage.warning("请输入商品名称或货号！");
    return;
  }

  try {
    // 发起GET请求，请求路径包含搜索的商品名称或货号参数
    const response = await axios.get(
      `http://localhost:8080/product/simpleList?keyword=${searchProductNameOrSn.value}`
    );

    // 处理响应结果
    if (response.data.data && response.data.data.length > 0) {
      products.value = response.data.data; // 假设后端返回的是包含多个匹配项的数组
      totalProducts.value = response.data.data.length;
    } else {
      ElMessage.warning("未找到该商品名称或货号的商品！");
      fetchProducts(); // 如果未找到，重新加载商品列表
    }
  } catch (error) {
    // 错误处理
    console.error(
      "There was an error searching the product by name or SKU!",
      error
    );
    ElMessage.error("搜索失败，请稍后再试！");
    fetchProducts(); // 如果搜索失败，重新加载商品列表
  }
};

// 新增商品的函数
const addProduct = async () => {
  try {
    await axios.post("http://localhost:8080/product/create", newProduct.value);
    ElMessage.success("商品新增成功！");
    showAddProductModal.value = false; // 关闭模态框
    fetchProducts(); // 刷新商品列表
    newProduct.value = {
      id: "",
      name: "",
      brandName: "",
      price: "",
      productSn: "",
      publishStatus: "",
      newStatus: "",
      recommandStatus: "",
      sort: "",
      stock: "",
      sale: ""
    }; // 重置表单
  } catch (error) {
    console.error("There was an error adding the product!", error);
    ElMessage.error("新增商品失败，请稍后再试！");
  }
};

// 删除商品的函数
const deleteProduct = async id => {
  try {
    await axios.delete(`http://localhost:8080/product/delete/${id}`);
    fetchProducts(); // 删除成功后刷新商品列表
    ElMessage.success("删除成功！"); // 显示 Element Plus 的成功消息提示
  } catch (error) {
    console.error("There was an error deleting the product!", error);
    ElMessage.error("删除失败，请稍后再试！"); // 显示 Element Plus 的错误消息提示
  }
};
// 要更新的商品数据模型
const editProduct = ref({
  id: "",
  name: "",
  brandName: "",
  price: "",
  productSn: "",
  publishStatus: "",
  newStatus: "",
  recommandStatus: "",
  sort: "",
  stock: "",
  sale: ""
});

const showEditProductModal = product => {
  console.log("Selected product data:", product); // 调试输出
  editProduct.value = { ...product }; // 将选中的商品信息赋值给 editProduct
  showEditModal.value = true; // 显示编辑商品模态框
};
// 修改商品的函数
const updateProduct = async () => {
  try {
    await axios.post(
      `http://localhost:8080/product/update/${editProduct.value.id}`,
      editProduct.value
    );
    ElMessage.success("商品更新成功！");
    showEditModal.value = false; // 关闭模态框
    fetchProducts(); // 刷新商品列表
  } catch (error) {
    console.error("There was an error updating the product!", error);
    ElMessage.error("更新商品失败，请稍后再试！");
  }
};
// 在组件挂载时获取商品数据
onMounted(fetchProducts);

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchProducts();
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
    fetchProducts();
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
