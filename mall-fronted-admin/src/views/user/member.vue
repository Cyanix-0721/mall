<template>
  <div>
    <h1>会员列表</h1>
    <div class="search-bar">
      <el-select v-model="searchType" placeholder="请选择" style="width: 120px">
        <el-option label="用户ID" value="userId" />
        <el-option label="用户名" value="username" />
      </el-select>
      <el-input
        v-model="searchQuery"
        class="search-input"
        placeholder="请输入搜索内容"
        style="max-width: 300px; margin-left: 10px"
      />
      <el-button type="primary" @click="search">搜索</el-button>
      <el-button @click="showAddMemberModal = true">新增用户</el-button>
    </div>
    <div>
      <el-table :data="members" style="width: 100%" height="490">
        <el-table-column
          prop="id"
          label="用户ID"
          align="center"
          header-align="center"
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
          prop="nickname"
          label="昵称"
          align="center"
          header-align="center"
        />
        <el-table-column
          prop="phone"
          label="手机号码"
          align="center"
          header-align="center"
          width="110px"
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
          prop="gender"
          label="性别"
          align="center"
          header-align="center"
          width="60px"
          :formatter="genderFormatter"
        />
        <el-table-column
          prop="birthday"
          label="生日"
          align="center"
          header-align="center"
          width="100px"
          :formatter="row => formatDate(row.birthday)"
        />
        <el-table-column
          prop="city"
          label="所在城市"
          align="center"
          header-align="center"
        />
        <el-table-column
          prop="personalizedSignature"
          label="个性签名"
          align="center"
          header-align="center"
          show-overflow-tooltip
        />
        <el-table-column
          prop="memberLevelId"
          label="会员等级"
          align="center"
          header-align="center"
          :formatter="memberLevelFormatter"
        />
        <el-table-column
          prop="integration"
          label="积分"
          align="center"
          header-align="center"
        />
        <el-table-column
          prop="growth"
          label="成长值"
          align="center"
          header-align="center"
        />
        <el-table-column
          prop="historyIntegration"
          label="历史积分"
          align="center"
          header-align="center"
        />
        <el-table-column
          label="操作"
          width="180"
          align="center"
          header-align="center"
          fixed="right"
        >
          <template #default="scope">
            <div>
              <div>
                <el-button
                  size="small"
                  type="info"
                  @click="openAddress(scope.row.id)"
                  >收货</el-button
                >
                <el-button
                  size="small"
                  type="info"
                  @click="openCouponHistory(scope.row.id)"
                  >优惠</el-button
                >
              </div>
              <div>
                <el-button
                  size="small"
                  type="primary"
                  @click="updateMember(scope.row.id)"
                  >修改</el-button
                >
                <el-button
                  size="small"
                  type="danger"
                  @click="deleteMember(scope.row.id)"
                  >删除</el-button
                >
              </div>
            </div>
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
          :max="Math.ceil(totalMembers / pageSize)"
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

    <!-- 会员增加/修改模态框 -->
    <div>
      <template v-if="showAddMemberModal">
        <el-dialog v-model="showAddMemberModal" title="新增会员">
          <el-form
            ref="addMemberForm"
            :model="newMember"
            :rules="rules"
            label-width="120px"
            @submit.prevent="addMember"
          >
            <el-form-item label="用户名:" prop="username">
              <el-input v-model="newMember.username" autocomplete="off" />
            </el-form-item>
            <el-form-item label="密码:" prop="password">
              <el-input
                v-model="newMember.password"
                autocomplete="off"
                show-password
              />
            </el-form-item>
            <el-form-item label="电话:" prop="phone">
              <el-input v-model="newMember.phone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="昵称:" prop="nickname">
              <el-input v-model="newMember.nickname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="头像:" prop="icon">
              <el-input
                v-model="newMember.icon"
                autocomplete="off"
                placeholder="请输入头像图片的URL"
              />
              <div style="margin-top: 10px">
                请输入头像图片的 URL，例如：http://example.com/image.jpg
              </div>
              <div v-if="newMember.icon" style="margin-top: 10px">
                <label>头像预览:</label>
                <img
                  :src="newMember.icon"
                  alt="头像预览"
                  style="
                    max-width: 100px;
                    max-height: 100px;
                    border-radius: 50%;
                  "
                />
              </div>
            </el-form-item>
            <el-form-item label="性别:" prop="gender">
              <el-select v-model="newMember.gender" placeholder="请选择">
                <el-option label="未知" :value="0" />
                <el-option label="男" :value="1" />
                <el-option label="女" :value="2" />
              </el-select>
            </el-form-item>
            <el-form-item label="生日:" prop="birthday">
              <el-date-picker
                v-model="newMember.birthday"
                type="date"
                placeholder="选择日期"
                format="YYYY-MM-DD"
                value-format="YYYY-MM-DD"
                autocomplete="off"
              />
            </el-form-item>
            <el-form-item label="城市:" prop="city">
              <el-input v-model="newMember.city" autocomplete="off" />
            </el-form-item>
            <el-form-item label="个性签名:" prop="personalizedSignature">
              <el-input
                v-model="newMember.personalizedSignature"
                autocomplete="off"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addMember">提交</el-button>
              <el-button @click="showAddMemberModal = false">取消</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </template>
      <template v-else-if="showEditMemberModal">
        <el-dialog v-model="showEditMemberModal" title="修改会员">
          <el-form
            ref="editMemberForm"
            :model="editMember"
            :rules="rules"
            label-width="120px"
            @submit.prevent="updateMemberSubmit"
          >
            <el-form-item label="用户ID">
              <el-input v-model="editMember.id" readonly />
            </el-form-item>
            <el-form-item label="用户名:" prop="username">
              <el-input
                v-model="editMember.username"
                autocomplete="off"
                readonly
              />
            </el-form-item>
            <el-form-item label="密码:" prop="password">
              <el-input
                v-model="editMember.password"
                autocomplete="off"
                show-password
              />
            </el-form-item>
            <el-form-item label="电话:" prop="phone">
              <el-input v-model="editMember.phone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="昵称:" prop="nickname">
              <el-input v-model="editMember.nickname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="头像:" prop="icon">
              <el-input
                v-model="editMember.icon"
                autocomplete="off"
                placeholder="请输入头像图片的URL"
              />
              <div style="margin-top: 10px">
                请输入头像图片的 URL，例如：http://example.com/image.jpg
              </div>
              <div v-if="editMember.icon" style="margin-top: 10px">
                <label>头像预览:</label>
                <img
                  :src="editMember.icon"
                  alt="头像预览"
                  style="
                    max-width: 100px;
                    max-height: 100px;
                    border-radius: 50%;
                  "
                />
              </div>
            </el-form-item>
            <el-form-item label="性别:" prop="gender">
              <el-select v-model="editMember.gender" placeholder="请选择">
                <el-option label="未知" :value="0" />
                <el-option label="男" :value="1" />
                <el-option label="女" :value="2" />
              </el-select>
            </el-form-item>
            <el-form-item label="生日:" prop="birthday">
              <el-date-picker
                v-model="editMember.birthday"
                type="date"
                placeholder="选择日期"
                format="YYYY-MM-DD"
                value-format="YYYY-MM-DD"
                autocomplete="off"
              />
            </el-form-item>
            <el-form-item label="城市:" prop="city">
              <el-input v-model="editMember.city" autocomplete="off" />
            </el-form-item>
            <el-form-item label="个性签名:" prop="personalizedSignature">
              <el-input
                v-model="editMember.personalizedSignature"
                autocomplete="off"
              />
            </el-form-item>
            <el-form-item label="会员等级ID:" prop="memberLevelId">
              <el-input v-model="editMember.memberLevelId" autocomplete="off" />
            </el-form-item>
            <el-form-item label="积分:" prop="integration">
              <el-input v-model="editMember.integration" autocomplete="off" />
            </el-form-item>
            <el-form-item label="成长值:" prop="growth">
              <el-input v-model="editMember.growth" autocomplete="off" />
            </el-form-item>
            <el-form-item label="历史积分:" prop="historyIntegration">
              <el-input
                v-model="editMember.historyIntegration"
                autocomplete="off"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateMemberSubmit"
                >提交</el-button
              >
              <el-button @click="showEditMemberModal = false">取消</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </template>
    </div>
    <!-- 会员删除模态框 -->
    <div>
      <el-dialog
        v-model="dialogVisible"
        title="删除会员"
        width="500"
        align-center
      >
        <span>通过ID删除该用户</span>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="danger" @click="confirmDeleteMember"
              >确认</el-button
            >
          </div>
        </template>
      </el-dialog>
    </div>
    <!-- 收货地址模态框 -->
    <div>
      <el-dialog
        v-model="showAddressModal"
        title="收货地址"
        width="80%"
        @close="clearAddresses"
      >
        <el-table :data="addresses" style="width: 100%">
          <el-table-column prop="name" label="姓名" width="120" />
          <el-table-column prop="phoneNumber" label="电话号码" width="180" />
          <el-table-column
            prop="defaultStatus"
            label="默认地址"
            width="100"
            :formatter="formatDefaultStatus"
          />
          <el-table-column prop="postCode" label="邮政编码" width="120" />
          <el-table-column prop="province" label="省份" />
          <el-table-column prop="city" label="城市" />
          <el-table-column prop="region" label="区域" />
          <el-table-column prop="detailAddress" label="详细地址" />
        </el-table>
      </el-dialog>
    </div>
    <!-- 优惠券历史信息模态框 -->
    <div>
      <el-dialog
        v-model="showCouponHistoriesModal"
        title="优惠券历史信息"
        width="80%"
        @close="clearCouponHistories"
      >
        <el-table :data="couponHistories" style="width: 100%">
          <el-table-column prop="couponCode" label="优惠券代码" width="180" />
          <el-table-column
            prop="memberNickname"
            label="领取人昵称"
            width="180"
          />
          <el-table-column
            prop="getType"
            label="获取类型"
            width="120"
            :formatter="formatGetType"
          />
          <el-table-column
            prop="useStatus"
            label="使用状态"
            width="120"
            :formatter="formatUseStatus"
          />
          <el-table-column prop="createTime" label="领取时间" width="180" />
          <el-table-column prop="useTime" label="使用时间" width="180" />
          <el-table-column prop="orderSn" label="订单号码" width="180" />
        </el-table>
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
  formatter,
  ElTable,
  ElTableColumn
} from "element-plus";
import moment from "moment";

// 定义组件名称
defineOptions({
  name: "Member"
});

// 定义响应式数据
const members = ref([]);
const addresses = ref([]);
const couponHistories = ref([]);
const totalMembers = ref(0);
const currentPage = ref(1);
const pageSize = ref(10);
const successMessage = ref(""); // 存储操作成功消息
const jumpPage = ref(1); // 跳转的页码
const searchType = ref(""); // 搜索条件
const searchQuery = ref(""); // 搜索关键字
const showAddMemberModal = ref(false); // 控制新增会员模态框的显示
const showEditMemberModal = ref(false); // 控制修改会员模态框的显示
const showAddressModal = ref(false); // 控制查看收货地址模态框的显示
const showAddAddressModal = ref(false); // 控制新增收货地址模态框的显示
const showEditAddressModal = ref(false); // 控制修改收货地址模态框的显示
const showCouponHistoriesModal = ref(false); // 控制查看优惠券模态框的显示
const addMemberForm = ref(null); // 新建会员表单引用
const editMemberForm = ref(null); // 修改会员表单引用
const dialogVisible = ref(false); // 控制删除会员模态框的显示
const currentMemberId = ref(""); // 当前会员ID
const newMember = ref({
  username: "",
  password: "",
  nickname: "",
  phone: "",
  icon: "",
  gender: "",
  birthday: "",
  city: "",
  personalizedSignature: ""
}); // 存储新增会员的信息
const editMember = ref({
  id: "",
  username: "",
  password: "",
  nickname: "",
  phone: "",
  icon: "",
  gender: "",
  birthday: "",
  city: "",
  personalizedSignature: "",
  memberLevelId: "",
  integration: "",
  growth: "",
  historyIntegration: ""
}); // 存储修改会员的信息
const newAddr = ref({
  memberId: "",
  name: "",
  phoneNumber: "",
  defaultStatus: 0, // 默认设置为非默认地址
  postCode: "",
  province: "",
  city: "",
  region: "",
  detailAddress: ""
}); // 存储新增会员收货地址的信息
const editAddr = ref({
  memberId: "",
  name: "",
  phoneNumber: "",
  defaultStatus: 0, // 默认设置为非默认地址
  postCode: "",
  province: "",
  city: "",
  region: "",
  detailAddress: ""
}); // 存储修改会员收货地址的信息

// formatter
const formatDate = date => {
  if (!date) return "";
  const parsedDate = moment(date);
  if (parsedDate.isValid()) {
    return parsedDate.format("YYYY-MM-DD");
  } else {
    return "日期无效";
  }
};

const genderFormatter = (row, column, cellValue) => {
  return genderMap[cellValue] || "未知";
};

const memberLevelFormatter = (row, column, cellValue) => {
  return memberLevelMap[cellValue] || "未知";
};

const formatDefaultStatus = (row, column, cellValue) => {
  return cellValue === 1 ? "是" : "否";
};

const formatGetType = row => {
  return row.getType === 0 ? "后台赠送" : "主动获取";
};

const formatUseStatus = row => {
  switch (row.useStatus) {
    case 0:
      return "未使用";
    case 1:
      return "已使用";
    case 2:
      return "已过期";
    default:
      return "未知";
  }
};

// 定义会员状态映射
const memberStatusMap = {
  0: "关闭",
  1: "启用"
};

const genderMap = {
  "0": "未知",
  "1": "男",
  "2": "女"
};

const memberLevelMap = {
  "0": "普通会员",
  "1": "黄金会员",
  "2": "白金会员",
  "3": "钻石会员"
};

// 计算总页数
const totalPage = computed(() =>
  Math.ceil(totalMembers.value / pageSize.value)
);

// 获取会员数据的函数
const fetchMembers = async () => {
  console.log("请求参数：", { page: currentPage.value, size: pageSize.value });
  try {
    const response = await axios.get("http://localhost:8080/members/pages", {
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
      throw new Error("Failed to fetch members: " + data.msg);
    }
    members.value = data.data.records; // 假设后端返回的数据结构中有一个 records 数组包含会员数据
    totalMembers.value = data.data.total; // 假设后端返回的数据结构中有一个 total 属性包含总记录数
  } catch (error) {
    console.error("There was an error fetching the members!", error);
  }
};

// 获取指定会员的收货地址信息
const fetchMemberAddresses = async memberId => {
  currentMemberId.value = memberId;
  console.log("请求参数: memberId: ", currentMemberId.value);
  try {
    const response = await axios.get(
      `http://localhost:8080/members/member-receive-addresses/${currentMemberId.value}`
    );
    if (response.status !== 200) {
      throw new Error("Network response was not ok");
    }
    if (response.data.code !== 200) {
      throw new Error("Failed to fetch member addresses: " + response.data.msg);
    }
    if (
      response.data.data.length === 0 ||
      !response.data.data ||
      response.data.data === null
    ) {
      ElMessage.warning("该会员暂无收货地址！");
      return;
    }
    console.log(response.data.data);
    addresses.value = response.data.data; // 假设后端返回的数据结构中直接包含收货地址信息
  } catch (error) {
    console.error("There was an error fetching the member addresses!", error);
  }
};
// 打开模态框并显示指定会员的收货地址信息
const openAddress = async memberId => {
  await fetchMemberAddresses(memberId); // 获取收货地址信息
  showAddressModal.value = true; // 显示模态框
};

const clearAddresses = () => {
  addresses.value = [];
};

// 获取指定会员的优惠券历史信息
const fetchMemberCouponHistories = async memberId => {
  currentMemberId.value = memberId;
  console.log("请求参数: memberId: ", currentMemberId.value);
  try {
    const response = await axios.get(
      `http://localhost:8080/couponHistories/memberId/${currentMemberId.value}`
    );
    if (response.status !== 200) {
      throw new Error(
        "Failed to fetch member coupon history: " + response.data.msg
      );
    }
    console.log(response.data);
    couponHistories.value = response.data; // 假设后端返回的数据结构中直接包含优惠券历史信息
  } catch (error) {
    console.error(
      "There was an error fetching the member coupon history!",
      error
    );
  }
};

// 打开模态框并显示指定会员的优惠卷历史记录信息
const openCouponHistory = async memberId => {
  await fetchMemberCouponHistories(memberId); // 获取优惠卷历史记录信息
  showCouponHistoriesModal.value = true; // 显示模态框
};

const clearCouponHistories = () => {
  couponHistories.value = [];
};

// 搜索会员的函数
const search = async () => {
  if (!searchQuery.value) {
    fetchMembers();
    return;
  }

  let searchUrl = "http://localhost:8080/members/";
  if (searchType.value === "userId") {
    searchUrl += searchQuery.value; // 如果搜索类型为用户ID
  } else if (searchType.value === "username") {
    searchUrl += `username/${searchQuery.value}`; // 如果搜索类型为用户名
  } else {
    ElMessage.warning("请选择搜索类型！");
    return;
  }

  try {
    const response = await axios.get(searchUrl);
    if (response.data.data) {
      members.value = [response.data.data];
      totalMembers.value = 1; // 使用的用户ID和用户名都是唯一的
    } else {
      ElMessage.warning("未找到该会员！");
      fetchMembers();
    }
  } catch (error) {
    console.error("搜索会员时发生错误！", error);
    ElMessage.error("搜索过程中发生错误！");
    fetchMembers();
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
  phone: [
    { required: true, message: "请输入电话号码", trigger: "blur" },
    { pattern: /^1[3-9]\d{9}$/, message: "电话号码格式不正确", trigger: "blur" }
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
  birthday: [
    { required: false, message: "请选择生日", trigger: "change" },
    {
      validator: (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请选择生日"));
        } else if (new Date(value) > new Date()) {
          callback(new Error("生日不能超过当前日期"));
        } else {
          callback();
        }
      },
      trigger: "change"
    }
  ],
  memberLevelId: [
    { required: false, message: "请选择会员等级ID", trigger: "change" }
  ],
  integration: [
    { required: false, message: "请输入积分", trigger: "blur" },
    { pattern: /^\d+$/, message: "积分必须是数字", trigger: "blur" }
  ],
  growth: [
    { required: false, message: "请输入成长值", trigger: "blur" },
    { pattern: /^\d+$/, message: "成长值必须是数字", trigger: "blur" }
  ],
  historyIntegration: [
    { required: false, message: "请输入历史积分", trigger: "blur" },
    { pattern: /^\d+$/, message: "历史积分必须是数字", trigger: "blur" }
  ]
};

// 新增会员的函数
const addMember = async () => {
  addMemberForm.value.validate(async valid => {
    if (!addMemberForm.value) {
      console.log("表单引用不存在！");
      return;
    }
    if (valid) {
      try {
        await axios.post("http://localhost:8080/members", newMember.value);
        ElMessage.success("会员新增成功！");
        // 关闭模态框，刷新列表，重置表单等操作
        showAddMemberModal.value = false;
        fetchMembers();
        newMember.value = {
          username: "",
          password: "",
          nickname: "",
          phone: "",
          icon: "",
          gender: "",
          birthday: "",
          city: "",
          personalizedSignature: ""
        };
      } catch (error) {
        console.error("新增会员失败", error);
        ElMessage.error("新增会员失败，请稍后再试！");
      }
    } else {
      ElMessage.error("请检查输入是否正确！");
    }
  });
};

const getMemberDetails = async memberId => {
  const detailsUrl = `http://localhost:8080/members/${memberId}`;
  try {
    const response = await axios.get(detailsUrl);
    if (response.data.data) {
      return response.data.data; // 返回会员详细信息
    } else {
      console.warn("未找到该会员！");
      ElMessage.warning("未找到该会员！");
      return null;
    }
  } catch (error) {
    console.error("获取会员详细信息时发生错误！", error);
    ElMessage.error("获取会员信息失败！");
    return null;
  }
};

// 修改会员的逻辑
const updateMember = async memberId => {
  const memberDetails = memberId ? await getMemberDetails(memberId) : {};
  console.log("会员详细信息：", memberDetails);
  // 使用Object.assign更新editMember对象，确保响应性
  Object.assign(editMember.value, memberDetails);
  // 显示模态框的逻辑
  showEditMemberModal.value = true;
};

// 更新会员信息的API调用
const updateMemberAPI = async member => {
  try {
    const response = await axios.put(
      `http://localhost:8080/members/${member.id}`,
      member
    );
    ElMessage.success("会员信息已更新！");
    return response.data.data;
  } catch (error) {
    console.error("更新会员信息失败:", error);
    ElMessage.error("更新会员信息失败，请稍后再试！");
    throw error;
  }
};

// 提交表单，更新成员信息的逻辑
const updateMemberSubmit = async () => {
  try {
    await updateMemberAPI(editMember.value);
    showEditMemberModal.value = false;
    console.log("会员信息已更新！");
  } catch {
    console.error("更新会员信息失败！");
  }
};

// 删除会员的函数
const deleteMember = async memberId => {
  currentMemberId.value = memberId;
  // 显示模态框的逻辑
  dialogVisible.value = true;
};

const confirmDeleteMember = async () => {
  if (!currentMemberId.value) {
    console.error("会员ID为空！");
    ElMessage.error("会员ID为空！");
    return;
  }
  try {
    await axios.delete(
      `http://localhost:8080/members/${currentMemberId.value}`
    );
    fetchMembers(); // 删除成功后刷新会员列表
    ElMessage.success("删除成功！"); // 显示 Element Plus 的成功消息提示
    dialogVisible.value = false;
  } catch (error) {
    console.error("There was an error deleting the order!", error);
    ElMessage.error("删除失败，请稍后再试！"); // 显示 Element Plus 的错误消息提示
    dialogVisible.value = false;
  }
};

// 在组件挂载时获取会员数据
onMounted(fetchMembers);

// 监听 jumpPage 的变化，自动跳转页面
watch(jumpPage, newPage => {
  if (newPage >= 1 && newPage <= totalPage.value) {
    currentPage.value = newPage;
    fetchMembers();
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
    fetchMembers();
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
  gap: 10px;
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
