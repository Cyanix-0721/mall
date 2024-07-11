<template>
  <el-card class="box-card">
    <template v-slot:header>
      <div class="clearfix">
        <span>操作历史记录</span>
      </div>
    </template>
    <el-table :data="operateHistories" style="width: 100%" stripe>
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="orderId" label="订单ID" />
      <el-table-column prop="operateMan" label="操作人" />
      <el-table-column prop="formattedCreateTime" label="创建时间" />
      <el-table-column prop="orderStatus" label="订单状态" />
      <el-table-column prop="note" label="备注" />
    </el-table>
    <div class="pagination-container">
      <el-pagination
        :total="total"
        layout="total, prev, pager, next, jumper"
        :current-page="pageNum"
        :page-size="pageSize"
        @current-change="handlePageChange"
      />
    </div>
  </el-card>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      operateHistories: [],
      pageSize: 10,
      pageNum: 1,
      total: 0
    };
  },
  mounted() {
    this.fetchOperateHistories();
  },
  methods: {
    fetchOperateHistories() {
      axios
        .get(`http://localhost:8080/operateHistories/list`, {
          params: {
            pageSize: this.pageSize,
            pageNum: this.pageNum
          }
        })
        .then(response => {
          const data = response.data;
          // Format the createTime field to a readable date
          this.operateHistories = data.records.map(record => ({
            ...record,
            formattedCreateTime: this.formatDate(record.createTime)
          }));
          this.total = data.total;
        })
        .catch(error => {
          console.error("Failed to fetch operate histories:", error);
        });
    },
    handlePageChange(newPageNum) {
      this.pageNum = newPageNum;
      this.fetchOperateHistories();
    },
    formatDate(timestamp) {
      const date = new Date(timestamp);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      const seconds = String(date.getSeconds()).padStart(2, "0");
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
  }
};
</script>

<style scoped>
.box-card {
  margin: 20px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.el-table {
  border: none;
}

.el-table th,
.el-table td {
  text-align: center;
}

.pagination-container {
  display: flex;
  justify-content: center;
  padding: 20px 0;
}

.el-pagination {
  margin: 0;
}
</style>
