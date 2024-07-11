<template>
  <div>
    <h1>品牌管理</h1>
    <div class="search-bar">
      <button class="add-button" @click="showAddBrandModal = true">
        新增品牌
      </button>
    </div>
    <table>
      <thead>
        <tr>
          <th>品牌ID</th>
          <th>品牌编号</th>
          <th>品牌名称</th>
          <th>推荐状态</th>
          <th>排序</th>
          <th>编辑</th>
          <th>删除</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="brand in brands" :key="brand.id">
          <td>{{ brand.id }}</td>
          <td>{{ brand.brandId }}</td>
          <td>{{ brand.brandName }}</td>
          <td>{{ brand.recommendStatus }}</td>
          <td>{{ brand.sort }}</td>
          <td>
            <button class="update-button" @click="showEditBrandModal(brand)">
              编辑
            </button>
          </td>
          <td>
            <button class="delete-button" @click="deleteBrand(brand.id)">
              删除
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="pagination">
      <button class="pagination-button" :disabled="currentPage === 1" @click="handlePageChange(currentPage - 1)">
        上一页
      </button>
      <span>
        第
        <input v-model.number="jumpPage" type="number" min="1" :max="Math.ceil(totalNewproducts / pageSize)"
          style="width: 50px; text-align: center" />
        页，共
        {{ totalPage }}
        页
      </span>
      <button class="pagination-button" :disabled="currentPage === totalPage"
        @click="handlePageChange(currentPage + 1)">
        下一页
      </button>
    </div>
    <div v-if="successMessage" class="success-message">
      {{ successMessage }}
    </div>

    <!-- 新增新品模态框 -->
    <div v-if="showAddBrandModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showAddBrandModal = false">&times;</span>
        <h2>新增新品</h2>
        <form @submit.prevent="addBrand">
          <div class="form-group">
            <label for="brandId">品牌编号:</label>
            <input id="brandId" v-model="newBrand.brandId" type="number" required />
          </div>
          <div class="form-group">
            <label for="brandName">品牌名称:</label>
            <input id="brandName" v-model="newBrand.brandName" type="text" required />
          </div>
          <div class="form-group">
            <label for="recommendStatus">推荐状态:</label>
            <input id="recommendStatus" v-model="newBrand.recommendStatus" type="number" required />
          </div>
          <div class="form-group">
            <label for="sort">排序:</label>
            <input id="sort" type="number" v-model="newBrand.sort" required />
          </div>
          <div class="form-group">
            <button type="submit" class="submit-button">提交</button>
          </div>
        </form>
      </div>
    </div>
    <!-- 更新新品模态框 -->
    <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <span class="close-button" @click="showEditModal = false">&times;</span>
        <h2>更新品牌管理</h2>
        <form @submit.prevent="updateBrand">
          <div class="form-group">
            <label for="brandId">品牌编号:</label>
            <input id="brandId" v-model="editBrand.brandId" type="number" required />
          </div>
          <div class="form-group">
            <label for="brandName">品牌名称:</label>
            <input id="brandName" v-model="editBrand.brandName" type="text" required />
          </div>
          <div class="form-group">
            <label for="recommendStatus">推荐状态:</label>
            <input id="recommendStatus" v-model="editBrand.recommendStatus" type="number" required />
          </div>
          <div class="form-group">
            <label for="sort">排序:</label>
            <input id="sort" v-model="editBrand.sort" type="number" required />
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
  name: "Brand"
});

// 定义响应式数据
const brands = ref([]);
const totalbrands = ref(0);
const currentPage = ref(1);
const pageSize = ref(5);
const successMessage = ref(""); // 存储删除成功消息
const jumpPage = ref(1); // 跳转的页码
const searchBrandId = ref(""); // 搜索的优惠劵ID
const showAddBrandModal = ref(false); // 控制新增优惠劵模态框的显示
const showEditModal = ref(false); // 控制模态框显示状态
const searchNBrandName = ref("");
const newBrand = ref({
  brandId: "",
  brandName: "",
  recommendStatus: "",
  sort: ""
}); // 存储新增优惠劵的信息

// 计算总页数
const totalPage = computed(() => Math.ceil(totalbrands.value / pageSize.value));

// 获取品牌数据的函数
const fetchBrands = async () => {
  try {
    const response = await axios.get("http://localhost:8080/home/brand/list", {
      params: {
        pageSize: pageSize.value,
        pageNum: currentPage.value
      }
    });
    console.log(response.data.data);
    brands.value = response.data.data.list; // 假设后端返回的数据结构中有一个 records 数组包含订单数据
    console.log(brands.value);
    totalbrands.value = response.data.data.total; // 假设后端返回的数据结构中有一个 total 属性包含总记录数
  } catch (error) {
    console.error("There was an error fetching the newproducts!", error);
  }
};

// 搜索新品的函数
const searchCouponById = async () => {
  if (!searchNewproductId.value) {
    ElMessage.warning("请输入品牌ID！");
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8070/coupons/${searchCouponById.value}`
    );
    if (response.data) {
      newproducts.value = [response.data]; // 假设后端返回的数据结构为单个优惠劵对象
      totalNewproducts.value = 1;
    } else {
      ElMessage.warning("未找到该优惠劵！");
      fetchNewproducts(); // 如果未找到优惠劵，重新加载优惠劵列表
    }
  } catch (error) {
    console.error("There was an error searching the coupon!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchNewproducts(); // 如果搜索失败，重新加载优惠劵列表
  }
};
// 搜索优惠劵的函数，现在根据名称搜索
const searchBrandByName = async () => {
  if (!searchBrandName.value) {
    ElMessage.warning("请输入优惠劵名称！");
    return;
  }
  try {
    const response = await axios.get(
      `http://localhost:8070/coupons/name?name=${searchCouponName.value}` // 更新API路径以包含名称参数
    );
    if (response.data && response.data.length > 0) {
      newproducts.value = response.data; // 假设后端返回的是包含多个匹配项的数组
      totalNewproducts.value = response.data.length;
    } else {
      ElMessage.warning("未找到该优惠劵名称的优惠劵！");
      fetchNewproducts(); // 如果未找到，重新加载优惠劵列表
    }
  } catch (error) {
    console.error("There was an error searching the coupon by name!", error);
    ElMessage.error("搜索失败，请稍后再试！");
    fetchNewproducts(); // 如果搜索失败，重新加载优惠劵列表
  }
};

// 新增品牌的函数
const addBrand = async () => {
  if (!newBrand.value.brandId) {
    ElMessage.warning("请填写所有必填项！");
    return;
  }

  const brandList = [newBrand.value];

  try {
    await axios.post("http://localhost:8080/home/brand/create", brandList);
    ElMessage.success("品牌新增成功！");
    showAddBrandModal.value = false;
    fetchBrands();
    newBrand.value = {
      brandId: "",
      brandName: "",
      recommendStatus: "",
      sort: ""
    }; // Reset the form
  } catch (error) {
    console.error("There was an error adding the new brand!", error);
    ElMessage.error("新增品牌失败，请稍后再试！");
  }
};

// 删除新品的函数
const deleteBrand = async id => {
  try {
    await axios.delete(`http://localhost:8080/home/brand/delete/${id}`);
    fetchBrands(); // 删除成功后刷新优惠劵列表
    ElMessage.success("删除成功！"); // 显示 Element Plus 的成功消息提示
  } catch (error) {
    console.error("There was an error deleting the newproduct!", error);
    ElMessage.error("删除失败，请稍后再试！"); // 显示 Element Plus 的错误消息提示
  }
};
// 要更新的优惠券数据模型
// 要更新的优惠券数据模型
const editBrand = ref({
  id: "",
  brandId: "",
  brandName: "",
  recommendStatus: "",
  sort: ""
});

const showEditBrandModal = brand => {
  console.log("Selected brand data:", brand); // 调试输出
  editBrand.value = { ...brand }; // 将选中的优惠劵信息赋值给 editCoupon
  showEditModal.value = true; // 显示编辑优惠劵模态框
};
// 编辑优惠劵的函数
const updateBrand = async () => {
  try {
    await axios.put(
      `http://localhost:8080/home/brand/update/${editBrand.value.id}`,
      editBrand.value
    );
    ElMessage.success("品牌更新成功！");
    showEditModal.value = false; // 关闭模态框
    fetchBrands(); // 刷新优惠劵列表
  } catch (error) {
    console.error("There was an error updating the brand!", error);
    ElMessage.error("更新品牌失败，请稍后再试！");
  }
};
// 在组件挂载时获取优惠劵数据
onMounted(fetchBrands);

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchBrands();
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
    fetchBrands();
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
