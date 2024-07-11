export default {
  path: "/order",
  redirect: "/order/index",
  meta: {
    icon: "radix-icons:archive",
    // showLink: false,
    title: "订单服务",
    rank: 50
  },
  children: [
    {
      path: "/order/orders",
      name: "Order",
      component: () => import("@/views/order/orders.vue"),
      meta: {
        title: "订单管理",
        showParent: true,
        icon: "ri:account-circle-line"
      }
    },
    {
      path: "/order/logistics",
      name: "Logistics",
      component: () => import("@/views/order/logistics.vue"),
      meta: {
        icon: "ant-design:car-filled",
        title: "订单物流",
        showParent: true
      }
    },
    {
      path: "/order/ComponyAddress",
      name: "ComponyAddress",
      component: () => import("@/views/order/componyAddress.vue"),
      meta: {
        icon: "uim:house-user",
        title: "发货公司",
        showParent: true
      }
    },
    {
      path: "/order/returnApply",
      name: "returnApply",
      component: () => import("@/views/order/returnApply.vue"),
      meta: {
        icon: "ic:sharp-keyboard-return",
        title: "退货申请",
        showParent: true
      }
    },
    {
      path: "/order/returnReasons",
      name: "returnReasons",
      component: () => import("@/views/order/returnReasons.vue"),
      meta: {
        icon: "material-symbols:assignment-return",
        title: "退货原因",
        showParent: true
      }
    },
    {
      path: "/order/operateHistories",
      name: "operateHistories",
      component: () => import("@/views/order/operateHistories.vue"),
      meta: {
        icon: "solar:history-2-bold",
        title: "操作历史",
        showParent: true
      }
    }
  ]
} satisfies RouteConfigsTable;
